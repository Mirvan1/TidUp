package com.example.myapplication.View;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.myapplication.Models.ExportEmployeeResponse;
import com.example.myapplication.Models.GetShiftResponse;
import com.example.myapplication.Models.UserShiftResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class qrScanner extends Fragment {
    private CodeScanner mCodeScanner;
    TextView startShift,endShift;
    int count=0;
    View view;
    String entrance="",exit="";
    String startShiftSave="",endShiftSave="";
    String role;
    Button qrButton;
    SharedPreferences sharedpreferences;
    List<String> username=new ArrayList<>();
    List<String> roleExport=new ArrayList<>();
    List<String> startShiftExport=new ArrayList<>();
    List<String> endShiftExport=new ArrayList<>();
    int listSize;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
         view= inflater.inflate(R.layout.fragment_qr_scanner, container, false);
//         getShiftRequest(MainPage.userID);
        CodeScannerView scannerView = view.findViewById(R.id.scanner_view);
        startShift=view.findViewById(R.id.startShift);
        endShift=view.findViewById(R.id.endShift);
        qrButton=view.findViewById(R.id.qrButton);
//        qrRole=view.findViewById(R.id.qrRole);
//        qrUsername=view.findViewById(R.id.qrUsername);
////        startShift.setText(startShiftSave);
////        endShift.setText(endShiftSave);
//            qrRole.setText(login.userRole);
//            qrUsername.setText(login.userName);
        mCodeScanner = new CodeScanner(activity, scannerView);

        mCodeScanner.setDecodeCallback(new DecodeCallback() {

            @Override
            public void onDecoded(@NonNull final Result result) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getShiftRequest(MainPage.userID);
                        startShift.setText(startShiftSave);
                        endShift.setText(endShiftSave);
                        count++;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd - HH:mm:ss", Locale.US);
                       // String currentDateandTime = sdf.format(new Date());
                        String total=sdf.format(new Date());
                                //+" :"+result.getText();
                      //  sharedpreferences=getContext().getSharedPreferences("saveShift", Context.MODE_PRIVATE);
                       // SharedPreferences.Editor editor=sharedpreferences.edit();
                            if(count%2==1){
                                 entrance=total;
                               // editor.putString("entranceKey",entrance);
                                //editor.apply();
                                startShift.setText(entrance);
                            }else{
                                 exit=total;
                                // editor.putString("exitKey",exit);
                                // editor.apply();
                                endShift.setText(exit);
                            }
                            //if(!entrance.equals("") && !exit.equals("")){
                                shiftRequest(entrance,exit,MainPage.userID);
                                //editor.clear();
                            //}

                        // Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        try {
            scannerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCodeScanner.startPreview();
                }
            });

        }
        catch (Exception e){
            Snackbar.make(view,"Something goes wrong",Snackbar.LENGTH_LONG).show();
        }
       getShiftRequest(MainPage.userID);
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role.equals("manager")){
                    if(permissionAlreadyGranted()){
                        exportEmployeeRequest(MainPage.companyID);
                    return;
                    }
                    requestPermission();

                    //Snackbar.make(view,"Download employee ",Snackbar.LENGTH_LONG).show();
                }
                else{
                    Snackbar.make(view,"You don't have permission to access",Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getShiftRequest(MainPage.userID);
        if(mCodeScanner!=null) {
            mCodeScanner.startPreview();
            if(startShift.getText().equals("")){
                startShift.setText(startShiftSave);
            }
            if(endShift.getText().equals("")){
                endShift.setText(endShiftSave);
            }
        }
    }
    @Override
    public void onPause() {
        if(mCodeScanner!=null) {
            mCodeScanner.releaseResources();
        }
        super.onPause();
    }

    public void shiftRequest(String startShift,String endShift,String userID){
        Call<UserShiftResponse> req= ManagerAll.getInstance().shiftResponseCall(startShift,endShift,userID);
        req.enqueue(new Callback<UserShiftResponse>() {
            @Override
            public void onResponse(Call<UserShiftResponse> call, Response<UserShiftResponse> response) {
//                     role=response.body().getRole();
                Snackbar.make(view,"add",Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserShiftResponse> call, Throwable t) {
                Snackbar.make(view,"fail",Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public void getShiftRequest(String userID){
        Call<GetShiftResponse> req=ManagerAll.getInstance().getShiftResponseCall(userID);
        req.enqueue(new Callback<GetShiftResponse>() {
            @Override
            public void onResponse(Call<GetShiftResponse> call, Response<GetShiftResponse> response) {
                startShiftSave=response.body().getStartShift();
                role=response.body().getRole();
                endShiftSave=response.body().getEndShift();
            }

            @Override
            public void onFailure(Call<GetShiftResponse> call, Throwable t) {

            }
        });
    }


    public void exportEmployee(View view){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("#"+",Username"+",Entrance Time"+",Exit Time"+",Role");
        for(int i=0;i<listSize;i++){
            stringBuilder.append("\n"+i+",").append(username.get(i)).append(",").append(startShiftExport.get(i)).append(",").append(endShiftExport.get(i)).append(",").append(roleExport.get(i));
            Log.e("usernameExport",username.get(i));
            Log.e("roleEx",roleExport.get(i));
        }
        try{
            FileOutputStream outputStream=getContext().openFileOutput("data.csv", Context.MODE_PRIVATE);
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.close();
            Context context=getContext();
            File fileLoc=new File(context.getFilesDir(),"data.csv");
            Uri path= FileProvider.getUriForFile(context,"com.example.myapplication.fileprovider",fileLoc);
            Intent fileIntent=new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT,"Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM,path);
            startActivity(Intent.createChooser(fileIntent,"Share"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean permissionAlreadyGranted() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            //startActivityForResult(getPickImageChooserIntent(), 200);
            exportEmployee(view);
            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            exportEmployee(view);
            Snackbar.make(view,"Permission granted success",Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view,"Permission is denied",Snackbar.LENGTH_LONG).show();
            boolean showRationale = shouldShowRequestPermissionRationale( Manifest.permission.WRITE_EXTERNAL_STORAGE );
            if (! showRationale) {
                openSettingsDialog();
            }
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //export(view);
            // startActivityForResult(getPickImageChooserIntent(), 200);
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
    }

    private void openSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, 101);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 221) {
            String dataa = data.getData().toString();
            String filename = dataa.substring(dataa.lastIndexOf("/") + 1);
            String path = data.getData().getPath();
            Log.e("path", path);
            Log.e("file", filename);
            FileReader fr = null;
            String fild = getContext().getFilesDir().toString();
            Log.e("file direc ", fild);
            File externalFile = new File(getContext().getExternalFilesDir(path), "employee.txt");
            StringBuilder stringBuilder = new StringBuilder();
            try {
                fr = new FileReader(externalFile);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    stringBuilder.append(line).append("\n");
                    line = br.readLine();
                }
                //tvImport.setText(line.charAt(0));
                Log.e("result", line);
                Toast.makeText(getContext(), "text" + line, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void exportEmployeeRequest(String companyID){
        Call<List<ExportEmployeeResponse>> req=ManagerAll.getInstance().exportEmployeeResponseCall(companyID);
       req.enqueue(new Callback<List<ExportEmployeeResponse>>() {
           @Override
           public void onResponse(Call<List<ExportEmployeeResponse>> call, Response<List<ExportEmployeeResponse>> response) {
               listSize=response.body().size();
               for(int i=0;i<listSize;i++){
                   username.add(response.body().get(i).getUsername());
                   startShiftExport.add(response.body().get(i).getStartShift());
                   endShiftExport.add(response.body().get(i).getEndShift());
                   roleExport.add(response.body().get(i).getRole());
               }
           }

           @Override
           public void onFailure(Call<List<ExportEmployeeResponse>> call, Throwable t) {
               Snackbar.make(view,t.getMessage(),Snackbar.LENGTH_LONG).show();
           }
       });


    }
}
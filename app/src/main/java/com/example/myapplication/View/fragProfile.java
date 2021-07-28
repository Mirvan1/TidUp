package com.example.myapplication.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.ExportDataResponse;
import com.example.myapplication.Models.InviteResponse;
import com.example.myapplication.Models.UserProfile;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.example.myapplication.View.Utils.GetSharedPreferences;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragProfile extends Fragment {
    View view;
    TextView profCompanyName,profFullName,profpName,tvImport;
    RelativeLayout editProfLayout,exportData,importData,signOut,assignManager;
    List<String> productUsername=new ArrayList<>();
    List<String> productName=new ArrayList<>();
    List<String> productPrice=new ArrayList<>();
    List<String> productQuantity=new ArrayList<>();
    List<String> productDate=new ArrayList<>();
    List<String> productBarcode=new ArrayList<>();
    List<String> productNote=new ArrayList<>();
    List<String> productFolder=new ArrayList<>();
    ProgressBar profProgress;
    int listSize;
    public static String username="",password="",company="",role="",roleforAssign="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_frag_profile, container, false);
        setValues();
    return  view;
    }
    public void setValues(){
        profFullName=view.findViewById(R.id.profFullName);
        profCompanyName=view.findViewById(R.id.profCompanyName);
        profpName=view.findViewById(R.id.profpName);
        tvImport=view.findViewById(R.id.tvImport);
        profProgress=view.findViewById(R.id.profProgress);
        profileResponse(MainPage.userID,MainPage.companyID);
       // profileResponse(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin);
        //profFullName.setText(sendFolderValue.usernamefromLogin);
        //profCompanyName.setText(sendFolderValue.companyNameFromLogin);
        //String pp=sendFolderValue.usernamefromLogin.substring(0,2);
        //profpName.setText(pp);
        editProfLayout=view.findViewById(R.id.editProfLayout);
        exportData=view.findViewById(R.id.exportData);
        importData=view.findViewById(R.id.importData);
        signOut=view.findViewById(R.id.signOut);
        assignManager=view.findViewById(R.id.assignManager);
        editProfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,new fragEditProfile(),"editprof")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack("editprof")
                        .commit();

            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSharedPreferences getSharedPreferences=new GetSharedPreferences(getActivity());
                getSharedPreferences.outSession();
//                getSharedPreferences.getSession().edit().clear();
//                getSharedPreferences.getSession().edit().apply();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

//        exportResponse(sendFolderValue.companyIDfromLogin);
    exportData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (permissionAlreadyGranted()) {
                Snackbar.make(view, "Permission is already granted!", Snackbar.LENGTH_LONG).show();
                //export(view);
                exportResponse(MainPage.companyID);
                //  startActivityForResult(getPickImageChooserIntent(), 200);
                return;
            }
            requestPermission();
            //export(view);
        }
    });

    importData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getContext(),"not working",Toast.LENGTH_LONG).show();
            //importData();
            inviteDialog();
        }
    });
    assignManager.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(roleforAssign.equals("manager")){
            ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.listViewLayout,new userList(),"assiggn")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .addToBackStack("assign")
                    .commit();}
            else{Snackbar.make(view,"You don't have permission",Snackbar.LENGTH_LONG).show();
            }
        }
    });
    }



    public void profileResponse(String id, String companyID){
        Call<UserProfile> req= ManagerAll.getInstance().userProfileCall(id,companyID);
        req.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                profProgress.setVisibility(View.INVISIBLE);
                roleforAssign=response.body().getRole();
                 username=response.body().getUsername();
                role="-"+response.body().getRole();
                String con=username+role;
                if(con.length()>12){
                    profFullName.setTextSize(30);
                }
                profFullName.setText(con);
                profCompanyName.setText(response.body().getCompanyName());


                String pp=response.body().getUsername().substring(0,1);
                profpName.setText(pp);
                 password=response.body().getPassword();
                 company=response.body().getCompanyID();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }


    public void export(View view){
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append("#"+",Name"+",Price"+",Quantity"+",Date"+",Barcode"+",Note"+",Folder");

    for(int i=0;i<listSize;i++){
        stringBuilder.append("\n"+i+",").append(productUsername.get(i)).append(",").append(productName.get(i)).append(",").append(productPrice.get(i)).append(",").append(productQuantity.get(i)).append(",").append(productDate.get(i)).append(",").append(productBarcode.get(i)).append(",").append(productNote.get(i)).append(",").append(productFolder.get(i));

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
        startActivity(Intent.createChooser(fileIntent,"send email"));
        }
    catch (Exception e) {
        e.printStackTrace();
        }
    }
    private boolean permissionAlreadyGranted() {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            //startActivityForResult(getPickImageChooserIntent(), 200);
            export(view);
            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           // startActivityForResult(getPickImageChooserIntent(), 200);
            //Toast.makeText(getActi, "Permission granted successfully", Toast.LENGTH_SHORT).show();
            export(view);
            Snackbar.make(view,"Permission granted success",Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(view,"Permission is denied",Snackbar.LENGTH_LONG).show();
            // Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
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
    public void importData(){

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        startActivityForResult(intent,221);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 221) {
//            String path = data.getData().getPath();
//            String filename=path.substring(path.lastIndexOf("/")+1);
//            String pat=path.substring(path.indexOf("/")+1);

//            try {
//                FileInputStream fis=getContext().openFileInput("hostr.txt");
//                InputStreamReader isr=new InputStreamReader(fis);
//                BufferedReader br=new BufferedReader(isr);
//                StringBuilder sb=new StringBuilder();
//                String line;
//                while((line=br.readLine())!=null){
//                    sb.append(line);
//
//                }
//                Log.e("strinBUIl", sb.toString());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            String dataa = data.getData().toString();
            String filename = dataa.substring(dataa.lastIndexOf("/") + 1);
            String path = data.getData().getPath();
            Log.e("path", path);
            Log.e("file", filename);
            FileReader fr = null;
            String fild = getContext().getFilesDir().toString();
            Log.e("file direc ", fild);
            File externalFile = new File(getContext().getExternalFilesDir(path), "hostr.txt");

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

    public void exportResponse(String companyID){
        Call<List<ExportDataResponse>> req=ManagerAll.getInstance().exportDataResponseCall(companyID);
        req.enqueue(new Callback<List<ExportDataResponse>>() {
            @Override
            public void onResponse(Call<List<ExportDataResponse>> call, Response<List<ExportDataResponse>> response) {

                 listSize=response.body().size();
                    for (int i = 0; i < response.body().size(); i++) {
                        productUsername.add(response.body().get(i).getUsername().toString());
                        productName.add(response.body().get(i).getProductName().toString());
                        productPrice.add(response.body().get(i).getProductPrice().toString());
                        productQuantity.add(response.body().get(i).getProductQuantity().toString());
                        productDate.add(response.body().get(i).getProductDate());
                        productBarcode.add(response.body().get(i).getProductBarcode());
                        productNote.add(response.body().get(i).getProductNote());
                        productFolder.add(response.body().get(i).getProductFolder());
                        Log.e("usernmae", productUsername.get(i));
                        Log.e("date", productDate.get(i));
                        Log.e("pric", productPrice.get(i));
                        Log.e("note", productNote.get(i));
                    }
                //}
                Snackbar.make(view,"response",Snackbar.LENGTH_LONG).show();
                }

            @Override
            public void onFailure(Call<List<ExportDataResponse>> call, Throwable t) {
Snackbar.make(view,"fail",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void inviteResponse(String companyID,String invited){
        Call<InviteResponse> req=ManagerAll.getInstance().inviteResponseCall(companyID,invited);
        req.enqueue(new Callback<InviteResponse>() {
            @Override
            public void onResponse(Call<InviteResponse> call, Response<InviteResponse> response) {
                Snackbar.make(view,response.body().getUsername()+"is added to company",Snackbar.LENGTH_LONG).show();
                Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<InviteResponse> call, Throwable t) {
            Snackbar.make(view,"problem",Snackbar.LENGTH_LONG).show();
            }
        });

    }

    public void inviteDialog(){
        LayoutInflater inflater=getLayoutInflater();
        View view = inflater.inflate(R.layout.dialoginvite,null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Invite user");
        alertDialog.setCancelable(false);
        EditText inviteEt = view.findViewById(R.id.inviteUser);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String invite=inviteEt.getText().toString();
                inviteResponse(MainPage.companyID,invite);
                // aa.addFolderResponse(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin,productFolder);
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();

    }
}
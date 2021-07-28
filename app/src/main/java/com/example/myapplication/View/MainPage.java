package com.example.myapplication.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.View.Utils.GetSharedPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainPage extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private GetSharedPreferences preferences;
    LinearLayout itemLinearLayout;
    public static String userID;
    public static String companyID;
   BottomNavigationView bottomNavigationMenuView;
    Fragment selectedFragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        preferences=new GetSharedPreferences(MainPage.this);
        sharedPreferences=preferences.getSession();
        userID=preferences.getSession().getString("userID","null");
        companyID=preferences.getSession().getString("companyID","null");
        Log.e("id m",userID);
        Log.e("email m",companyID);
        itemLinearLayout=findViewById(R.id.listViewLayout);
        controlSession();
//checkFirstRun();
BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
getSupportFragmentManager().beginTransaction().replace(R.id.listViewLayout,new fragmentMainPage()).commit();

//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Fragment selectedFragment = new fragmentMainPage();
//                switch (item.getItemId()) {
//                    case R.id.item0:
//                        selectedFragment=new fragmentMainPage();
//                        break;
////                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
////                        ft1.add(R.id.listViewLayout, new fragmentMainPage(),"mainf");
////                        ft1.addToBackStack("mainf");
////                        ft1.commit();
////                        getFragmentManager().popBackStackImmediate();
//
//                       // Toast.makeText(MainPage.this, "main", Toast.LENGTH_SHORT).show();
//
//                    case R.id.item1:
//                        Toast.makeText(MainPage.this, "qr", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.item2:
//                        selectedFragment=new fragProfile();
//                        break;
//                       // Toast.makeText(MainPage.this, "profile", Toast.LENGTH_SHORT).show();
////                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
////                        ft3.replace(R.id.listViewLayout, new fragProfile(),"profile");
////                        ft3.addToBackStack("profile");
////                        ft3.commit();
////                        getFragmentManager().popBackStackImmediate();
//
//
//                }
//                FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
//                ft3.replace(R.id.listViewLayout, selectedFragment,"profile");
//                ft3.addToBackStack("profile");
//                ft3.commit();
//                getFragmentManager().popBackStackImmediate();
//                return true;
//            }
//        });


        //\



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                    case R.id.item0:

                        selectedFragment=new fragmentMainPage();
                        break;
                    case R.id.item1:
                       // AlertDialog.Builder builder;
                        if(android.os.Build.VERSION.SDK_INT< Build.VERSION_CODES.KITKAT)
                        {
                       //     builder = new AlertDialog.Builder(this);
                             //    builder = new AlertDialog.Builder(getApplicationContext());
                        Toast.makeText(getApplicationContext(),"your device cannot",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if (permissionAlreadyGranted()) {
                                Snackbar.make(findViewById(android.R.id.content), "Permission is already granted!", Snackbar.LENGTH_LONG).show();
                                //export(view);
                                selectedFragment=new qrScanner();
                                //  startActivityForResult(getPickImageChooserIntent(), 200);
                            }
                           else {
                                requestPermission();
                                selectedFragment=new qrScanner();
                            }
                           }
                        Toast.makeText(MainPage.this, "qr", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:

                        selectedFragment=new fragProfile();

                        break;
                        case  R.id.item3:
                            selectedFragment=new fragDashboard();
                }
                    getSupportFragmentManager().beginTransaction().replace(R.id.listViewLayout,selectedFragment).commit();
                    return true;
                }
            };




//    public void define(){
//        itemLinearLayout=findViewById(R.id.listViewLayout);
//
//        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
//        ft1.add(R.id.listViewLayout, new fragmentMainPage());
//        ft1.commit();
//    }


//    public void controlSession(){
//
//        if(sharedPreferences.getString("id",null)==null && sharedPreferences.getString("email",null)==null){
//            Intent intent=new Intent(MainPage.this,MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        }
//    }


//    public void define(){
//    preferences=new GetSharedPreferences(MainPage.this);
//    getSharedPreferences=preferences.getSession();
//    }
//    public void control(){
//        if(getSharedPreferences.getString("id",null)==null &&
//                getSharedPreferences.getString("email",null)==null
//                && getSharedPreferences.getString("username",null)==null){
////            Intent intent = new Intent(MainPage.this, ;
////            startActivity(intent);
////            finish();
//
//
//        }
//
//    }

    @Override
    protected void onPause() {
        super.onPause();
        controlSession();
//checkFirstRun();
        Log.e("pa user id frm",sendFolderValue.userIDfromLogin);
        Log.e("pa company id frm",sendFolderValue.companyIDfromLogin);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controlSession();
//checkFirstRun();
        Log.e(" de user id frm",sendFolderValue.userIDfromLogin);
        Log.e("de company id frm",sendFolderValue.companyIDfromLogin);


    }

    public void controlSession() {
        Log.e("send id",sendFolderValue.companyIDfromLogin);
        Log.e("send user id",sendFolderValue.userIDfromLogin);
//        PackageManager pm = getApplicationContext().getPackageManager();
//        boolean isInstalled = isPackageInstalled("com.example.myapplication", pm);
        if ((sharedPreferences.getString("id", null) == null && sharedPreferences.getString("companyID", null) == null)) {

//            if(isInstalled){
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
                finish();
//            }else{
//                Intent infoIntent=new Intent(MainPage.this,startApp.class);
//            startActivity(infoIntent);
//            finish();
//            }
        }
//        else if(!sendFolderValue.userIDfromLogin.equals("") && !sendFolderValue.companyIDfromLogin.equals("")) {
//            Intent intent = new Intent(MainPage.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }


    private boolean permissionAlreadyGranted() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            //startActivityForResult(getPickImageChooserIntent(), 200);
//            selectedFragment=new qrScanner();
            // export(view);
            return true;
        }

        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // startActivityForResult(getPickImageChooserIntent(), 200);
            //Toast.makeText(getActi, "Permission granted successfully", Toast.LENGTH_SHORT).show();
            selectedFragment=new qrScanner();
            // export(view);
            Snackbar.make(findViewById(android.R.id.content),"Permission granted success",Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(findViewById(android.R.id.content),"Permission is denied",Snackbar.LENGTH_LONG).show();
            // Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
            boolean showRationale = shouldShowRequestPermissionRationale( Manifest.permission.CAMERA );
            if (! showRationale) {
                openSettingsDialog();
            }
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainPage.this, Manifest.permission.CAMERA)) {
            //export(view);
            // startActivityForResult(getPickImageChooserIntent(), 200);
        }
        ActivityCompat.requestPermissions(MainPage.this, new String[]{Manifest.permission.CAMERA}, 200);
    }

    private void openSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Required Permissions");
        builder.setMessage("This app require permission to use awesome feature. Grant them in app settings.");
        builder.setPositiveButton("Take Me To SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
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
    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

//    private void checkFirstRun() {
//
//        final String PREFS_NAME = "MyPrefsFile";
//        final String PREF_VERSION_CODE_KEY = "version_code";
//        final int DOESNT_EXIST = -1;
//
//        // Get current version code
//        int currentVersionCode = BuildConfig.VERSION_CODE;
//
//        // Get saved version code
//        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);
//
//        // Check for first run or upgrade
//        if (currentVersionCode == savedVersionCode) {
//         controlSession();
//
////            Intent infoIntent = new Intent(MainPage.this, startApp.class);
////            startActivity(infoIntent);
//            // This is just a normal run
//            return;
//
//        } else if (savedVersionCode == DOESNT_EXIST) {
//            Intent infoIntent = new Intent(MainPage.this, startApp.class);
//            startActivity(infoIntent);
//            Toast.makeText(getApplicationContext(),"install",Toast.LENGTH_LONG).show();
//            finish();
//            // TODO This is a new install (or the user cleared the shared preferences)
//
//        } else if (currentVersionCode > savedVersionCode) {
//            Intent infoIntent = new Intent(MainPage.this, startApp.class);
//            startActivity(infoIntent);
//            finish();
//            Toast.makeText(getApplicationContext(),"update",Toast.LENGTH_LONG).show();
//            //finish();
//            // TODO This is an upgrade
//        }
//
//        // Update the shared preferences with the current version code
//        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
//    }


}
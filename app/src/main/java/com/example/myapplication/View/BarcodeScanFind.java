package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeScanFind extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    Button findBarcodeCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

//        findBarcodeCamera=scannerView.findViewById(R.id.findBarcodeCamera);
//        findBarcodeCamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragFindBarcode fr=new fragFindBarcode();
//                ((FragmentActivity) getApplicationContext()).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.listViewLayout,fr,"fragment")
//                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .commit();
//            }
//        });
    }

    @Override
    public void handleResult(Result rawResult) {

sendFolderValue.findBarcode=rawResult.getText();
        Toast.makeText(getApplicationContext(),rawResult.getText(),Toast.LENGTH_LONG).show();
       // fragFindBarcode fr=new fragFindBarcode();
        //fr.findBarcode(sendFolderValue.userIDfromLogin,rawResult.getText());

      //  onBackPressed();
        Intent intent = new Intent(getApplicationContext(), actBarcodeScanFInd.class);
        startActivity(intent);
//                fragFindBarcode fr=new fragFindBarcode();
//
//                ((FragmentActivity) getApplicationContext()).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.listViewLayout,fr,"fragment")
//                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .commit();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
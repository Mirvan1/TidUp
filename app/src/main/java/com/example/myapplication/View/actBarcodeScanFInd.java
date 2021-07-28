package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Models.FindBarcodeResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class actBarcodeScanFInd extends AppCompatActivity {
    ImageView findBarcodePhoto;
    TextView findBarcodeName,findBarcodePrice,findBarcodeDate,findBarcodeQuantity,findBarcodeBarcode,findBarcodeNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_barcode_scan_f_ind);
        acDef();
        findBarcode(MainPage.userID,sendFolderValue.findBarcode);
        // findBarcode(sendFolderValue.userIDfromLogin,sendFolderValue.findBarcode);
    }
    public  void acDef(){
        findBarcodePhoto=findViewById(R.id.findBarcodePhoto);
        findBarcodeName=findViewById(R.id.findBarcodeName);
        findBarcodePrice=findViewById(R.id.findBarcodePrice);
        findBarcodeDate=findViewById(R.id.findBarcodeDate);
        findBarcodeQuantity=findViewById(R.id.findBarcodeQuantity);
        findBarcodeBarcode=findViewById(R.id.findBarcodeBarcode);
        findBarcodeNote=findViewById(R.id.findBarcodeNote);
    }

    public void findBarcode(String userID,String productBarcode){
        Call<FindBarcodeResponse> req= ManagerAll.getInstance().findBarcodeItemResponseCall(userID,productBarcode);
        req.enqueue(new Callback<FindBarcodeResponse>() {
            @Override
            public void onResponse(Call<FindBarcodeResponse> call, Response<FindBarcodeResponse> response) {
                findBarcodeName.setText(response.body().getProductName());
                findBarcodePrice.setText(response.body().getProductPrice());
                findBarcodeDate.setText(response.body().getProductDate());
                findBarcodeQuantity.setText(response.body().getProductQuantity());
                findBarcodeBarcode.setText(response.body().getProductBarcode());
                findBarcodeNote.setText(response.body().getProductNote());
                Picasso.get().load(response.body().getProductPhoto()).into(findBarcodePhoto);
            }

            @Override
            public void onFailure(Call<FindBarcodeResponse> call, Throwable t) {

            }
        });


    }
}
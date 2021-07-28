package com.example.myapplication.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Models.UpdateItemResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragUpdateItem extends Fragment {

    EditText updateItemName,updateItemPrice,updateItemDate,updateItemQuantity,updateItemBarcode,updateItemNote;
    View view;
    Button updateItemButtonIn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_update_item, container, false);
   define();

    return view;
    }

    public void define(){
        updateItemName=view.findViewById(R.id.updateItemName);
        updateItemPrice=view.findViewById(R.id.updateItemPrice);
        updateItemDate=view.findViewById(R.id.updateItemDate);
        updateItemQuantity=view.findViewById(R.id.updateItemQuantity);
        updateItemNote=view.findViewById(R.id.updateItemNote);
        updateItemBarcode=view.findViewById(R.id.updateItemBarcode);
        updateItemButtonIn=view.findViewById(R.id.updateItemButtonIn);

        updateItemName.setText(sendFolderValue.detailName);
        updateItemPrice.setText(sendFolderValue.detailPrice);
        updateItemDate.setText(sendFolderValue.detailDate);
        updateItemQuantity.setText(sendFolderValue.detailQuantity);
        updateItemNote.setText(sendFolderValue.detailBarcode);
        updateItemBarcode.setText(sendFolderValue.detailNote);


        updateItemButtonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=updateItemName.getText().toString();
                String price=updateItemPrice.getText().toString();
                String date=updateItemDate.getText().toString();
                String quantity=updateItemQuantity.getText().toString();
                String note=updateItemNote.getText().toString();
                String barcode=updateItemBarcode.getText().toString();
                updateItem(sendFolderValue.detailUserID,sendFolderValue.detailProductID,name,price,
                        quantity,date,barcode,note);

            }
        });

    }
    public void updateItem(String userID, String productID,String productName, String productPrice,
                           String productQuantity, String productDate, String productBarcode,
                           String productNote){
        Call<UpdateItemResponse> req= ManagerAll.getInstance().updateItemResponseCall(userID,productID,productName,productPrice,
                productQuantity,productDate,productBarcode,productNote);
        req.enqueue(new Callback<UpdateItemResponse>() {
            @Override
            public void onResponse(Call<UpdateItemResponse> call, Response<UpdateItemResponse> response) {
                fragmentMainPage fr=new fragmentMainPage();
            if(response.body().isTf()){

                Snackbar.make(view,response.body().getText(), Snackbar.LENGTH_LONG).show();

                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
            else{
                Snackbar.make(view,response.body().getText(), Snackbar.LENGTH_LONG).show();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
            }

            @Override
            public void onFailure(Call<UpdateItemResponse> call, Throwable t) {
                Snackbar.make(view,t.getMessage(), Snackbar.LENGTH_LONG).show();

            }
        });

    }


}
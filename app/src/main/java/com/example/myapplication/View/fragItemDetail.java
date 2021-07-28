package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.CheckRoleResponse;
import com.example.myapplication.Models.DeleteItemResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragItemDetail extends Fragment {
    ImageView  itemDetailPhoto;
    TextView  itemDetailNameText,itemDetailPriceText,itemDetailDateText,itemDetailQuantityText,itemDetailBarcodeText,itemDetailNoteText;
    View view;
    Button deleteOneItemButton,UpdateOneItemButton;
    ScrollView scrollView;
    Button detailBack;
    String checkRole="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_item_detail, container, false);
        define();
        //Snackbar.make(view,sendFolderValue.detailUserID+"iser"+sendFolderValue.detailProductID+" product",Snackbar.LENGTH_LONG).show();
        Toast.makeText(getContext(),sendFolderValue.detailUserID+"iser"+sendFolderValue.detailProductID+" product",Toast.LENGTH_LONG).show();
        return  view;
    }

    public void define(){
        itemDetailPhoto=view.findViewById(R.id.itemDetailPhoto);
        itemDetailNameText=view.findViewById(R.id.itemDetailNameText);
        itemDetailPriceText=view.findViewById(R.id.itemDetailPriceText);
        itemDetailDateText=view.findViewById(R.id.itemDetailDateText);
        itemDetailQuantityText=view.findViewById(R.id.itemDetailQuantityText);
        itemDetailBarcodeText=view.findViewById(R.id.itemDetailBarcodeText);
        itemDetailNoteText=view.findViewById(R.id.itemDetailNoteText);
        deleteOneItemButton=view.findViewById(R.id.deleteOneItemButton);
        UpdateOneItemButton=view.findViewById(R.id.UpdateOneItemButton);
        itemDetailNameText.setText(sendFolderValue.detailName);
        itemDetailPriceText.setText(sendFolderValue.detailPrice);
        itemDetailDateText.setText(sendFolderValue.detailDate);
        itemDetailQuantityText.setText(sendFolderValue.detailQuantity);
        itemDetailBarcodeText.setText(sendFolderValue.detailBarcode);
        itemDetailNoteText.setText(sendFolderValue.detailNote);
        scrollView=view.findViewById(R.id.scrollView);
        detailBack=view.findViewById(R.id.detailBack);
        checkRoleResponse(MainPage.userID);
        Picasso.get().load(sendFolderValue.detailPhoto).resize(500,500).into(itemDetailPhoto);
        deleteOneItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteItemCall(sendFolderValue.detailProductID,sendFolderValue.detailUserID);
              if(checkRole.equals("manager")){
                deleteDialogPopup();}
              else {
              Snackbar.make(view,"You don't have permission ",Snackbar.LENGTH_LONG).show();
              }
            }
        });

        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragItemList fr=new fragItemList();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,".")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });
        UpdateOneItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragUpdateItem fr=new fragUpdateItem();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragUpdateItem")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack("fragUpdateItem")
                        .commit();
            }
        });

//        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//
//                    UpdateOneItemButton.setVisibility(View.INVISIBLE);
//                    deleteOneItemButton.setVisibility(View.INVISIBLE);
//
//                            }
//        });

    }
    public void deleteItemCall(String userID,String productID){
        Call<DeleteItemResponse> req= ManagerAll.getInstance().deleteItemResponseCall(userID,productID);
        req.enqueue(new Callback<DeleteItemResponse>() {
            @Override
            public void onResponse(Call<DeleteItemResponse> call, Response<DeleteItemResponse> response) {

                if(response.body().isTf()){
    //                Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
                    Log.i("if dusdu","if");
                }
                else{
//                    Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
  //Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    Log.i("else dusdu","else");
                }
            }

            @Override
            public void onFailure(Call<DeleteItemResponse> call, Throwable t) {
      //          Snackbar.make(view,t.getMessage()+"",Snackbar.LENGTH_LONG).show();
            Log.i("fail","fa");
            }
        });



    }


    public void deleteDialogPopup(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Are you sure delete this item?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteItemCall(sendFolderValue.detailProductID,sendFolderValue.detailUserID);
                        fragmentMainPage fr=new fragmentMainPage();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.listViewLayout,fr,"fragMain to detail Item")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack("fragMain to detail Item")
                                .commit();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    public void checkRoleResponse(String userID){
        Call<CheckRoleResponse> req=ManagerAll.getInstance().checkRoleResponseCall(userID);

        req.enqueue(new Callback<CheckRoleResponse>() {
            @Override
            public void onResponse(Call<CheckRoleResponse> call, Response<CheckRoleResponse> response) {
                checkRole=response.body().getRoleVar();
            }

            @Override
            public void onFailure(Call<CheckRoleResponse> call, Throwable t) {

            }
        });

    }
}
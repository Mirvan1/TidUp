package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.Models.companyCreateJson;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class createCompany extends DialogFragment {
    View view;
    Button generateCode;
    EditText companyNameText;
    TextView companyCodeText;
    String companyCode;

    public createCompany(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_create_company, container, false);
        define();
        getNameOfCompany();
        return view;
    }
    public void define(){
        generateCode=view.findViewById(R.id.generateCode);

        companyCodeText=view.findViewById(R.id.companyCodeText);
        companyNameText=view.findViewById(R.id.companyNameText);
    }

    public void getNameOfCompany() {
        generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=companyNameText.getText().toString();
                SharedPreferences sh = getContext().getSharedPreferences("company", Context.MODE_MULTI_PROCESS);
                String email = sh.getString("email", "");
                signCompany(name,email);
            }
        });
    }
    public void signCompany(String companyName,String email){
       // createCompany a=new createCompany();
  Call<companyCreateJson> req= ManagerAll.getInstance().create(companyName,email);
  req.enqueue(new Callback<companyCreateJson>() {
      @Override
      public void onResponse(Call<companyCreateJson> call, Response<companyCreateJson> response) {
          companyCode=response.body().getID();
          AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
          builder.setTitle("Your company ID");
          builder.setMessage("Company ID: "+companyCode);
          builder.setNegativeButton("Copy",null);
          builder.setPositiveButton("Next",null);
          AlertDialog dialog = builder.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positive=dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negative=dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    positive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            fragVerifyAccount lg=new fragVerifyAccount();
                            ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.createToLoginLayout,lg,"createtoverifyfrag")
                                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                    .addToBackStack("createtoverifyfrag")
                                    .commit();
                            dialog.dismiss();
                        }
                    });

                    negative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View button) {
                            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("copied text",companyCode);
                            clipboard.setPrimaryClip(clip);
                            Snackbar.make(view,"Copied clipboard",Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            });

//          Button negativeButton=dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
//            negativeButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                    ClipData clip = ClipData.newPlainText("copied text",companyCode);
//                    clipboard.setPrimaryClip(clip);
//                    Snackbar.make(view,"Copied clipboard",Snackbar.LENGTH_LONG).show();
//                }
//            });


//          DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialog, int which) {
//
//
//                  //dialog.dismiss();
//              }
//          };
//          DialogInterface.OnClickListener create=new DialogInterface.OnClickListener() {
//              @Override
//              public void onClick(DialogInterface dialog, int which) {
////                  login fr=new login();
////                  ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
////                          .replace(R.id.frameLayout,fr,"crToLog")
////                          .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
////                          .addToBackStack("crToLog")
////                          .commit();
////                  Intent crtoLog=new Intent(getActivity(),createToLogin.class);
////                  startActivity(crtoLog);
//                    login lg=new login();
//                  ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
//                          .replace(R.id.createToLoginLayout,lg,"createtologinfrag")
//                          .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                          .addToBackStack("createtologinfrag")
//                          .commit();
//                  dialog.dismiss();
//              }
//          };
        //  builder.setNegativeButton("Copy",dialogClickListener);
       //   builder.setPositiveButton("Next",create);

            dialog.setCancelable(false);
          dialog.show();
      }
      @Override
      public void onFailure(Call<companyCreateJson> call, Throwable t) {
         // companyCode=response.body().getID();
          companyCode="Code can not generated";
      }
  });
    }
}
package com.example.myapplication.View;

import android.app.AlertDialog;
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
import android.widget.Toast;

import com.example.myapplication.Models.companyJoinJson;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class joinCompany extends DialogFragment {
    View view;
    Button joinCompanyButtonUsingCode;
    EditText joinCompanyIDText;
    String companyNameReq;


    public joinCompany(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_join_company, container, false);
        define();
        getIDofCompanyJoin();
        return view;
    }
    public void define(){
        joinCompanyIDText=view.findViewById(R.id.joinCompanyIDText);
        joinCompanyButtonUsingCode=view.findViewById(R.id.joinCompanyButtonUsingCode);
    }
    public  void getIDofCompanyJoin(){
        joinCompanyButtonUsingCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joinID=joinCompanyIDText.getText().toString();
                if(!joinID.equals("")) {
                    SharedPreferences sh = getContext().getSharedPreferences("company", Context.MODE_MULTI_PROCESS);
                    String email = sh.getString("emailJoin", "");
                    joinResponse(email, joinID);
                }
                else{
                    Snackbar.make(view,"Please enter valid company code",Snackbar.LENGTH_LONG).show();
                     }
                }
        });
    }

    public void joinResponse(String email,String companyID){
        Call<companyJoinJson> request=ManagerAll.getInstance().join(email,companyID);
        request.enqueue(new Callback<companyJoinJson>() {
            @Override
            public void onResponse(Call<companyJoinJson> call, Response<companyJoinJson> response) {
                Object companyNameReq=response.body().getNameJoin();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Your company");
                builder.setMessage("Is this your company? \n "+companyNameReq.toString());
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                DialogInterface.OnClickListener join=new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                  login fr=new login();
//                  ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
//                          .replace(R.id.frameLayout,fr,"crToLog")
//                          .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                          .addToBackStack("crToLog")
//                          .commit();
//                  Intent crtoLog=new Intent(getActivity(),createToLogin.class);
//                  startActivity(crtoLog);
                        fragVerifyAccount lg=new fragVerifyAccount();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.createToLoginLayout,lg,"joinoverifyfrag")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack("joinoverifyfrag")
                                .commit();
                        dialog.dismiss();
                    }
                };

                builder.setNegativeButton("No",dialogClickListener);
                builder.setNegativeButton("Join",join);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            @Override
            public void onFailure(Call<companyJoinJson> call, Throwable t) {
                companyNameReq="there was issue or there is no id like that";
                Toast.makeText(view.getContext(),companyNameReq,Toast.LENGTH_LONG).show();
            }
        });
    }
}
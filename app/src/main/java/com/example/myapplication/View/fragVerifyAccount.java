package com.example.myapplication.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.VerifyAccountResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragVerifyAccount extends Fragment {
    View view;
    TextView verifyCode,verifyError;
    Button verifyButton;
    String message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_verify_account, container, false);
    define();
        return view;
    }
    public void define(){
        verifyCode=view.findViewById(R.id.verifyCode);
        verifyButton=view.findViewById(R.id.verifyButton);
        verifyError=view.findViewById(R.id.verifyError);
          exec();
    }
    public void exec(){

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=verifyCode.getText().toString();
                Toast.makeText(getContext()," "+code,Toast.LENGTH_LONG).show();
                verifyRequest(SignUp.email,code);

            }
        });
    }

    public void verifyRequest(String userID,String verifyCode){
        Call<VerifyAccountResponse> req= ManagerAll.getInstance().verifyAccountResponseCall(userID,verifyCode);
        req.enqueue(new Callback<VerifyAccountResponse>() {
            @Override
            public void onResponse(Call<VerifyAccountResponse> call, Response<VerifyAccountResponse> response) {
                if(response.body().isTf()){
                   message=response.body().getText();
                        login lg=new login();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.createToLoginLayout,lg,"createtoverifyfrag")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack("createtoverifyfrag")
                                .commit();


                }
                else{
                    message=response.body().getText();
                }
                String smsWithStar="*"+message;
                verifyError.setText(smsWithStar);
                Snackbar.make(view,response.body().getText()+" "+ SignUp.email+" "+verifyCode,Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<VerifyAccountResponse> call, Throwable t) {
                Snackbar.make(view,"Check internet connection",Snackbar.LENGTH_LONG).show();
            }
        });

    }

}
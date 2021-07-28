package com.example.myapplication.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.LoginJson;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.example.myapplication.View.Utils.GetSharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends Fragment {
    View view;
    EditText loginEmail,loginPassword;
    Button loginButton;
    Context context;
    private SharedPreferences sharedPreferences;
    private GetSharedPreferences preferences;
    String user;
    String company;
    TextView checkCorrection;
    public static String userRole="",userName="";
    public login() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_login, container, false);
        define();
        toText();

        return view;
    }
    public void define(){
        loginPassword=view.findViewById(R.id.loginPassword);
        loginEmail=view.findViewById(R.id.loginEmail);
        loginButton=view.findViewById(R.id.loginButton);
        checkCorrection=view.findViewById(R.id.checkCorrection);

    }
    public boolean emailValidator(String emailRegex)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailRegex);
        return matcher.matches();
    }
    public boolean passwordValidator(String passwordRegex){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{5,}" +               //at least 5 characters
                "$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passwordRegex);
        return matcher.matches();

    }
    public void toText(){

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=loginEmail.getText().toString();
                String password=loginPassword.getText().toString();
                if(emailValidator(email) ){
//                    checkCorrection.setVisibility(View.INVISIBLE);
//                    loginRequest(email,password);
                    if(passwordValidator(password)){
                        checkCorrection.setVisibility(View.INVISIBLE);
                        loginRequest(email,password);
                    }
                    else{
                        checkCorrection.setVisibility(View.VISIBLE);
                        checkCorrection.setText("The password must be at least 5 characters and 1 digit");
                    }

                }

                else{
                    checkCorrection.setVisibility(View.VISIBLE);
                    checkCorrection.setText("Invalid email address");
                }

//                if(passwordValidator(password)){
//                    checkCorrection.setVisibility(View.INVISIBLE);
//                    loginRequest(email,password);
//                }
//                else{
//                    checkCorrection.setVisibility(View.VISIBLE);
//                    checkCorrection.setText("The password must be at least 5 characters and 1 digit");
//                }
            }
        });
    }



    public void loginRequest(String email,String password){
        final Call<LoginJson> req=ManagerAll.getInstance().login(email,password);
        req.enqueue(new Callback<LoginJson>() {
            @Override
            public void onResponse(Call<LoginJson> call, Response<LoginJson> response) {
                Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                checkCorrection.setText(response.body().getText());
                checkCorrection.setVisibility(View.VISIBLE);
                GetSharedPreferences getSharedPreferences=new GetSharedPreferences(getActivity());

                getSharedPreferences.setSession(response.body().getId(),response.body().getCompanyID());
                Log.e("comp id fr l",response.body().getCompanyID());
                sendFolderValue.userIDfromLogin=response.body().getId();
                sendFolderValue.companyIDfromLogin=response.body().getCompanyID().toString();
                user=response.body().getId();
                company=response.body().getCompanyID().toString();
                userRole=response.body().getRole();
                userName=response.body().getUsername();
                //sendFolderValue.usernamefromLogin=response.body().getUsername();
               // sendFolderValue.passwordfromLogin=response.body().getPassword();
               // sendFolderValue.companyNameFromLogin=response.body().getUsername();
                if((!response.body().getId().equals("") && !response.body().getCompanyID().equals("")) && response.body().isTf()) {
                    Intent intent = new Intent(getActivity(), MainPage.class);
                    startActivity(intent);
                    getActivity().finish();
                }
//                if(response.body().isTf() ){
//
//                   // controlSession();
//                }


             }


            @Override
            public void onFailure(Call<LoginJson> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
//    public void controlSession(){
//
//        if((sharedPreferences.getString("id",null)!=null && sharedPreferences.getString("email",null)!=null)||
//                (!sharedPreferences.getString("id", "").equals("") && !sharedPreferences.getString("email", "").equals(""))){
//            Intent intent=new Intent(getActivity(),MainPage.class);
//            startActivity(intent);
//            Objects.requireNonNull(getActivity()).finish();
//
//        }
    //}
}
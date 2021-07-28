package com.example.myapplication.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Models.Register;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends Fragment {
        View view;
    private Button SignUpButton;
      EditText signEmail,signUsername,signPassword;
    ArrayList<String>  emailForCompany =new ArrayList<>();
    TextView checkSign;
    ProgressBar progressSignUp;
    public static String email;
    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        define();
        signUser();
        return view;

    }


    public void define(){
        signEmail=view.findViewById(R.id.signEmail);
        signUsername=view.findViewById(R.id.signUsername);
        signPassword=view.findViewById(R.id.signPassword);
        SignUpButton=view.findViewById(R.id.SignUpButton);
        checkSign=view.findViewById(R.id.checkSign);
        progressSignUp=view.findViewById(R.id.progressSignUp);

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

    public void signUser(){
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 email=signEmail.getText().toString();
                String username=signUsername.getText().toString();
                String password=signPassword.getText().toString();
                SharedPreferences sharedPref =getContext().getSharedPreferences("company",Context.MODE_MULTI_PROCESS);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email",email);
                editor.putString("emailJoin",email);
                editor.commit();
                if(emailValidator(email) ) {
//                    checkCorrection.setVisibility(View.INVISIBLE);
//                    loginRequest(email,password);
                    if (passwordValidator(password)) {
                        checkSign.setVisibility(View.INVISIBLE);
                        progressSignUp.setVisibility(View.VISIBLE);
                        sign(email, username, password);
                        Intent intent = new Intent(getActivity(), Company.class);
                        startActivity(intent);
                    } else {
                        checkSign.setVisibility(View.VISIBLE);
                        checkSign.setText("The password must be at least 5 characters and 1 digit");
                    }
                }
                else{
                    checkSign.setVisibility(View.VISIBLE);
                    checkSign.setText("Invalid email address type ");
                }

                // sign(email,username,password);
            //    delete();

                }
            });
        }

     public void delete(){
        signEmail.setText("");
        signUsername.setText("");
        signPassword.setText("");
     }

     public void sign(String signEmail,String signUsername,String signPassword){
       Call<Register> request=ManagerAll.getInstance().register(signEmail,signUsername,signPassword);
         request.enqueue(new Callback<Register>() {
             @Override
             public void onResponse(Call<Register> call, Response<Register> response) {

                 Toast.makeText(view.getContext(), "sucess m", Toast.LENGTH_LONG).show();
                    if(response.body().isTf()){
//                        Intent intent = new Intent(getActivity(), Company.class);
//                        startActivity(intent);
                        Toast.makeText(view.getContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(view.getContext(), response.body().getText(), Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(getActivity(), Company.class);
//                        startActivity(intent);
                        }

             }

             @Override
             public void onFailure(Call<Register> call, Throwable t) {
                 Toast.makeText(view.getContext(),  t.getMessage(), Toast.LENGTH_LONG).show();
                 Toast.makeText(view.getContext(),"fail",Toast.LENGTH_LONG).show();
                 //t.getCause().toString();
             }
         });
     }



}
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
import android.widget.TextView;

import com.example.myapplication.Models.ChangePasswordResponse;
import com.example.myapplication.Models.EditProfileResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragEditProfile extends Fragment {
        View view;
        EditText editProfileName,editProfileCurrentPassword,editProfileNewPassword;
        Button updateMailButton,changePasswordDropDownButton,changePasswordButton;
        TextInputLayout currentPasswordLayout,newPasswordLayout;
        TextView companyIDText;
        Button editBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_edit_profile, container, false);
        define();
        press();
    return view;
        }
    public void define(){
    editProfileName=view.findViewById(R.id.editProfileName);
    editProfileCurrentPassword=view.findViewById(R.id.editProfileCurrentPassword);
    editProfileNewPassword=view.findViewById(R.id.editProfileNewPassword);
    updateMailButton=view.findViewById(R.id.updateMailButton);
    changePasswordDropDownButton=view.findViewById(R.id.changePasswordDropDownButton);
    changePasswordButton=view.findViewById(R.id.changePasswordButton);
     currentPasswordLayout=view.findViewById(R.id.currentPasswordLayout);
     newPasswordLayout=view.findViewById(R.id.newPasswordLayout);
        companyIDText=view.findViewById(R.id.companyIDText);
        companyIDText.setText(fragProfile.company);
        editProfileName.setText(fragProfile.username);
        editBack=view.findViewById(R.id.editBack);

    }
    public void press(){
        updateMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=editProfileName.getText().toString();
                editProfileRes(MainPage.userID,MainPage.companyID,getName,
                        fragProfile.password,fragProfile.password);
//                editProfileRes(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin,getName,
//                        fragProfile.password,fragProfile.password);
               // Snackbar.make(view,sendFolderValue.passwordfromLogin+"",Snackbar.LENGTH_LONG).show();
//                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.listViewLayout,new fragProfile(),"editprof")
//                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .addToBackStack("editprof")
//                        .commit();
                editProfileName.setText(fragProfile.username);
            }
        });
        changePasswordDropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileCurrentPassword.setVisibility(View.VISIBLE);
                currentPasswordLayout.setVisibility(View.VISIBLE);
                newPasswordLayout.setVisibility(View.VISIBLE);
                editProfileNewPassword.setVisibility(View.VISIBLE);
                changePasswordButton.setVisibility(View.VISIBLE);
                changePasswordDropDownButton.setVisibility(View.GONE);
            }
        });
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPass=editProfileCurrentPassword.getText().toString();
                String newPass=editProfileNewPassword.getText().toString();
//                editProfileRes(MainPage.userID,MainPage.companyID,fragProfile.username,
//                        currentPass,newPass);
//                editProfileRes(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin,fragProfile.username,
//                        currentPass,newPass);
                changePass(MainPage.userID,MainPage.companyID,currentPass,newPass);
            }
        });
        editBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragProfile fr=new fragProfile();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragDetail")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });

    }

    public void editProfileRes(String id,String companyID,String currentPassword,String newPassword,String username){
        Call<EditProfileResponse> request= ManagerAll.getInstance().editProfileResponseCall(id,companyID,currentPassword,newPassword,username);
        request.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if(response.body().isTf()){
                    Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                Snackbar.make(view,t.getMessage(),Snackbar.LENGTH_LONG).show();
            }
        });


    }


    public void changePass(String userID,String companyID,String currentPassword,String newPassword){
        Call<ChangePasswordResponse> req=ManagerAll.getInstance().changePasswordResponseCall(userID,companyID,currentPassword,newPassword);
        req.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                Snackbar.make(view,response.body().getText(),Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                Snackbar.make(view,"Check internet connection",Snackbar.LENGTH_LONG).show();
            }
        });


    }
}
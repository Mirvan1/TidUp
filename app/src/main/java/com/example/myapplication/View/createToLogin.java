package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.myapplication.R;

public class createToLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_to_login);

//        login fr=new login();
//        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
//                .replace(R.id.createToLoginLayout,fr,"crToLog")
//                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                .addToBackStack("crToLog")
//                .commit();E
        String sessionId = getIntent().getStringExtra("create");
        if(sessionId.equals("createVal")){
            openCreate();
        }
        else{
            openJoin();
        }

    }
    public void openCreate(){
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.createToLoginLayout, new createCompany());
        ft1.commit();
    }

    public void openJoin(){
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.createToLoginLayout, new joinCompany());
        ft1.commit();
    }
}
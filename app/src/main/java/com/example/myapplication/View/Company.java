package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.myapplication.R;

public class Company extends AppCompatActivity {
    Button createCompanyButton,joinCompanyButton;
    FragmentManager fm1=getSupportFragmentManager();
    FragmentManager fm2=getSupportFragmentManager();
    FragmentTransaction ft1 = fm1.beginTransaction();
    FragmentTransaction ft2 = fm2.beginTransaction();

    FrameLayout frameLayout;
    FrameLayout frameLayoutJoin;
   //Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
   int creatfragClosed=0;
    int joinFrag=0;
    public final String TAG_CREATE="create a company";
    public final String TAG_JOIN="join a company";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        frameLayout=findViewById(R.id.frameLayout);
        frameLayoutJoin=findViewById(R.id.frameLayoutJoin);
        createCompanyButton=findViewById(R.id.createCompanyButton);
        joinCompanyButton=findViewById(R.id.joinCompanyButton);
        createCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //frameLayout.setVisibility(View.VISIBLE);
                //frameLayoutJoin.setVisibility(View.INVISIBLE);
//                creatfragClosed+=1;
//                if(creatfragClosed%2==1){
//                    }
//                else{
//                    ft1.remove(new createCompany());
////                }
//                ft1.add(R.id.frameLayout, new createCompany(),TAG_CREATE);
//                ft1.addToBackStack(TAG_CREATE);
//
//                try{
//                ft1.commit();}
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(),"Buttom already pressed",Toast.LENGTH_LONG).show();
//
//                }
                    String openCreateFrag="createVal";
                Intent crtoLog=new Intent(Company.this,createToLogin.class);

                crtoLog.putExtra("create",openCreateFrag);
                startActivity(crtoLog);
               // createCompanyButton.setVisibility(View.INVISIBLE);
                //joinCompanyButton.setVisibility(View.INVISIBLE);
//                if(fm1!=null){
//
//                }
            }
        });
       // createCompanyButton.setVisibility(View.VISIBLE);
         //joinCompanyButton.setVisibility(View.VISIBLE);
        joinCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //createCompanyButton.setClickable(false);
                //frameLayout.setVisibility(View.INVISIBLE);
                //frameLayoutJoin.setVisibility(View.VISIBLE);
//                 joinFrag+=1;
//                if(creatfragClosed%2==1) {
//
//                    ft2.add(R.id.frameLayoutJoin, new joinCompany(), TAG_JOIN);
//                    ft2.commit();
//                }
//                else{
//                    ft1.remove(new createCompany());
//                }
//                ft2.add(R.id.frameLayoutJoin, new joinCompany(), TAG_JOIN);
//
//                ft2.addToBackStack(TAG_JOIN);
//
//            try{
//                    ft2.commit();}
//                catch (Exception e){
//                    Toast.makeText(getApplicationContext(),"Buttom already pressed",Toast.LENGTH_LONG).show();
//                    ft1.remove(new createCompany());
//                }
                //ft2.commit();
//
//                joinCompany jc=new joinCompany();
//                jc.show(getSupportFragmentManager(),"create");
                String openJoinFrag="joinVal";
                Intent crtoLog=new Intent(Company.this,createToLogin.class);

                crtoLog.putExtra("create",openJoinFrag);
                startActivity(crtoLog);
            }
        });
    }




}
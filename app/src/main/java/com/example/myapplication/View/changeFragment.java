package com.example.myapplication.View;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class changeFragment {
    private Context context;
    public changeFragment(Context context){
        this.context=context;

    }

    public  void  change(Fragment fr) {
        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                .replace(R.id.listViewLayout,fr,"fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

}

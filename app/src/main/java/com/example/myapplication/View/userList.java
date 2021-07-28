package com.example.myapplication.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.View.Adapters.userListAdapter;
import com.example.myapplication.Models.ListUserResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class userList extends Fragment {
    View view;
    RecyclerView userListRecycler;
    userListAdapter userListAdapter;
    List<ListUserResponse> listUser;
    SwipeRefreshLayout swipeRefreshUser;
    Button managerBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_user_list, container, false);
        define();
        listUserRequest(MainPage.userID,MainPage.companyID);
    return view;
    }
    public void define(){
        listUser=new ArrayList<>();
        userListRecycler=view.findViewById(R.id.recyclerViewUsers);
        RecyclerView.LayoutManager manager=new GridLayoutManager(getContext(),1);
        userListRecycler.setLayoutManager(manager);
        swipeRefreshUser=view.findViewById(R.id.swipeRefreshUser);
        managerBack=view.findViewById(R.id.managerBack);
        swipeRefreshUser.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listUserRequest(MainPage.userID,MainPage.companyID);
                swipeRefreshUser.setRefreshing(false);
            }
        });
        managerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragProfile fr=new fragProfile();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragProf")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });
    }
//    public void removeCardView(){
//        RecyclerView.ViewHolder holder=null;
//        holder=userListRecycler.findViewHolderForAdapterPosition(holder.getAdapterPosition());;
//        int position=holder.getAdapterPosition();
//        userListAdapter.deleteView(position);
//        listUserRequest(MainPage.userID,MainPage.companyID);
//
//    }
    public void listUserRequest(String userID,String companyID){
        Call<List<ListUserResponse>> req= ManagerAll.getInstance().listUserResponseCall(userID,companyID);
        req.enqueue(new Callback<List<ListUserResponse>>() {
            @Override
            public void onResponse(Call<List<ListUserResponse>> call, Response<List<ListUserResponse>> response) {
                if(response.body().get(0).isTf()){
                    listUser=response.body();
                    userListAdapter=new userListAdapter(listUser,getContext());
                    userListRecycler.setAdapter(userListAdapter);
//
                }
                else{
                    Snackbar.make(view,"cannot load recyclerview",Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ListUserResponse>> call, Throwable t) {

            }
        });


    }
}
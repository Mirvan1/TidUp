package com.example.myapplication.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.DoManagerResponse;
import com.example.myapplication.Models.ListUserResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userListAdapter extends RecyclerView.Adapter<userListAdapter.ViewHolder> {
  List<ListUserResponse> userLists;
  Context context;
  View view;
  String currentID;

    public userListAdapter(List<ListUserResponse> userList,Context context){
        this.userLists=userList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.listuserlayout, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userListAdapter.ViewHolder holder, int position) {
        holder.listUserName.setText(userLists.get(position).getUsername());
        holder.listUserRole.setText(userLists.get(position).getRole());
        holder.listUserID.setText(userLists.get(position).getUserID());
        holder.listTwoName.setText(userLists.get(position).getUsername().substring(0,2));
       //holder.doManager.setTag(position);
    }
    public void deleteView(int position){
        userLists.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return userLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView listUserName,listUserRole,listUserID,listTwoName;
        Button doManager;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listUserName=itemView.findViewById(R.id.listUserName);
            listUserRole=itemView.findViewById(R.id.listUserRole);
            listUserID=itemView.findViewById(R.id.listUserID);
            listTwoName=itemView.findViewById(R.id.listTwoName);
            doManager=itemView.findViewById(R.id.doManager);
            doManager.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  int position= (int) v.getTag();
                    managerRequest(listUserID.getText().toString());
                   // Snackbar.make(view,listUserID.getText().toString(),Snackbar.LENGTH_LONG).show();
                }
            });

        }

        public void managerRequest(String userID){
            Call<DoManagerResponse> req= ManagerAll.getInstance().managerResponseCall(userID);
            req.enqueue(new Callback<DoManagerResponse>() {
                @Override
                public void onResponse(Call<DoManagerResponse> call, Response<DoManagerResponse> response) {
                    if(response.body().isTf()) {
                       // doManager.setVisibility(View.INVISIBLE);
                       // Snackbar.make(view,"the user is manager",Snackbar.LENGTH_LONG).show();
//                    userList us=new userList();
//                    us.removeCardView();
                        deleteView(getAdapterPosition());
                    }
                }

                @Override
                public void onFailure(Call<DoManagerResponse> call, Throwable t) {

                }
            });
        }
    }
}

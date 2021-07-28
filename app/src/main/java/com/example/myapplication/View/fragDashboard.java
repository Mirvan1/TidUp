package com.example.myapplication.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Controller.ManagerAll;
import com.example.myapplication.Models.DashboardResponse;
import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragDashboard extends Fragment {
    View view;
    TextView totalItem,totalFolder,totalQuantity,totalPrice,totalManagers,totalEmployees;
    CardView cardItem,cardFolder,cardQuantity,cardPrice,cardManagers,cardEmployees;
    ProgressBar progressDashboard;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_dashboard, container, false);
    //    define();
        totalItem=view.findViewById(R.id.totalItem);
        totalFolder=view.findViewById(R.id.totalFolder);
        totalQuantity=view.findViewById(R.id.totalQuantity);
        totalPrice=view.findViewById(R.id.totalPrice);
        totalManagers=view.findViewById(R.id.totalManagers);
        totalEmployees=view.findViewById(R.id.totalEmployees);

        progressDashboard=view.findViewById(R.id.progressDashboard);

        cardItem=view.findViewById(R.id.cardItem);
        cardFolder=view.findViewById(R.id.cardFolder);
        cardQuantity=view.findViewById(R.id.cardQuantity);
        cardPrice=view.findViewById(R.id.cardPrice);
        cardManagers=view.findViewById(R.id.cardManager);
        cardEmployees=view.findViewById(R.id.cardEmployee);
        dashboardRequest(MainPage.companyID);
    return view;
    }
    public void define(){
        totalItem=view.findViewById(R.id.totalItem);
        totalFolder=view.findViewById(R.id.totalFolder);
        totalQuantity=view.findViewById(R.id.totalQuantity);
        totalPrice=view.findViewById(R.id.totalPrice);
        totalManagers=view.findViewById(R.id.totalManagers);
        totalEmployees=view.findViewById(R.id.totalEmployees);

        progressDashboard=view.findViewById(R.id.progressDashboard);

        cardItem=view.findViewById(R.id.cardItem);
        cardFolder=view.findViewById(R.id.cardFolder);
        cardQuantity=view.findViewById(R.id.cardQuantity);
        cardPrice=view.findViewById(R.id.cardPrice);
        cardManagers=view.findViewById(R.id.cardManager);
        cardEmployees=view.findViewById(R.id.cardEmployee);

    }
    public void dashboardRequest(String companyID){
        Call<DashboardResponse> req= ManagerAll.getInstance().dashboardResponseCall(companyID);
        req.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
              progressDashboard.setVisibility(View.INVISIBLE);
                cardItem.setVisibility(View.VISIBLE);
                cardFolder.setVisibility(View.VISIBLE);
                cardQuantity.setVisibility(View.VISIBLE);
                cardPrice.setVisibility(View.VISIBLE);
                cardManagers.setVisibility(View.VISIBLE);
                cardEmployees.setVisibility(View.VISIBLE);

                totalItem.setText(Integer.toString(response.body().getItem()));
                totalFolder.setText(response.body().getFolder()+"");
                totalQuantity.setText(response.body().getQuantity());
                totalPrice.setText(response.body().getPrice().toString());
                totalManagers.setText(Integer.toString(response.body().getManager()));
                totalEmployees.setText(Integer.toString(response.body().getEmployee()));
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {

            }
        });

    }
}
package com.example.myapplication.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.ProductResponse;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class productAdapter extends  RecyclerView.Adapter<productAdapter.ViewHolder>{
    List<ProductResponse> list;
    Context context;
    public productAdapter(List<ProductResponse> list,Context context) {
        this.context = context;
        this.list=list;
    }

//    public productAdapter(List<ProductResponse> list) {
//        this.list = list;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.productviewlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    holder.itemName.setText(list.get(position).getProductFolder().toString());
    sendFolderValue.getFolderNameFordelete=holder.itemName.getText().toString();

//        holder.itemPrice.setText(list.get(position).getProductPrice().toString());
//        holder.itemQuantity.setText(list.get(position).getProductQuantity().toString());
        Picasso.get().load("https://insgur.com/wp-content/uploads/2020/04/black-folder-png-icon.png").into(holder.itemPhoto);
//        holder.itemName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendFolderValue.getFolderNamefromTextView=holder.itemName.getText().toString();
//                Toast.makeText(context,"BASILDI",Toast.LENGTH_LONG).show();
//                changeFragment ch=new changeFragment(context);
//                ch.change(new fragItemList());
////                FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
////                ft2.add(R.id.frameLayoutForItemList, new fragItemList());
////                ft2.commit();
//            }
//        });
    }

    public void deleteView(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
//public void listCopy(ArrayList<String> ls){
//  //List<String>ls=new ArrayList<>();
//  for (int position=0;position<list.size();position++){
//  ls.add(list.get(position).getProductFolder());
//
//  }
//    Log.i("adapterdaki list: ",list+"");
//}

//list.get(position).getProductPhoto().toString()
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<ProductResponse> filteredList){
        list=filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        ImageView itemPhoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemPrice=itemView.findViewById(R.id.itemPrice);
//            itemQuantity=itemView.findViewById(R.id.itemQuantity);
            itemName=itemView.findViewById(R.id.itemName);
            itemPhoto=itemView.findViewById(R.id.itemPhoto);
            getFolderName(itemName.getText().toString());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendFolderValue.getFolderNamefromTextView=itemName.getText().toString();
                    fragItemList fr=new fragItemList();
//                 FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                ft2.add(R.id.listViewLayout, new fragItemList(),"fragList");
//                ft2.commit();
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.listViewLayout,fr,"fragItem")
                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack("fragItem")
                            .commit();
                }
            });


        }
        public void getFolderName(String folderName){
            sendFolderValue.getFolderNamefromTextView=folderName;
        }
    }
}
package com.example.myapplication.View.Adapters;

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

import com.example.myapplication.Models.SearchItemResponse;
import com.example.myapplication.R;
import com.example.myapplication.View.fragItemDetail;
import com.example.myapplication.View.sendFolderValue;
import com.squareup.picasso.Picasso;

import java.util.List;

public class searchListAdapter extends RecyclerView.Adapter<searchListAdapter.ViewHolder> {
    List<SearchItemResponse> searchlist; // helelik;
    Context context;
    public String layoutCh;
    View view;
    public searchListAdapter(List<SearchItemResponse> searchlist,Context context) {
        this.context=context;
        this.searchlist=searchlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.itemlistlayout, parent, false);

        return new searchListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //layoutCh=itemlist.get(position).getProductNameList();

        if (!searchlist.get(position).getProductNameList().equals("null")) {
            holder.itemListName.setText(searchlist.get(position).getProductNameList());
//            holder.itemListName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //sendFolderValue.detailClickName= holder.itemListName.getText().toString();
//                    sendFolderValue.detailName = itemlist.get(position).getProductNameList();
//                    sendFolderValue.detailPrice = itemlist.get(position).getProductPriceList();
//                    sendFolderValue.detailDate = itemlist.get(position).getProductDateList();
//                    sendFolderValue.detailQuantity = itemlist.get(position).getProductQuantityList();
//                    sendFolderValue.detailBarcode = itemlist.get(position).getProductBarcodeList();
//                    sendFolderValue.detailNote = itemlist.get(position).getProductNoteList();
//                    sendFolderValue.detailPhoto = itemlist.get(position).getProductPhotoList();
//                    sendFolderValue.detailUserID=itemlist.get(position).getUserIDList();
//                    sendFolderValue.detailProductID=itemlist.get(position).getProductIDList();
//                    changeFragment ch = new changeFragment(context);
//                    ch.change(new fragItemDetail());
//                }
//            });
            sendFolderValue.detailClickName= holder.itemListName.getText().toString();
            sendFolderValue.detailName = searchlist.get(position).getProductNameList();
            sendFolderValue.detailPrice = searchlist.get(position).getProductPriceList();
            sendFolderValue.detailDate = searchlist.get(position).getProductDateList();
            sendFolderValue.detailQuantity = searchlist.get(position).getProductQuantityList();
            sendFolderValue.detailBarcode = searchlist.get(position).getProductBarcodeList();
            sendFolderValue.detailNote = searchlist.get(position).getProductNoteList();
            sendFolderValue.detailPhoto = searchlist.get(position).getProductPhotoList();
            sendFolderValue.detailUserID=searchlist.get(position).getUserIDList();
            sendFolderValue.detailProductID=searchlist.get(position).getProductIDList();
            holder.itemListPrice.setText(searchlist.get(position).getProductPriceList());
            holder.itemListQuantity.setText(searchlist.get(position).getProductQuantityList());
            Picasso.get().load(searchlist.get(position).getProductPhotoList()).into(holder.itemListPhoto);

        }
    }

    @Override
    public int getItemCount() {
        return searchlist.size();
    }
    public void dataChanged(){

        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemListName,itemListPrice,itemListQuantity;
        ImageView itemListPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemListName=itemView.findViewById(R.id.itemListName);
            itemListPrice=itemView.findViewById(R.id.itemListPrice);
            itemListQuantity=itemView.findViewById(R.id.itemListQuantity);
            itemListPhoto=itemView.findViewById(R.id.itemListPhoto);
            sendFolderValue.emptyName=itemListName.getText().toString();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragItemDetail fr=new fragItemDetail();
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.listViewLayout,fr,"fragDetail")
                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack("fragItem")
                            .commit();

                }
            });
        }


   }
}

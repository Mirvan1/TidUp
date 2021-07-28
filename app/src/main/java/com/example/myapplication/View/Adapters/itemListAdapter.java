package com.example.myapplication.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Models.ItemListResponse;
import com.example.myapplication.R;
import com.example.myapplication.View.fragItemList;
import com.example.myapplication.View.sendFolderValue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class itemListAdapter extends RecyclerView.Adapter<itemListAdapter.ViewHolder> {

    List<ItemListResponse> itemlist; // helelik;
    Context context;
    public String layoutCh;
    View view;
    OnItemClickListener onItemClickListener;
public interface OnItemClickListener{
    void onItemClick(int position);
}
public void setOnItemClickListener(OnItemClickListener listener){
    onItemClickListener=listener;
}

    public itemListAdapter(List<ItemListResponse> itemlist,Context context) {
        this.context=context;
        this.itemlist=itemlist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            view = LayoutInflater.from(context).inflate(R.layout.itemlistlayout, parent, false);

        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         //layoutCh=itemlist.get(position).getProductNameList();
//        if(holder.itemListName.getText().equals("sdsfh")){
//                    holder.itemListCard.setVisibility(View.INVISIBLE);
//                itemlist.remove(position);
//        }
//        else{
//            holder.itemListCard.setVisibility(View.VISIBLE);
//        }
      // if (!itemlist.get(position).getProductNameList().equals("null")) {
           holder.itemListRelative.setClickable(true);
            holder.itemListCard.setVisibility(View.VISIBLE);
            holder.itemListRelative.setVisibility(View.VISIBLE);
            holder.itemListName.setText(itemlist.get(position).getProductNameList());

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
            sendFolderValue.detailClickName=  holder.itemListName.getText().toString();
            holder.itemListPrice.setText(itemlist.get(position).getProductPriceList());
            holder.itemListQuantity.setText(itemlist.get(position).getProductQuantityList());
            Picasso.get().load(itemlist.get(position).getProductPhotoList()).into(holder.itemListPhoto);

//
//            sendFolderValue.detailClickName= holder.itemListName.getText().toString();
//                    sendFolderValue.detailName = itemlist.get(position).getProductNameList();
//                    sendFolderValue.detailPrice = itemlist.get(position).getProductPriceList();
//                    sendFolderValue.detailDate = itemlist.get(position).getProductDateList();
//                    sendFolderValue.detailQuantity = itemlist.get(position).getProductQuantityList();
//                    sendFolderValue.detailBarcode = itemlist.get(position).getProductBarcodeList();
//                    sendFolderValue.detailNote = itemlist.get(position).getProductNoteList();
//                    sendFolderValue.detailPhoto = itemlist.get(position).getProductPhotoList();
//                    sendFolderValue.detailUserID=itemlist.get(position).getUserIDList();
//                    sendFolderValue.detailProductID=itemlist.get(position).getProductIDList();
//            holder.itemListPrice.setText(itemlist.get(position).getProductPriceList());
//            holder.itemListQuantity.setText(itemlist.get(position).getProductQuantityList());
//            Picasso.get().load(itemlist.get(position).getProductPhotoList()).into(holder.itemListPhoto);

        //}


//        holder.itemListCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragItemDetail fr=new fragItemDetail();
//                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.listViewLayout,fr,"fragDetail")
//                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .addToBackStack("fragItem")
//                        .commit();
//            }
//        });
    }

    public void deleteView(int position){
        itemlist.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
//public void deleteRedundant(int position)
//{
//
//    if(holder.itemListName.getText().equals("sdsfh")){
//        //  holder.itemListCard.setVisibility(View.INVISIBLE);
//        itemlist.remove(position);
//    }
//}
@Override
    public int getItemCount() {
        return itemlist.size();
    }
    public void filterList(ArrayList<ItemListResponse> filteredList){
        itemlist=filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemListName,itemListPrice,itemListQuantity;
        ImageView itemListPhoto;
        public CardView itemListCard;
        RelativeLayout itemListRelative;
        public ViewHolder(@NonNull View itemView,OnItemClickListener listener)  {
            super(itemView);
            itemListName=itemView.findViewById(R.id.itemListName);
            itemListPrice=itemView.findViewById(R.id.itemListPrice);
            itemListQuantity=itemView.findViewById(R.id.itemListQuantity);
            itemListCard=itemView.findViewById(R.id.itemListCard);
            itemListPhoto=itemView.findViewById(R.id.itemListPhoto);
            itemListRelative=itemView.findViewById(R.id.itemListRelative);
            sendFolderValue.emptyName=itemListName.getText().toString();

//            itemListCard.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//                fragItemDetail fr=new fragItemDetail();
//            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.listViewLayout,fr,"fragDetail")
//                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                    .addToBackStack("fragItem")
//                    .commit();
//
//        }
//    });

itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    if (listener!=null){
        int position=getAdapterPosition();
        if(position!=RecyclerView.NO_POSITION){
            listener.onItemClick(position);
        }
    }
    }
});


        }
    }

}
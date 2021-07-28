package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.View.Adapters.itemListAdapter;

import com.example.myapplication.Models.ItemListResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.View.Adapters.itemListAdapter.*;

public class fragItemList extends Fragment  {
    View view;
    public RecyclerView itemListRecycler;
    itemListAdapter itemListAdapter;
     List<ItemListResponse> itemListusingProductResponse;
    List<ItemListResponse> filterNull;
    String folderName;
    public TextView empty;
    public ImageView emptyView;
    boolean checkList;
    SwipeRefreshLayout swipeRefreshLayoutItem;
    ProgressBar progressList;
    Button listBack;
    private List<ItemListResponse> ItemListResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        folderName = getArguments().ge-itString("folderName");

            // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_frag_item_list, container, false);

        define();
        getItemRequest(sendFolderValue.getFolderNamefromTextView,MainPage.companyID);
//      getItemRequest(sendFolderValue.getFolderNamefromTextView,sendFolderValue.companyIDfromLogin);

        swipeRefreshLayoutItem=view.findViewById(R.id.swipeRefreshLayoutItem);
        swipeRefreshLayoutItem.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
             // itemListAdapter=new itemListAdapter(ItemListResponse,getContext());
                getItemRequest(sendFolderValue.getFolderNamefromTextView,MainPage.companyID);
                //getItemRequest(sendFolderValue.getFolderNamefromTextView,sendFolderValue.companyIDfromLogin);
                Snackbar.make(view,sendFolderValue.getFolderNamefromTextView+" "+MainPage.companyID,Snackbar.LENGTH_LONG).show();
                swipeRefreshLayoutItem.setRefreshing(false);
            }
        });

//
//        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(itemListRecycler);

        listBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                //Fragment menuFragment_by_tag = fragmentManager.findFragmentByTag("fragItem");
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("fragItem");
//                if(fragment != null)
//                    getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).remove(fragment).commit();
//                OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//                    @Override
//                    public void handleOnBackPressed() {
//                        // Handle the back button event
//                        Snackbar.make(view,"back",Snackbar.LENGTH_LONG).show();
//                    }
//                };
//                requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
                fragmentMainPage fr=new fragmentMainPage();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragDetail")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });
    return view;
    }
    public void define(){
        itemListusingProductResponse=new ArrayList<>();
        itemListRecycler=view.findViewById(R.id.itemListRecycler);
        RecyclerView.LayoutManager manage=new GridLayoutManager(getContext(),2);
        listBack=view.findViewById(R.id.listBack);
        itemListRecycler.setLayoutManager(manage);
        empty=view.findViewById(R.id.empty);
        emptyView=view.findViewById(R.id.emptyView);
        progressList=view.findViewById(R.id.progressList);
        itemListRecycler.setVisibility(View.INVISIBLE);

    }

    public void getItemRequest(String folder,String companyID){
        Call<List<ItemListResponse>> itemReq= ManagerAll.getInstance().itemfromFolder(folder,companyID);
        itemReq.enqueue(new Callback<List<ItemListResponse>>() {
            @Override
            public void onResponse(Call<List<ItemListResponse>> call, Response<List<ItemListResponse>> response) {
              //checkList  = response.body().get(0).getProductNameList().equals("null");
                progressList.setVisibility(View.INVISIBLE);
                empty.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),response.body().get(0).getProductNameList(),Toast.LENGTH_LONG).show();
                if(response.body().get(0).isTf()) {
                   // if (response.body().size() > 0 ) {

                                                //  if(response.body().get(1).getProductNameList().isEmpty()){
                        Snackbar.make(view, response.body().get(0).getProductNameList(), Snackbar.LENGTH_LONG).show();


                        // if(!response.body().get(1).getProductNameList().isEmpty()) {
                        empty.setVisibility(View.INVISIBLE);
                        emptyView.setVisibility(View.INVISIBLE);
                        itemListusingProductResponse=response.body();


                        itemListAdapter = new itemListAdapter(itemListusingProductResponse,getContext());
                     //   Collection<String> filterFolder=Collections2



                        itemListRecycler.setAdapter(itemListAdapter);
                        itemListRecycler.setVisibility(View.VISIBLE);
                        itemListAdapter.notifyDataSetChanged();
                        itemListAdapter.setOnItemClickListener(new OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                sendFolderValue.detailName = itemListusingProductResponse.get(position).getProductNameList();
                                sendFolderValue.detailPrice = itemListusingProductResponse.get(position).getProductPriceList();
                                sendFolderValue.detailDate = itemListusingProductResponse.get(position).getProductDateList();
                                sendFolderValue.detailQuantity = itemListusingProductResponse.get(position).getProductQuantityList();
                                sendFolderValue.detailBarcode = itemListusingProductResponse.get(position).getProductBarcodeList();
                                sendFolderValue.detailNote = itemListusingProductResponse.get(position).getProductNoteList();
                                sendFolderValue.detailPhoto = itemListusingProductResponse.get(position).getProductPhotoList();
                                sendFolderValue.detailUserID = itemListusingProductResponse.get(position).getUserIDList();
                                sendFolderValue.detailProductID = itemListusingProductResponse.get(position).getProductIDList();

                                fragItemDetail fr = new fragItemDetail();
                                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.listViewLayout, fr, "fragDetail")
                                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                        .addToBackStack("fragItem")
                                        .commit();
                                // itemListusingProductResponse.get(position).;
                            }
                        });
                        //Toast.makeText(view.getContext(),"There is "+itemListusingProductResponse.size()+ " product",Toast.LENGTH_LONG).show();
                        //    sendFolderValue.detailName=response.body().get(0).getProductNameList();
                        //       sendFolderValue.detailPrice=response.body().get(response.body().indexOf("asdf")).getProductPriceList();
                        //     sendFolderValue.detailDate=response.body().get(response.body().indexOf("asdfs")).getProductDateList();
//                    sendFolderValue.detailQuantity=response.body().get(response.body().indexOf(sendFolderValue.detailClickName)).getProductQuantityList();
//                    sendFolderValue.detailBarcode=response.body().get(response.body().indexOf(sendFolderValue.detailClickName)).getProductBarcodeList();
//                    sendFolderValue.detailNote=response.body().get(response.body().indexOf(sendFolderValue.detailClickName)).getProductNoteList();
//                    Toast.makeText(view.getContext(),"There is "+sendFolderValue.detailName,Toast.LENGTH_LONG).show();
                        //              Toast.makeText(view.getContext(),"There is "+response.body().indexOf(sendFolderValue.detailClickName)+ " product",Toast.LENGTH_LONG).show();


//                    if ( response.body().get(0).getProductNameList().isEmpty() || sendFolderValue.emptyName.equals("") ){
//                        Toast.makeText(view.getContext(),"There is no product",Toast.LENGTH_LONG).show();
//                        Toast.makeText(view.getContext(),"BOS "+sendFolderValue.emptyName,Toast.LENGTH_LONG).show();
//
//                        itemListRecycler.setVisibility(View.GONE);
//                        empty.setVisibility(View.VISIBLE);}
                    } else {
                        //bura item yox fragmenti ele eger isdese
                        //      if (sendFolderValue.emptyName.equals("sss") || sendFolderValue.emptyName.equals("") ){  }
                        Toast.makeText(view.getContext(), "There is no product", Toast.LENGTH_LONG).show();
                        Toast.makeText(view.getContext(), "BOS " + sendFolderValue.emptyName, Toast.LENGTH_LONG).show();

                        Snackbar.make(view, response.body().get(0).getTx(), Snackbar.LENGTH_LONG).show();
                        itemListRecycler.setVisibility(View.GONE);
                        empty.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.VISIBLE);
                        Snackbar.make(view, "response is false", Snackbar.LENGTH_LONG).show();
                    }

            }

            @Override
            public void onFailure(Call<List<ItemListResponse>> call, Throwable t) {
//                Log.i("item list",t.getCause().getMessage());
                //              Log.i("item list 2 ",t.getMessage());
//                Toast.makeText(view.getContext(),t.getCause().toString(),Toast.LENGTH_LONG).show();
                Log.i("item list","fail");
                Toast.makeText(view.getContext(),"ERRORERROR",Toast.LENGTH_LONG).show();
                Snackbar.make(view,"fail",Snackbar.LENGTH_LONG).show();
            }
        });
    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position=viewHolder.getAdapterPosition();
//            viewHolder=recyclerViewProduct.findViewHolderForAdapterPosition(position);
//            View recView=viewHolder.itemView;
//            TextView folderName=recView.findViewById(R.id.itemName);
//            String folder=folderName.getText().toString();
            String productID=itemListusingProductResponse.get(position).getProductIDList();
            Log.i("product id",productID);
            //deletedialogText
            switch (direction){
                case ItemTouchHelper.LEFT:
                    LayoutInflater inflater=getLayoutInflater();
                    View view = inflater.inflate(R.layout.deletedialog,null);
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Delete Dialog");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                fragItemDetail fi=new fragItemDetail();
                            fi.deleteItemCall(productID,MainPage.userID);
                               // fi.deleteItemCall(productID,sendFolderValue.userIDfromLogin);
                            //deleteFolderClass df=new deleteFolderClass();
                            //df.deleteFolderM(sendFolderValue.userIDfromLogin,folder);
                            Snackbar.make(getView()," sil",Snackbar.LENGTH_LONG).show();

                           itemListAdapter.deleteView(position);

                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                           // Snackbar.make(recyclerViewProduct,"prees no",Snackbar.LENGTH_LONG).show();
                        }
                    });
                    alertDialog.setView(view);
                    alertDialog.show();
                    //Toast.makeText(getContext(),"left to right",Toast.LENGTH_LONG).show();
                    break;
                case ItemTouchHelper.RIGHT:

                    break;

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent))
                    .addSwipeLeftActionIcon(R.drawable.ic_bar_code)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorAccent))
                    .addSwipeRightActionIcon(R.drawable.ic_bar_code)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
public void visibleView(){
    empty.setVisibility(View.VISIBLE);
    emptyView.setVisibility(View.VISIBLE);
    itemListRecycler.setVisibility(View.INVISIBLE);
}
}

package com.example.myapplication.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.View.Adapters.searchListAdapter;
import com.example.myapplication.Models.ProductResponse;
import com.example.myapplication.Models.SearchItemResponse;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.R.id.bottom_sheet;

public class fragmentMainPage extends Fragment{

    View view;
    private RecyclerView recyclerViewProduct;
    private productAdapter getsAdapter;
    private searchListAdapter searchListAdapter;
     List<ProductResponse>  productList;
    Button searchImage;
     List <SearchItemResponse> searchItemResponses;
    String folderNameSendItemList;
        FloatingActionButton addItemFloatButton;
        SwipeRefreshLayout swipeRefreshLayout;
BottomSheetDialog bottomSheetDialog;
   ArrayList<String> ls=new ArrayList<>();
   EditText editSearchText;
   Button findBarcodeButtonPress;
    TextView noitem;
    ImageView noItemView;
    String productFolder="";
    TextInputLayout textLayout;
//    private SharedPreferences sharedPreferences;
//    private GetSharedPreferences preferences;
    ProgressBar progressMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main_page, container, false);
        define();
        searchI();
//        controlSession();
//        getsAdapter=new productAdapter(productList,getContext());
//        recyclerViewProduct.setAdapter(getsAdapter);
                Log.e("cr user id frm",sendFolderValue.userIDfromLogin);
                Log.e("cr company id frm",sendFolderValue.companyIDfromLogin);
        Log.e("crs user id frm",MainPage.userID);
        Log.e("crs company id frm",MainPage.companyID);
                getItemRequest(MainPage.userID, MainPage.companyID);

        //getItemRequest(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              Toast.makeText(view.getContext(),"BASILDI",Toast.LENGTH_LONG).show();
//            }
//        });

        swipeRefreshLayout=view.findViewById(R.id.swipeRefreshLayou);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               // getItemRequest(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin);
                getItemRequest(MainPage.userID, MainPage.companyID);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewProduct);
    return view;
    }
    public void define(){
        searchImage=view.findViewById(R.id.searchImage);
        editSearchText=view.findViewById(R.id.editSearchText);
        findBarcodeButtonPress=view.findViewById(R.id.findBarcodeButtonPress);
        textLayout=view.findViewById(R.id.textLayout);
        progressMain=view.findViewById(R.id.progressMain);
        productList=new ArrayList<>();
        recyclerViewProduct=view.findViewById(R.id.recyclerViewProduct);
        sendFolderValue.adSp.clear();
//        RecyclerView.LayoutManager manage=new GridLayoutManager(getContext(),1);
//        recyclerViewProduct.setLayoutManager(manage);
        noitem=view.findViewById(R.id.noitem);
        noItemView=view.findViewById(R.id.noitemView);
        //addFolderFloatButton=view.findViewById(R.id.addFolderFloatButton);
        addItemFloatButton=view.findViewById(R.id.addItemFloatButton);
//        preferences=new GetSharedPreferences(getContext());
//        sharedPreferences=preferences.getSession();
//        addFolderFloatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                addFolder();
//            }
//        });
        addItemFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog=new BottomSheetDialog(getActivity(),R.style.BottomSheetTheme);
                View sheetView=LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout,
                        v.findViewById(bottom_sheet));

//                 sheetView.findViewById(R.id.bottomAddFolder).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        addFolder();
//                        Snackbar.make(v,"Folder",Snackbar.LENGTH_LONG).show();
//                        bottomSheetDialog.dismiss();
//                    }
//                });
                sheetView.findViewById(R.id.bottomAddFolderLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFolder();
                        Snackbar.make(v,"Folder",Snackbar.LENGTH_LONG).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                 //searchImage.setVisibility(View.VISIBLE);
                sheetView.findViewById(R.id.bottomAddItemLayout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,new fragAddItem(getContext()),"fragment")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack("fragAdd")
                        .commit();
                        Snackbar.make(v,"Item",Snackbar.LENGTH_LONG).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                sheetView.findViewById(R.id.bottomSheetCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();

            }
        });
     //  this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//        editSearchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if(hasFocus){
//                    InputMethodManager imm = (InputMethodManager) getSystemService(getContext(),Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(editSearchText.getWindowToken(), 0);
//                }
//            }
//        });
        editSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                  //  getItemRequest(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin);
                    getItemRequest(MainPage.userID, MainPage.companyID);
                    recyclerViewProduct.setVisibility(View.VISIBLE);
                    noitem.setVisibility(View.INVISIBLE);
                    noItemView.setVisibility(View.INVISIBLE);
                }
                else {
                    filter(s.toString(),MainPage.companyID);
//                    filter(s.toString(), sendFolderValue.companyIDfromLogin);
                }
                }
        });


        findBarcodeButtonPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),BarcodeScanFind.class));
            }
        });



    }
//public void controlSession(){
//
//    if(sharedPreferences.getString("id",null)==null && sharedPreferences.getString("email",null)==null){
//        Intent intent=new Intent(getActivity(),MainActivity.class);
//        startActivity(intent);
//
//
//    }
//}

    private void filter(String searchText,String companyID){
    Call<List<SearchItemResponse>> req=ManagerAll.getInstance().searchItemResponseCall(searchText,companyID);
   req.enqueue(new Callback<List<SearchItemResponse>>() {
       @Override
       public void onResponse(Call<List<SearchItemResponse>> call, Response<List<SearchItemResponse>> response) {
           RecyclerView.LayoutManager manage=new GridLayoutManager(getContext(),2);
           recyclerViewProduct.setLayoutManager(manage);
           if (response.body().get(0).isTf()){
               searchItemResponses=response.body();
               searchListAdapter=new searchListAdapter(searchItemResponses,getContext());
               recyclerViewProduct.setAdapter(searchListAdapter);
               recyclerViewProduct.setVisibility(View.VISIBLE);
               noitem.setVisibility(View.INVISIBLE);
               noItemView.setVisibility(View.INVISIBLE);
           }
           if(response.body().get(0).getText().equals("not found")){
           //    searchListAdapter.notifyDataSetChanged();
               recyclerViewProduct.setVisibility(View.INVISIBLE);
               noitem.setVisibility(View.VISIBLE);
               noItemView.setVisibility(View.VISIBLE);
               Snackbar.make(view,"no item found ",Snackbar.LENGTH_LONG).show();
           }
       }

       @Override
       public void onFailure(Call<List<SearchItemResponse>> call, Throwable t) {

       }
   });
    }

    public void getItemRequest(String userID,String companyID){

        Call<List<ProductResponse>>  itemReq= ManagerAll.getInstance().item(userID,companyID);
        itemReq.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                progressMain.setVisibility(View.INVISIBLE);
//                Log.i("item list",response.body().toString());
//                Toast.makeText(view.getContext(),response.body().toString(),Toast.LENGTH_LONG).show();
               // sendFolderValue.folderNameClass=response.body().get(response.body().indexOf(sendFolderValue.getFolderNamefromTextView)).getFolder().toString();

                //Toast.makeText(view.getContext(),"gonderilen folder "+sendFolderValue.getFolderNamefromTextView,Toast.LENGTH_LONG).show();
              //  Toast.makeText(view.getContext(),response.body().size(),Toast.LENGTH_LONG).show();
               // sendFolderValue.folderNameClass=folderNameSendItemList;

                RecyclerView.LayoutManager manage=new GridLayoutManager(getContext(),1);
                recyclerViewProduct.setLayoutManager(manage);
                if(response.body().get(0).isTf())
                {
                    productList=response.body();
                    getsAdapter=new productAdapter(productList,getContext());
                    recyclerViewProduct.setAdapter(getsAdapter);
                    for (int i = 0; i < response.body().size(); i++) {
                        if(i==0){
                             sendFolderValue.adSp.add(response.body().get(i).getProductFolder());
                        }
                        else if(!response.body().get(i-1).getProductFolder().toString().equals(response.body().get(i).getProductFolder().toString())){
                        sendFolderValue.adSp.add(response.body().get(i).getProductFolder());
                        }
                    }
                    Toast.makeText(view.getContext(),"There is "+ls+ " product",Toast.LENGTH_LONG).show();
                }
                else{
                        //bura item yox fragmenti ele eger isdese
                    Toast.makeText(view.getContext(),"There is no product",Toast.LENGTH_LONG).show();
//                    Snackbar.make(view,response.body().get(0).getUsername().toString(),Snackbar.LENGTH_LONG).show();
                }
//                Toast.makeText(view.getContext(),"AXRINCI ELEEMNT "+response.body().get(response.body().size()-1).getProductFolder().toString(),Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
//                Log.i("item list",t.getCause().getMessage());
  //              Log.i("item list 2 ",t.getMessage());
//                Toast.makeText(view.getContext(),t.getCause().toString(),Toast.LENGTH_LONG).show();
                Log.i("item list","fail");
                Toast.makeText(view.getContext(),"ERRORERROR",Toast.LENGTH_LONG).show();
            }
        });


    }



    public  void addFolder(){

        LayoutInflater inflater=getLayoutInflater();
         View view = inflater.inflate(R.layout.dialoglayout,null);
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("ADD FOLDER");
        alertDialog.setCancelable(false);
         EditText etComments = view.findViewById(R.id.folderadddialog);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 productFolder=etComments.getText().toString();
                if(!productFolder.equals("")) {
                    addFolderClass aa = new addFolderClass();
                    aa.addFolderResponse(MainPage.userID, MainPage.companyID, productFolder);
                    // aa.addFolderResponse(sendFolderValue.userIDfromLogin,sendFolderValue.companyIDfromLogin,productFolder);
                    fragmentMainPage a=new fragmentMainPage();
                    ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.listViewLayout,a,"fragment")
                            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                }
                else{
                    Snackbar.make(view,"Please fill the blank",Snackbar.LENGTH_LONG).show();
                }
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();

    }


    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position=viewHolder.getAdapterPosition();
            viewHolder=recyclerViewProduct.findViewHolderForAdapterPosition(position);
            View recView=viewHolder.itemView;
            TextView folderName=recView.findViewById(R.id.itemName);
            String folder=folderName.getText().toString();
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

                           deleteFolderClass df=new deleteFolderClass();
                            df.deleteFolderM(MainPage.userID,folder);
//                           df.deleteFolderM(sendFolderValue.userIDfromLogin,folder);
                           Snackbar.make(recyclerViewProduct,df.deleteMessage+" aa-",Snackbar.LENGTH_LONG).show();

                            getsAdapter.deleteView(position);

                           }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                            Snackbar.make(recyclerViewProduct,"prees no",Snackbar.LENGTH_LONG).show();
                        }
                    });
                    alertDialog.setView(view);
                    alertDialog.show();
                    //Toast.makeText(getContext(),"left to right",Toast.LENGTH_LONG).show();
                    break;
                case ItemTouchHelper.RIGHT:
                    LayoutInflater inflater2=getLayoutInflater();
                    View view2 = inflater2.inflate(R.layout.update_dialog,null);
                    AlertDialog alertDialog2 = new AlertDialog.Builder(getContext()).create();
                    alertDialog2.setTitle("Update Folder");
                    alertDialog2.setCancelable(true);
                    EditText updatedFolder = view2.findViewById(R.id.updateFolderDialog);
                    alertDialog2.setButton(AlertDialog.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          String newFolder= updatedFolder.getText().toString();
                                updateFolderClass uf=new updateFolderClass();
                                uf.updateFolderRequest(MainPage.companyID,MainPage.userID,newFolder,folder);
                                getsAdapter.notifyDataSetChanged();
                                getsAdapter.notifyItemChanged(position);
//                            deleteFolderClass df=new deleteFolderClass();
//                            df.deleteFolderM(MainPage.userID,folder);
////                           df.deleteFolderM(sendFolderValue.userIDfromLogin,folder);
//                            Snackbar.make(recyclerViewProduct,df.deleteMessage+" aa-",Snackbar.LENGTH_LONG).show();
//
//                            getsAdapter.deleteView(position);

                        }
                    });

                    alertDialog2.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog2.dismiss();
                            Snackbar.make(recyclerViewProduct,"prees no",Snackbar.LENGTH_LONG).show();
                        }
                    });
                    alertDialog2.setView(view2);
                    alertDialog2.show();
                    break;

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(),R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_24)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(),R.color.green))
                    .addSwipeRightActionIcon(R.drawable.ic_edit)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


    public  void searchI(){
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();
                     //           view.animate().alpha(0.0f);

                               // textLayout.setVisibility(View.VISIBLE);
                                //textLayout.animate().alpha(0.0f);
                editSearchText.requestFocus();
                if(editSearchText.requestFocus()) {
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                    Toast.makeText(getContext(),"clicked",Toast.LENGTH_LONG).show();

                }
                //searchImage.setVisibility(View.INVISIBLE);

            }
        });

    }
}
package com.example.myapplication.Controller;


import com.example.myapplication.Models.AddFolderResponse;
import com.example.myapplication.Models.AddItemJson;
import com.example.myapplication.Models.ChangePasswordResponse;
import com.example.myapplication.Models.CheckRoleResponse;
import com.example.myapplication.Models.DashboardResponse;
import com.example.myapplication.Models.DeleteFolderResponse;
import com.example.myapplication.Models.DeleteItemResponse;
import com.example.myapplication.Models.DoManagerResponse;
import com.example.myapplication.Models.EditProfileResponse;
import com.example.myapplication.Models.ExportDataResponse;
import com.example.myapplication.Models.ExportEmployeeResponse;
import com.example.myapplication.Models.FindBarcodeResponse;
import com.example.myapplication.Models.GetShiftResponse;
import com.example.myapplication.Models.InviteResponse;
import com.example.myapplication.Models.ItemListResponse;
import com.example.myapplication.Models.ListUserResponse;
import com.example.myapplication.Models.LoginJson;
import com.example.myapplication.Models.ProductResponse;
import com.example.myapplication.Models.Register;
import com.example.myapplication.Models.SearchItemResponse;
import com.example.myapplication.Models.UpdateFolderResponse;
import com.example.myapplication.Models.UpdateItemResponse;
import com.example.myapplication.Models.UserProfile;
import com.example.myapplication.Models.UserShiftResponse;
import com.example.myapplication.Models.VerifyAccountResponse;
import com.example.myapplication.Models.companyCreateJson;
import com.example.myapplication.Models.companyJoinJson;

import java.util.List;

import retrofit2.Call;

public class ManagerAll  extends BaseManager{

    private static ManagerAll ourInstance = new ManagerAll();

    public  static synchronized ManagerAll getInstance()
    {
        return  ourInstance;
    }

    public Call<Register> register(String email , String username, String password)
    {
        Call<Register> x = getRestApi().registerUser(email,username,password);
        return  x ;
    }

    public Call<companyCreateJson> create(String companyName,String email )
    {
        Call<companyCreateJson> x = getRestApi().createCompanyUser(companyName,email);
        return  x ;
    }


    public Call<companyJoinJson> join(String email , String companyID)
    {
        Call<companyJoinJson> x = getRestApi().joinCompanyUser(email,companyID);
        return  x ;
    }


    public Call<LoginJson> login(String email , String password)
    {
        Call<LoginJson> x = getRestApi().loginUser(email,password);
        return  x ;
    }


    //

    public Call<List<ProductResponse>> item(String userID,String companyID )
    {
        Call<List<ProductResponse>> x = getRestApi().item(userID,companyID);
        return  x ;
    }


    public Call<List<ItemListResponse>> itemfromFolder(String folder,String companyID )
    {
        Call<List<ItemListResponse>> x = getRestApi().itemListFromFolder(folder,companyID);
        return  x ;
    }


    public Call<AddFolderResponse> addFolderResponseCall(String userID, String companyID,String productFolder )
    {
        Call<AddFolderResponse> x = getRestApi().addFolder(userID,companyID,productFolder);
        return  x ;
    }


    public Call<AddItemJson> addItemJsonCall(String userID, String companyID, String companyName,String username,String productName,
                                             String productPrice,String productQuantity,String productDate,String productBarcode,String productNote,
                                             String productPhoto,String productFolder )
    {
        Call<AddItemJson> x = getRestApi().addItem(userID,companyID,companyName,username,productName,productPrice,productQuantity,
                productDate,productBarcode,productNote,productPhoto, productFolder);
        return  x ;
    }


    public Call<DeleteFolderResponse> deleteFolderResponseCall(String userID, String productFolder )
    {
        Call<DeleteFolderResponse> x = getRestApi().deleteFolder(userID,productFolder);
        return  x ;
    }


    public Call<DeleteItemResponse> deleteItemResponseCall(String userID, String productFolder )
    {
        Call<DeleteItemResponse> x = getRestApi().deleteItem(userID,productFolder);
        return  x ;
    }


    public Call<UpdateItemResponse> updateItemResponseCall(String userID, String productID,String productName, String productPrice,
                                                           String productQuantity, String productDate, String productBarcode,
                                                           String productNote)
    {
        Call<UpdateItemResponse> x = getRestApi().updateItem(userID,productID,productName,productPrice,
                                        productQuantity,productDate,productBarcode,productNote);
        return  x ;
    }


    public Call<FindBarcodeResponse> findBarcodeItemResponseCall(String userID, String productBarcode)
    {
        Call<FindBarcodeResponse> x = getRestApi().findItem(userID,productBarcode);
        return  x ;
    }


    public Call<EditProfileResponse> editProfileResponseCall(String id, String companyID,String username,String currentPassword,String newPassword)
    {
        Call<EditProfileResponse> x = getRestApi().editProfile(id,companyID,username,currentPassword,newPassword);
        return  x ;
    }


    public Call<UserProfile> userProfileCall(String id, String companyID)
    {
        Call<UserProfile> x = getRestApi().userProfile(id,companyID);
        return  x ;
    }


    public Call<List<SearchItemResponse>> searchItemResponseCall(String searchText, String companyID)
    {
        Call<List<SearchItemResponse>> x = getRestApi().searchItem(searchText,companyID);
        return  x ;
    }


    public Call<List<ExportDataResponse>> exportDataResponseCall(String companyID)
    {
        Call<List<ExportDataResponse>> x = getRestApi().exportData(companyID);
        return  x ;
    }


    public Call<InviteResponse> inviteResponseCall(String companyID,String invited)
    {
        Call<InviteResponse> x = getRestApi().invite(companyID,invited);
        return  x ;
    }


    public Call<List<ListUserResponse>> listUserResponseCall(String userID, String companyID)
    {
        Call<List<ListUserResponse>> x = getRestApi().listUsers(userID,companyID);
        return  x ;
    }


    public Call<DoManagerResponse> managerResponseCall(String userID)
    {
        Call<DoManagerResponse> x = getRestApi().manage(userID);
        return  x ;
    }


    public Call<GetShiftResponse> getShiftResponseCall(String userID)
    {
        Call<GetShiftResponse> x = getRestApi().getShift(userID);
        return  x ;
    }


    public Call<UserShiftResponse> shiftResponseCall(String startShift,String endShift,String userID)
    {
        Call<UserShiftResponse> x = getRestApi().shift(startShift,endShift,userID);
        return  x ;
    }


    public Call<List<ExportEmployeeResponse>> exportEmployeeResponseCall(String companyID)
    {
        Call<List<ExportEmployeeResponse>> x = getRestApi().exportEmployee(companyID);
        return  x ;
    }


    public Call<List<UpdateFolderResponse>> updateFolderResponseCall(String companyID,String userID,String productFolder,String oldFolder)
    {
        Call<List<UpdateFolderResponse>> x = getRestApi().updateFolder(companyID,userID,productFolder,oldFolder);
        return  x ;
    }


    public Call<VerifyAccountResponse> verifyAccountResponseCall( String userID, String verifyCode)
    {
        Call<VerifyAccountResponse> x = getRestApi().verify(userID,verifyCode);
        return  x ;
    }


    public Call<CheckRoleResponse> checkRoleResponseCall(String userID)
    {
        Call<CheckRoleResponse> x = getRestApi().checkRole(userID);
        return  x ;
    }


    public Call<ChangePasswordResponse> changePasswordResponseCall(String userID,String companyID,String currentPassword,String newPassword)
    {
        Call<ChangePasswordResponse> x = getRestApi().changePassword(userID,companyID,currentPassword,newPassword);
        return  x ;
    }


    public Call<DashboardResponse> dashboardResponseCall(String companyID)
    {
        Call<DashboardResponse> x = getRestApi().dashboard(companyID);
        return  x ;
    }
}

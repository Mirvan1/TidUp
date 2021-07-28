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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {
    //@Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("/register_project.php")
    Call<Register> registerUser(@Field("email") String email, @Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("/createCompany.php")
    Call<companyCreateJson> createCompanyUser(@Field("companyName") String companyName,@Field("email") String email);


    @FormUrlEncoded
    @POST("/joinCompany.php")
    Call<companyJoinJson> joinCompanyUser(@Field("email") String email, @Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/login_project.php")
    Call<LoginJson> loginUser(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("/mainFragment.php")
    Call<List<ProductResponse>> item(@Field("userID") String userID,@Field("companyID") String companyID);



    @FormUrlEncoded
    @POST("/itemListFromFolder.php")
    Call<List<ItemListResponse>> itemListFromFolder(@Field("productFolder") String productFolder, @Field("companyID") String companyID);



    @FormUrlEncoded
    @POST("/addFolder.php")
    Call<AddFolderResponse> addFolder(@Field("userID") String userID, @Field("companyID") String companyID, @Field("productFolder") String productFolder);

    @FormUrlEncoded
    @POST("/project_addItem.php")
    Call<AddItemJson> addItem(@Field("userID") String userID, @Field("companyID") String companyID,
                              @Field("companyName") String companyName, @Field("username") String username,
                              @Field("productName") String productName, @Field("productPrice") String productPrice,
                              @Field("productQuantity") String productQuantity, @Field("productDate") String productDate,
                              @Field("productBarcode") String productBarcode, @Field("productNote") String productNote,
                              @Field("productPhoto") String productPhoto, @Field("productFolder") String productFolder);



    @FormUrlEncoded
    @POST("/project_deleteItem.php")
    Call<DeleteFolderResponse> deleteFolder(@Field("userID") String userID, @Field("productFolder") String productFolder);


    @FormUrlEncoded
    @POST("/deleteItemOne.php")
    Call<DeleteItemResponse> deleteItem(@Field("productID") String productID,@Field("userID") String userID);


    @FormUrlEncoded
    @POST("/updateItem.php")
    Call<UpdateItemResponse> updateItem(@Field("userID") String userID,@Field("productID") String productID,
                                     @Field("productName") String productName, @Field("productPrice") String productPrice,
                                     @Field("productQuantity") String productQuantity, @Field("productDate") String productDate,
                                     @Field("productBarcode") String productBarcode, @Field("productNote") String productNote);


    @FormUrlEncoded
    @POST("/findBarcode.php")
    Call<FindBarcodeResponse> findItem(@Field("userID") String userID, @Field("productBarcode") String productBarcode);


    @FormUrlEncoded
    @POST("/userProfile.php")
    Call<UserProfile> userProfile(@Field("id") String id, @Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/updateUser.php")
    Call<EditProfileResponse> editProfile(@Field("id") String id, @Field("companyID") String companyID,@Field("username") String username,@Field("currentPassword") String currentPassword, @Field("newPassword") String newPassword);


    @FormUrlEncoded
    @POST("/searchItem.php")
    Call<List<SearchItemResponse>> searchItem(@Field("searchText") String searchText, @Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/exportData.php")
    Call<List<ExportDataResponse>> exportData(@Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/inviteUser.php")
    Call<InviteResponse> invite(@Field("companyID") String companyID,@Field("invited") String invited);


    @FormUrlEncoded
    @POST("/listUser.php")
    Call<List<ListUserResponse>> listUsers(@Field("userID") String userID, @Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/doManager.php")
    Call<DoManagerResponse> manage(@Field("userID") String userID);


    @FormUrlEncoded
    @POST("/userShift.php")
    Call<UserShiftResponse> shift(@Field("startShift") String startShift,@Field("endShift") String endShift,@Field("id") String userID);

    @FormUrlEncoded
    @POST("/getShift.php")
    Call<GetShiftResponse> getShift(@Field("id") String userID);


    @FormUrlEncoded
    @POST("/exportEmployee.php")
    Call<List<ExportEmployeeResponse>> exportEmployee(@Field("companyID") String companyID);


    @FormUrlEncoded
    @POST("/updateFolder.php")
    Call<List<UpdateFolderResponse>> updateFolder(@Field("companyID") String companyID,@Field("userID") String userID,@Field("productFolder") String productFolder,@Field("oldFolder") String oldFolder);


    @FormUrlEncoded
    @POST("/verifyAccount.php")
    Call<VerifyAccountResponse> verify( @Field("userID") String userID, @Field("verifyCode") String verifyCode);


    @FormUrlEncoded
    @POST("/defineRole.php")
    Call<CheckRoleResponse> checkRole(@Field("userID") String userID);


    @FormUrlEncoded
    @POST("/updatePassword.php")
    Call<ChangePasswordResponse> changePassword(@Field("id") String userID,@Field("companyID") String companyID,@Field("currentPassword") String currentPassword,@Field("newPassword") String newPassword);


    @FormUrlEncoded
    @POST("/dashboard.php")
    Call<DashboardResponse> dashboard(@Field("companyID") String companyID);

}

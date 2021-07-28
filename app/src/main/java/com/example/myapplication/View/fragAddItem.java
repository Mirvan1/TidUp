package com.example.myapplication.View;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Models.AddItemJson;
import com.example.myapplication.R;
import com.example.myapplication.Controller.ManagerAll;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragAddItem extends Fragment{
    View view;
    public EditText addItemName,addItemPrice,addItemQuantity,addItemDate,addItemNote;
    public static EditText addItemBarcode;
    Button  addItemButton,addItemBarcodeButton;
   // ImageView addItemImage;
   // Bitmap bitmap;
    String imageString;
    public static String barcodeResult;
    Button addBack;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;
    String userIDlogin,companyIDlogin;
    ImageView mPreview;
    Button imageRecog;
    Bitmap cropped;
    private Uri mCropImageUri;
    Button loadImage,cropImage,imageApprove;
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    private static final int IMAGE_PICK_CAMERA_CODE = 1001;
    //private Button captureImageBtn,detectTextBtn;
    private ImageView imageSetCropped;
    //private EditText editText;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    public String text = "";
    Bitmap cameraBitmap;
    TextView cameraLogo;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
   // Bitmap photo;
   final int PIC_CROP = 1;
    String cameraPermission[];
    String storagePERmission[];
    int count=0;
    Uri image_url;
    Context context;
    Spinner folderSpinnerAdd;
    String folderSpinnerValue;
    Button imageCrop;
    DatePickerDialog datePickerDialog;
    Button datePicker;
    private CropImageView mCropImageView;
    String currentDate="",pickedDate="";

    public fragAddItem(Context context){this.context=context;}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_frag_add_item, container, false);
        define();
        operate();
       // textToIm();
        return view;
    }

    public void define(){
        addItemName=view.findViewById(R.id.addItemName);
        addItemPrice=view.findViewById(R.id.addItemPrice);
        addItemQuantity=view.findViewById(R.id.addItemQuantity);
        addItemDate=view.findViewById(R.id.addItemDate);
        cameraLogo=view.findViewById(R.id.cameraLogo);
        addItemBarcode=view.findViewById(R.id.addItemBarcode);
       // addItemFolder=view.findViewById(R.id.addItemFolder);
        addItemNote=view.findViewById(R.id.addItemNote);
      //  addItemImage=view.findViewById(R.id.addItemImage);
        addBack=view.findViewById(R.id.addBack);
        addItemButton=view.findViewById(R.id.addItemButton);
        addItemBarcodeButton=view.findViewById(R.id.addItemBarcodeButton);
        imageRecog=view.findViewById(R.id.imageRecog);
        folderSpinnerAdd=view.findViewById(R.id.folderSpinnerAdd);
        mCropImageView =view.findViewById(R.id.CropImageView);
        cropImage=view.findViewById(R.id.cropImage);
        imageApprove=view.findViewById(R.id.imageApprove);
        imageSetCropped=view.findViewById(R.id.imageSetCropped);
        datePicker=view.findViewById(R.id.datePicker);

        setProductDate();
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker(v);
            }
        });

        addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentMainPage fr=new fragmentMainPage();
                ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listViewLayout,fr,"fragMain")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }
        });
    }

    public void operate(){


        cropped=null;
        imageString="";
        mCropImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivityForResult(getPickImageChooserIntent(), 200);
                if(checkAndRequestPermissions()) {
                    startActivityForResult(getPickImageChooserIntent(), 200);
                }

            }
        });
        cropImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCropImageClick(view);

//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
////                photo = (Bitmap) data.getExtras().get("data");
//                addItemImage.setImageBitmap(photo);
               // }
                cameraLogo.setVisibility(View.INVISIBLE);
            }
        });

        imageApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSetCropped.setImageBitmap(cropped);
                imageSetCropped.setVisibility(View.VISIBLE);

                cropImage.setVisibility(View.INVISIBLE);
                imageApprove.setVisibility(View.INVISIBLE);
            }
        });

        imageRecog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cropped!=null){
                detectText(cropped);}
                //dispatchTakePictureIntent();
                //count+=1;
                //  showImageImportDialog();
            }
        });


//        addItemPrice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                detectText();
//            }
//        });
        addItemBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getContext(),barcodeView.class));

               Log.i("barcodec","1"+barcodeResult);
              //  startActivityForResult(intentIntegrator,555);
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=addItemName.getText().toString();
                String price=addItemPrice.getText().toString();
                String quantity=addItemQuantity.getText().toString();
                String date=addItemDate.getText().toString();
                String barcode=addItemBarcode.getText().toString();
                String note=addItemNote.getText().toString();
             //   String folder=addItemFolder.getText().toString();

                if (!(name.equals("")) && !(price.equals("")) && !(quantity.equals(""))
                && !(folderSpinnerValue.equals(""))){
                    Toast.makeText(context,"send response",Toast.LENGTH_LONG).show();
                 userIDlogin=MainPage.userID;
                    //   userIDlogin=sendFolderValue.userIDfromLogin;
                    companyIDlogin=MainPage.companyID;
                    //companyIDlogin=sendFolderValue.companyIDfromLogin;
                        addItemRequest(userIDlogin,companyIDlogin , "company Name",
                                "username", name, price, quantity, date, barcode, note, imageTostring(), folderSpinnerValue);

                    }
                else{
                    Toast.makeText(context,"Please fill the blanks",Toast.LENGTH_LONG).show();
                }
            }
        });
//            ProductResponse pr=new ProductResponse();
//            String bc=pr.getProductFolder();
//            Log.i("folder names: ",bc);
        ;
        fragmentMainPage a=new fragmentMainPage();
//        ArrayList<String> spinnerData=a.ls;

        Toast.makeText(getContext(),a.ls+" ",Toast.LENGTH_LONG).show();
        Log.i("spinner  ",a.ls+"");
//        productAdapter pa=new productAdapter(spinnerData,getContext());

        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,sendFolderValue.adSp);
        folderSpinnerAdd.setAdapter(spinnerAdapter);
        folderSpinnerAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 folderSpinnerValue=parent.getItemAtPosition(position).toString();
                Snackbar.make(view,folderSpinnerValue+"  :folders",Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri imageUri = getPickImageResultUri(data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage,
            // but we don't know if we need to for the URI so the simplest is to try open the stream and see if we get error.
            boolean requirePermissions = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkAndRequestPermissions()) {
//                checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
//                    isUriRequiresPermissions(imageUri)

                // request permissions and handle the result in onRequestPermissionsResult()
               requirePermissions = true;
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            }

            if (!requirePermissions) {
                mCropImageView.setImageUriAsync(imageUri);
            }
        }
        //IntentResult intentResult= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
//         if(requestCode==123 && data!=null){
//
//                 performCrop();
//            Uri uri=data.getData();
//            try {
//
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//
//                addItemImage.setImageBitmap(bitmap);/////////////////
//
//            }
//            catch (IOException e){
//                Toast.makeText(context,"bitmap error"+e.toString(),Toast.LENGTH_LONG).show();
//
//            }
//
//
//            }


//        if (requestCode == CAMERA_REQUEST )
//        {       //if (requestCode==1){}
//
//             bitmap = (Bitmap) data.getExtras().get("data");
//            addItemImage.setImageBitmap(bitmap);
//
//        }
        // && resultCode == RESULT_OK
//        if (requestCode == REQUEST_IMAGE_CAPTURE) {
//
//            Bundle extras = data.getExtras();
//            cameraBitmap = (Bitmap) extras.get("data");
//            addItemImage.setImageBitmap(cameraBitmap);
//        }
//        if(requestCode==IMAGE_PICK_CAMERA_CODE && data!=null){
//
//            Uri uri=data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
//                addItemImage.setImageBitmap(bitmap);
//            }
//            catch (IOException e){
//                Toast.makeText(context,"bitmap error"+e.toString(),Toast.LENGTH_LONG).show();
//
//            }
//
//        }

    }
//
//    public String imageTostring(){
//        if (bitmap!=null) {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] bt = byteArrayOutputStream.toByteArray();
//             imageString = Base64.encodeToString(bt, Base64.DEFAULT);
//            return imageString;
//    }
//        else {return imageString;}
//    }
public String imageTostring(){
    if (cropped!=null) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        cropped.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bt = byteArrayOutputStream.toByteArray();
        imageString = Base64.encodeToString(bt, Base64.DEFAULT);
        return imageString;
    }
    else {return imageString;}
}
    public void onCropImageClick(View view) {
        cropped = mCropImageView.getCroppedImage(500, 500);
        if (cropped != null)
            mCropImageView.setImageBitmap(cropped);
    }

    public void addItemRequest(String userID,String companyID,String companyName,String username,String productName,String productPrice,
                               String productQuantity,String productDate,String productBarcode,
                               String productNote,String productPhoto,String productFolder){
        Call<AddItemJson> req=ManagerAll.getInstance().addItemJsonCall(userID,companyID,companyName,username,productName,productPrice,productQuantity,
                productDate,productBarcode,productNote,productPhoto, productFolder);
            req.enqueue(new Callback<AddItemJson>() {
                @Override
                public void onResponse(Call<AddItemJson> call, Response<AddItemJson> response) {
                    if(response.body().isTf()){
                        Toast.makeText(context,response.body().getText().toString(),Toast.LENGTH_LONG).show();
                        fragmentMainPage fr=new fragmentMainPage();
                        ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.listViewLayout,fr,"fragMain")
                                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }
                    else{

                    Toast.makeText(context,"else-false",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AddItemJson> call, Throwable t) {
                    Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });


    }



    private void pickGallery() {
        Intent gal = new Intent(Intent.ACTION_PICK);
        gal.setType("image/*");
        startActivityForResult(gal, IMAGE_PICK_GALLERY_CODE);
    }


    public void detectText(Bitmap bitmap){
        FirebaseVisionImage firebaseVisionImage=FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVisionTextDetector firebaseVisionTextDetector= FirebaseVision.getInstance().getVisionTextDetector();

        firebaseVisionTextDetector.detectInImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {

            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                checkSuccess(firebaseVisionText);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"error "+e.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("eror",e.getMessage());
            }
        });

    }
    public void checkSuccess(FirebaseVisionText firebaseVisionText){

        List<FirebaseVisionText.Block> blockList=firebaseVisionText.getBlocks();
        if(blockList.size()==0){
            Toast.makeText(getContext(),"no text found in image",Toast.LENGTH_LONG).show();
        }
        else{
            for(FirebaseVisionText.Block block:firebaseVisionText.getBlocks()){
                text =block.getText();
               addItemName.setText(text);
               Toast.makeText(getContext(),"DETECTED tExt: "+text,Toast.LENGTH_LONG).show();
            }
        }

    }

    private  boolean checkAndRequestPermissions() {
        int camerapermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
        int writepermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionLocation = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE);
       // int permissionRecordAudio = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (camerapermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (writepermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

    private void explain(String msg){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setMessage(msg)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        //  permissionsclass.requestPermission(type,code);
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.exampledemo.parsaniahardik.marshmallowpermission")));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        getActivity().finish();
                    }
                });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mCropImageView.setImageUriAsync(mCropImageUri);
        } else {
            Toast.makeText(getContext(), "Required permissions are not granted", Toast.LENGTH_LONG).show();
        }

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                //perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("permiission camera in", "sms & location services permission granted");

                        // process the normal flow
                        startActivityForResult(getPickImageChooserIntent(), 200);
                        //else any one or both the permissions are not granted
                    } else {
                       Log.d("permiission camera in", "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)
                                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            showDialogOK("Service Permissions are required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    // proceed with logic by disabling the related features or quit the app.
                                                   // finish();
                                                    dialog.dismiss();
                                                    break;
                                            }
                                        }
                                    });
                        }
                        //permission is denied (and never ask again is  checked)
                        //shouldShowRequestPermissionRationale will return false
                        else {
                            explain("You need to give some mandatory permissions to continue. Do you want to go to app settings?");
                            //                            //proceed with logic by disabling the related features or quit the app.
                        }
                    }
                }
            }
        }
    }

    /**
     * Create a chooser intent to select the source to get image from.<br/>
     * The source can be camera's (ACTION_IMAGE_CAPTURE) or gallery's (ACTION_GET_CONTENT).<br/>
     * All possible sources are added to the intent chooser.
     */
    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager =getActivity().getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list  so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 2);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "pickImageResult.jpeg"));
        }
        return outputFileUri;
    }

    /**
     * Get the URI of the selected image from {@link #getPickImageChooserIntent()}.<br/>
     * Will return the correct URI for camera and gallery image.
     *
     * @param data the returned data of the activity result
     */
    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null && data.getData() != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Test if we can open the given Android URI to test if permission required error is thrown.<br>
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isUriRequiresPermissions(Uri uri) {
        try {
            ContentResolver resolver = getActivity().getContentResolver();
            InputStream stream = resolver.openInputStream(uri);
            stream.close();
            return false;
        } catch (FileNotFoundException e) {
            if (e.getCause() instanceof ErrnoException) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void setProductDate(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                pickedDate=makeDateString(day,month,year);
                currentDate=getTodayDate();
                try {
                    Date date1=new SimpleDateFormat("MM-dd-yyyy").parse(pickedDate);
                    Date date2=new SimpleDateFormat("MM-dd-yyyy").parse(currentDate);
                    if(date1.after(date2)){
                        addItemDate.setText(pickedDate);
                    }
                    else{
                        Snackbar.make(view,"The product date expired!",Snackbar.LENGTH_LONG).setAction("Add anyway", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addItemDate.setText(pickedDate);
                            }
                        })
                         .setDuration(5)
                        .show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style=AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog=new DatePickerDialog(getContext(),style,dateSetListener,year,month,day);
    }
    public void openDatePicker(View v){
        datePickerDialog.show();
    }
    public String getTodayDate(){
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        month=month+1;
        return makeDateString(day,month,year);
    }

    private String makeDateString(int day, int month, int year) {
        return month+"-"+day+"-"+year;
    }

//    private String getMonthFormat(int month) {
//        switch (month) {
//            case 1:
//                return "Jan";
//            case 2:
//                return "Feb";
//            case 3:
//                return "Mar";
//            case 4:
//                return "Apr";
//            case 5:
//                return "May";
//            case 6:
//                return "Jun";
//            case 7:
//                return "Jul";
//            case 8:
//                return "Aug";
//            case 9:
//                return "Sep";
//            case 10:
//                return "Oct";
//            case 11:
//                return "Nov";
//            case 12:
//                return "Dec";
//        }
//    return "Jan";
//    }



}
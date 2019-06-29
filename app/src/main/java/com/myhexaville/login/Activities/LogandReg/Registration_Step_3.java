package com.myhexaville.login.Activities.LogandReg;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.myhexaville.login.Activities_Dashboards.DashBoard;
import com.myhexaville.login.Adapters.Morebrands_Adapter;
import com.myhexaville.login.AllParsings.AddMoreBrands;
import com.myhexaville.login.MainActivity;
import com.myhexaville.login.R;
import com.myhexaville.login.SessionManage.SessionManager;
import com.myhexaville.login.Utils.Utilities;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission_group.CAMERA;

public class Registration_Step_3 extends AppCompatActivity {
    RecyclerView morebrands;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PERMISSION_REQUEST_CAMERA_CODE = 201;
    Morebrands_Adapter morebrands_adapter;
    Boolean CameraPermission ;
    Boolean RecordAudioPermission;
    Boolean SendSMSPermission;
    public static ArrayList<String> Models = new ArrayList<>();
    SessionManager sessionManager;
    Button upload;
    String name, email, com_name, password, address, mobile;
    File trandmark_cerFile, copyright_cerFile, others_cerFile, gst_cerFile, degree_cerFile;
    ImageView trandmark_cer, copyright_cer, others_cer, gst_cer, degree_cer;
    int trandmark_cerAnInt, copyright_cerAnInt, others_cerAnInt, gst_cerAnInt, degree_cerAnInt;
    List<AddMoreBrands> addMoreBrandsArrayList = new ArrayList<>();
    private ProgressDialog dialog;
    private int RESULT_LOAD_IMAGE = 101;
    private int RESULT_PICK_IMAGE = 141;
    public final int REQUEST_ID_MULTIPLE_PERMISSIONS = 100;
    public final int REQUEST_ID_MULTIPLE_CAMERA = 100;
    public final int REQUEST_ID_MULTIPLE_READ_PER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__step_3);
        morebrands = findViewById(R.id.morebrands);
        trandmark_cer = findViewById(R.id.trandmark_cer);
        copyright_cer = findViewById(R.id.copyright_cer);
        others_cer = findViewById(R.id.others_cer);
        upload = findViewById(R.id.uplodoc);
        gst_cer = findViewById(R.id.gst_cer);
        sessionManager= new SessionManager(this);
        degree_cer = findViewById(R.id.degree_cer);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        com_name = getIntent().getStringExtra("com_name");
        password = getIntent().getStringExtra("password");
        address = getIntent().getStringExtra("address");
        mobile = getIntent().getStringExtra("mobile");
        Log.e("name", "" + name);
        Log.e("email", "" + email);
        Log.e("com_name", "" + com_name);
        Log.e("password", "" + password);
        Log.e("address", "" + address);
        Log.e("mobile", "" + mobile);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        addMoreBrandsArrayList.add(new AddMoreBrands(new EditText(this), new ImageView(this), new ImageView(this)));
        morebrands_adapter = new Morebrands_Adapter(this, addMoreBrandsArrayList);
        morebrands.setLayoutManager(llm);
        morebrands.setAdapter(morebrands_adapter);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (trandmark_cerFile.exists() || copyright_cerFile.exists() || others_cerFile.exists() || gst_cerFile.exists() || degree_cerFile.exists()) {
                        new UPLOAd_Certificates(trandmark_cerFile, copyright_cerFile, others_cerFile, gst_cerFile, degree_cerFile).execute();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(Registration_Step_3.this, "Please select all Certificates", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        degree_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    degree_cerAnInt = 1;
                    seewhattheythink();
                    Snackbar.make(v.getRootView(), "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {
                    degree_cerAnInt = 0;
                    requestPermission();
                    Snackbar.make(v.getRootView(), "Please request permission.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        others_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    others_cerAnInt = 1;
                    seewhattheythink();
                    Snackbar.make(v.getRootView(), "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {
                    others_cerAnInt = 0;
                    requestPermission();
                    Snackbar.make(v.getRootView(), "Please request permission.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        gst_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    gst_cerAnInt = 1;
                    seewhattheythink();
                    Snackbar.make(v.getRootView(), "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {
                    gst_cerAnInt = 0;
                    requestPermission();
                    Snackbar.make(v.getRootView(), "Please request permission.", Snackbar.LENGTH_LONG).show();
                }

            }
        });
        copyright_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    copyright_cerAnInt = 1;
                    seewhattheythink();
                    Snackbar.make(v.getRootView(), "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {
                    copyright_cerAnInt = 0;
                    requestPermission();
                    Snackbar.make(v.getRootView(), "Please request permission.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        trandmark_cer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission()) {
                    trandmark_cerAnInt = 1;
                    seewhattheythink();
                    Snackbar.make(v.getRootView(), "Permission already granted.", Snackbar.LENGTH_LONG).show();

                } else {
                    trandmark_cerAnInt = 0;
                    requestPermission();
                    Snackbar.make(v.getRootView(), "Please request permission.", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }

    private void seewhattheythink() {
        new AlertDialog.Builder(Registration_Step_3.this)
                .setMessage("From which you want to upload?")
                .setPositiveButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(cameraIntent, RESULT_PICK_IMAGE);
                        Toast.makeText(Registration_Step_3.this, "ok", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Take it from gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                }).setNeutralButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Registration_Step_3.this, "back pressed", Toast.LENGTH_SHORT).show();
            }
        })
                .create()
                .show();
    }

    private void requestPermission() {
// Creating String Array with Permissions.
        ActivityCompat.requestPermissions(Registration_Step_3.this, new String[]
                {
                        CAMERA,
                        READ_EXTERNAL_STORAGE,
                        WRITE_EXTERNAL_STORAGE,
                }, PERMISSION_REQUEST_CODE);
        //      ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE, CAMERA}, PERMISSION_REQUEST_CODE);
        //  ActivityCompat.requestPermissions(this , new String[]{ CAMERA} ,RESULT_PICK_IMAGE  );

    }

    private boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
      //  int ForthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), GET_ACCOUNTS);
            Log.e("FirstPermissionResult" , ""+FirstPermissionResult);
            Log.e("SecondPermissionResult" , ""+SecondPermissionResult);
            Log.e("ThirdPermissionResult" , ""+ThirdPermissionResult);
        FirstPermissionResult =0;
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED ;
           //     ForthPermissionResult == PackageManager.PERMISSION_GRANTED ;
//        if (ActivityCompat.checkSelfPermission(Registration_Step_3.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//
//            return true;
//
//        } else if (ActivityCompat.checkSelfPermission(Registration_Step_3.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//
//            return true;
//
//        } else {
//            //    askForPermission(Manifest.permission.CAMERA,REQUEST_ID_MULTIPLE_CAMERA);
//            return false;
//        }
//

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == RESULT_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                Log.i("We go somethihbygf", "Uri: " + resultData.getData());
                Toast.makeText(this, "is " + resultData.getData(), Toast.LENGTH_SHORT).show();
                //   showImage(uri,resultData);
            }

        }
        Toast.makeText(this, "" + resultData, Toast.LENGTH_SHORT).show();
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != resultData) {
            Uri selectedImage = resultData.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("We go somethihbygf", "Uri: " + resultData.getData());
            if (trandmark_cerAnInt == 1) {
                trandmark_cerFile = new File(picturePath);
                if (trandmark_cerFile.exists()) {
                    trandmark_cer.setImageResource(R.drawable.docfound);
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
                trandmark_cerAnInt = 0;
            } else if (others_cerAnInt == 1) {
                others_cerFile = new File(picturePath);
                if (others_cerFile.exists()) {
                    others_cer.setImageResource(R.drawable.docfound);
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
                others_cerAnInt = 0;
            } else if (gst_cerAnInt == 1) {
                gst_cerFile = new File(picturePath);
                if (gst_cerFile.exists()) {
                    gst_cer.setImageResource(R.drawable.docfound);
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
                gst_cerAnInt = 0;
            } else if (copyright_cerAnInt == 1) {
                copyright_cerFile = new File(picturePath);
                if (copyright_cerFile.exists()) {
                    copyright_cer.setImageResource(R.drawable.docfound);
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
                copyright_cerAnInt = 0;
            } else if (degree_cerAnInt == 1) {
                degree_cerFile = new File(picturePath);
                if (degree_cerFile.exists()) {
                    degree_cer.setImageResource(R.drawable.docfound);
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
                }
                degree_cerAnInt = 0;
            }


//
//           else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }else if(trandmark_cerFile.exists() && trandmark_cerAnInt==1)
//            {
//                trandmark_cer.setImageResource(R.drawable.docfound);
//                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show();
//            }
            Toast.makeText(this, "is " + resultData.getData(), Toast.LENGTH_SHORT).show();
            cursor.close();
            // ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

    }


    private class UPLOAd_Certificates extends AsyncTask<Void, Void, String> {
        File trandmark_cerFile, copyright_cerFile, others_cerFile, gate_photo_file, gate_sign_file;
        String result = "";

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Registration_Step_3.this);
            dialog.setCancelable(false);
            dialog.show();
            super.onPreExecute();
        }

        public UPLOAd_Certificates(File trandmark_cerFile, File copyright_cerFile, File others_cerFile, File gate_photo_file, File gate_sign_file) {
            this.trandmark_cerFile = trandmark_cerFile;
            this.copyright_cerFile = copyright_cerFile;
            this.others_cerFile = others_cerFile;
            this.gate_photo_file = gate_photo_file;
            this.gate_sign_file = gate_sign_file;
        }

        @Override
        protected String doInBackground(Void... Void) {
            try {


                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);

                entity.addPart("name", new StringBody(name));
                try{
                if(!Models.isEmpty())
                {
                    entity.addPart("brand_name", new StringBody(Models.get(0)));
                }
                }catch (Exception e){
                    e.printStackTrace();
                    entity.addPart("brand_name", new StringBody("Default"));
                }

                entity.addPart("user_type", new StringBody("3"));
                entity.addPart("email", new StringBody("" + email));
                entity.addPart("mobile", new StringBody("" + mobile));
                entity.addPart("address", new StringBody("" + address));
                entity.addPart("company_name", new StringBody("" + com_name));
                entity.addPart("password", new StringBody("" + password));
                entity.addPart("sub_type", new StringBody("3"));
                entity.addPart("trandmark_cer", new FileBody(trandmark_cerFile));
                entity.addPart("copyright_cer", new FileBody(copyright_cerFile));
                entity.addPart("others_cer", new FileBody(others_cerFile));
                entity.addPart("gst_cer", new FileBody(gst_cerFile));
                entity.addPart("degree_cer", new FileBody(degree_cerFile));
//                    result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
//                 //   result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
                result = Utilities.postEntityAndFindJson("https://sdltechserv.in/dgfeb/api/api/Ragistration", entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            String result1 = result;
            if (result1 != null) {

                dialog.dismiss();
                Log.e("result1", result1);

                Toast.makeText(Registration_Step_3.this, " Successfully Registered", Toast.LENGTH_LONG).show();

                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);

            } else {
                dialog.dismiss();
                Toast.makeText(Registration_Step_3.this, "Success", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Registration_Step_3.this , MainActivity.class);
                startActivity(intent);
                finish();

            }

        }
    }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CAMERA_CODE:

                if (grantResults.length > 0) {
                     CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    Log.e("permissions" , ""+CameraPermission);
                    if(!CameraPermission)
                    {
                        Log.e("  CAmear" ," PERMISSION_REQUEST_CAMERA_CODE GONE");
                        ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{Manifest.permission.CAMERA} , PERMISSION_REQUEST_CAMERA_CODE);
                    }
                    if(!RecordAudioPermission)
                    {
                        Log.e("  read" ," PERMISSION_REQUEST_CAMERA_CODE GONE");
                        ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{READ_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CAMERA_CODE);
                    }
                    if(!SendSMSPermission)
                    {
                        Log.e("write" ,"PERMISSION_REQUEST_CAMERA_CODE GONE");
                        ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{WRITE_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CAMERA_CODE);
                    }

                }

            case PERMISSION_REQUEST_CODE:

                if (grantResults.length > 0) {
                    try {
                         CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                         RecordAudioPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                         SendSMSPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                        Log.e("permissions", "" + CameraPermission);
                        Log.e("permissions", "" + RecordAudioPermission);
                        Log.e("permissions", "" + SendSMSPermission);

                        if ( RecordAudioPermission && SendSMSPermission) {

                            Toast.makeText(Registration_Step_3.this, "Permission Granted", Toast.LENGTH_LONG).show();
                        } else {
                            if(!CameraPermission)
                            {
                                Log.e("CAmear" ,"PERMISSION_REQUEST_CODE GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{Manifest.permission.CAMERA} , PERMISSION_REQUEST_CODE);
                            }
                            if(!RecordAudioPermission)
                            {
                                Log.e("read" ,"PERMISSION_REQUEST_CODE GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{READ_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CODE);
                            }
                            if(!SendSMSPermission)
                            {
                                Log.e("write" ,"PERMISSION_REQUEST_CODE GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{WRITE_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CODE);
                            }
                         //   Toast.makeText(Registration_Step_3.this, "Permission Denied", Toast.LENGTH_LONG).show();

                        }
                    }catch (Exception e)
                    {

                        e.printStackTrace();
//                        if (CameraPermission && RecordAudioPermission && SendSMSPermission) {
//
//                            Toast.makeText(Registration_Step_3.this, "Permission Granted", Toast.LENGTH_LONG).show();
//                        } else {
                            if(!CameraPermission)
                            {
                                Log.e("CAmear" ,"GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{Manifest.permission.CAMERA} , PERMISSION_REQUEST_CAMERA_CODE);
                            }
                            if(!RecordAudioPermission)
                            {
                                Log.e("read" ,"GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{READ_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CAMERA_CODE);
                            }
                            if(!SendSMSPermission)
                            {
                                Log.e("write" ,"GONE");
                                ActivityCompat.requestPermissions(Registration_Step_3.this,new String[]{WRITE_EXTERNAL_STORAGE} , PERMISSION_REQUEST_CAMERA_CODE);
                            }
                           // Toast.makeText(Registration_Step_3.this, "Permission Denied", Toast.LENGTH_LONG).show();

//                        }
                    }
                 //   boolean GetAccountsPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                }

                break;
        }
    }
}

package ics.dynamic.dgfab.Activities_Dashboards;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myhexaville.login.R;

import ics.dynamic.dgfab.ScrollingActivity;
import ics.dynamic.dgfab.SessionManage.SessionManager;
import ics.dynamic.dgfab.Utils.Utilities;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class ProfileActivty extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView add_com ,jetpro;
    Toolbar toolbar_prff;
    private ProgressDialog dialog;
    SessionManager sessionManager;
    ImageView prom ,header_cover_image;
    File Propic,Coverpic;
    int c,p;
    private int RESULT_PICK_IMAGE = 101;
    private int RESULT_LOAD_IMAGE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activty);
        sessionManager = new SessionManager(this);
        toolbar_prff = (Toolbar)findViewById(R.id.toolbar_prff);
        toolbar_prff.setNavigationIcon(R.drawable.home);
//        Intent intent = new Intent(this , ScrollingActivity.class);
//        startActivity(intent);
      //  toolbar_prff.setNavigationIcon(R.drawable.home);
        header_cover_image = findViewById(R.id.header_cover_image);
        jetpro = findViewById(R.id.jetpro);
       // prom = findViewById(R.id.prom);
        prom = findViewById(R.id.user_profile_photo);
        header_cover_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c=1;
                doyawanna();
            }
        });
        prom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=1;
                doyawanna();
            }
        });
        toolbar_prff.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   stopActivityTask();
                onBackPressed();
            }
        });

        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.pager);
        add_com = findViewById(R.id.add_com);
        new GET_All_Profile_PICS_From_USer(sessionManager.getUS()).execute();
        tabLayout.addTab(tabLayout.newTab().setText("OverView"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Average"));
        tabLayout.addTab(tabLayout.newTab().setText("Analytics"));
        tabLayout.addTab(tabLayout.newTab().setText("Connections"));
        tabLayout.addTab(tabLayout.newTab().setText("Interest"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        add_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_com.setText("Connected");
            }
        });
//        final ProfileAdapter profileAdapter = new ProfileAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(profileAdapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    private void doyawanna() {
        Toast.makeText(this, "showing", Toast.LENGTH_SHORT).show();
        new AlertDialog.Builder(this)
                .setMessage("From which you want to upload?")
                .setPositiveButton("Take Photo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//
//                        startActivityForResult(cameraIntent, RESULT_PICK_IMAGE);
//                        Toast.makeText(ProfileActivty.this, "ok", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ProfileActivty.this, "back pressed", Toast.LENGTH_SHORT).show();
            }
        })
                .create()
                .show();
        
        
    }

    private class GET_All_Profile_PICS extends AsyncTask<Void, Void, String> {
        File trandmark_cerFile, copyright_cerFile, others_cerFile, gate_photo_file, gate_sign_file;
        String result = "";
        int user_id;

        public GET_All_Profile_PICS(int us) {
            user_id =us;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(ProfileActivty.this);
            dialog.setCancelable(false);
           // dialog.show();
            super.onPreExecute();
        }

//        public UPLOAd_Certificates(File trandmark_cerFile, File copyright_cerFile, File others_cerFile, File gate_photo_file, File gate_sign_file) {
//            this.trandmark_cerFile = trandmark_cerFile;
//            this.copyright_cerFile = copyright_cerFile;
//            this.others_cerFile = others_cerFile;
//            this.gate_photo_file = gate_photo_file;
//            this.gate_sign_file = gate_sign_file;
//        }

        @Override
        protected String doInBackground(Void... Void) {
            try {


                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);

                entity.addPart("user_id", new StringBody(""+user_id));
                if(p==1) {
                    entity.addPart("image", new FileBody(Propic));
                }
                if(c==1) {
                    entity.addPart("cover_image", new FileBody(Coverpic));
                }
                if(p==1 && c==1)
                {
                    entity.addPart("image", new FileBody(Propic));
                    entity.addPart("cover_image", new FileBody(Coverpic));
                }

//                    result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
//                 //   result = Utilities.postEntityAndFindJson("https://www.spellclasses.co.in/DM/Api/taxreturn", entity);
                result = Utilities.postEntityAndFindJson("https://sdltechserv.in/dgfeb/api/api/updateprofile", entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            String result1 = result;
            if (result1 !=null) {


                Log.e("result1 of upload", result1);

                Toast.makeText(ProfileActivty.this, " Successfully Registered", Toast.LENGTH_LONG).show();
                new GET_All_Profile_PICS_From_USer(sessionManager.getUS()).execute();
                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);
                c=0;
                p=0;
            } else {
                dialog.dismiss();
                new GET_All_Profile_PICS_From_USer(sessionManager.getUS()).execute();
                //   Intent in=new Intent(MainActivity.this,NextActivity.class);
                //  in.putExtra("doc",doc);
                //     startActivity(in);
                c=0;
                p=0;
                Toast.makeText(ProfileActivty.this, "Some Problem", Toast.LENGTH_LONG).show();
            }

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
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
            if(p==1) {
                Propic = new File(picturePath);
            }else if(c==1) {
                Coverpic = new File(picturePath);
            }
            try {
                if (Propic.exists()) {
                    Toast.makeText(this, "uploading Profile", Toast.LENGTH_SHORT).show();
                    new GET_All_Profile_PICS(sessionManager.getUS()).execute();

                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            try {
                if (Coverpic.exists()) {
                    Toast.makeText(this, "uploading cover", Toast.LENGTH_SHORT).show();
                    new GET_All_Profile_PICS(sessionManager.getUS()).execute();
                }
            }catch (Exception e)
            {

            }
            Toast.makeText(this, "is " + resultData.getData(), Toast.LENGTH_SHORT).show();
            cursor.close();
            // ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

    }

    private class GET_All_Profile_PICS_From_USer extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;
       int US;
        public GET_All_Profile_PICS_From_USer(int us) {
            this.US =us;
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(ProfileActivty.this);
            dialog.setCancelable(true);
           // dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://sdltechserv.in/dgfeb/api/api/getusers");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("user_id", US);


                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000  /*milliseconds*/);
                conn.setConnectTimeout(15000  /*milliseconds*/);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            dialog.dismiss();
            if (result != null) {

                try {
                    Log.e("result at ", "get back" + result);
                    JSONObject jsonObject = new JSONObject(result);
                    jetpro.setText(jsonObject.getJSONArray("data").getJSONObject(0).get("name").toString());
                    try {
                        Glide.with(ProfileActivty.this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.prof).error(R.drawable.prof))
                                .load("https://sdltechserv.in/dgfeb/uploads/" + jsonObject.getJSONArray("data").getJSONObject(0).getString("image"))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        // log exception
                                        Log.e("TAG", "Error loading image", e);
                                        return false; // important to return false so the error placeholder can be placed
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                })
                                .into(prom);
//                        prom.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(ProfileActivty.this, ProfileActivty.class);
//                                startActivity(intent);
//                            }
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Glide.with(ProfileActivty.this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.pbgg).error(R.drawable.pbgg))
                                .load("https://sdltechserv.in/dgfeb/uploads/" + jsonObject.getJSONArray("data").getJSONObject(0).getString("cover_image"))
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        // log exception
                                        Log.e("TAG", "Error loading image", e);
                                        return false; // important to return false so the error placeholder can be placed
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        return false;
                                    }
                                })
                                .into(header_cover_image);
//                        prom.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Intent intent = new Intent(ProfileActivty.this, ProfileActivty.class);
//                                startActivity(intent);
//                            }
//                        });
                    } catch (Exception e) {
                        e.printStackTrace();
//                    }
                    }

//                nav_user.setText(user);

                    Log.e("PostRegistration", result.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }
    }
}

package com.myhexaville.login.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myhexaville.login.Activities.LogandReg.Registration_Step_1;
import com.myhexaville.login.AllParsings.Type_Sub_User_Data;
import com.myhexaville.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class SignUpFragment extends Fragment implements OnSignUpListener{
    private static final String TAG = "SignUpFragment";
    ArrayList<Type_Sub_User_Data> type_sub_user_data = new ArrayList<>();
   // ArrayList<String> SubTypestrings = new ArrayList<>();
    String SubTypestrings [];
    Button nxbtn;
    String Typestrings [];
    android.support.v7.widget.AppCompatSpinner main_type ,sub_main_type;
    private ProgressDialog progressDialog;
    EditText name,com_name,email,password,address,mobile;
//    Button nxbtn;
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_signup, container, false);


        main_type = inflate.findViewById(R.id.typeuser);
        sub_main_type = inflate.findViewById(R.id.subtype);
        sub_main_type.setVisibility(View.GONE);
        nxbtn = inflate.findViewById(R.id.nxbtn);
        name= inflate.findViewById(R.id.name);
        com_name = inflate.findViewById(R.id.com_name);
        email = inflate.findViewById(R.id.email);
        password = inflate.findViewById(R.id.password);
        address = inflate.findViewById(R.id.address);
        mobile = inflate.findViewById(R.id.mobile);
        nxbtn = inflate.findViewById(R.id.nxbtn);
        Typestrings = new String[main_type.getCount()];
        nxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() !=0 && com_name.getText().toString().length()!=0 && email.getText().toString().length()!=0
                        && password.getText().toString().length() !=0) {
                    if(isValidEmail(getActivity() ,email.getText().toString()) && mobile.getText().toString().length() ==10)
                    {
                        Intent intent = new Intent(getActivity(), Registration_Step_1.class);
                        intent.putExtra("name" , name.getText().toString());
                        intent.putExtra("com_name" , com_name.getText().toString());
                        intent.putExtra("email" , email.getText().toString());
                        intent.putExtra("password" , password.getText().toString());
                        intent.putExtra("address" , address.getText().toString());
                        intent.putExtra("mobile" , mobile.getText().toString());
                        startActivity(intent);
                    }else {
                        if(mobile.getText().toString().length() !=10)
                        {
                            mobile.setError("Mobile should be exactly 10 digit");
                        }
                        if(isValidEmail(getActivity(), email.getText().toString())) {
                            email.setError("Email is not valid");
                        }
                    }

                }else {
                    Toast.makeText(getActivity(), "Please fill all requirements", Toast.LENGTH_SHORT).show();
                }
            }
        });
        for(int k=0;k<main_type.getCount();k++)
        {
            Typestrings[k] = String.valueOf(main_type.getItemAtPosition(k));
        }
        main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_list_item_1,Typestrings));
        main_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ""+main_type.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
                if(main_type.getSelectedItem().toString().startsWith("Manu"))
                {
                    Toast.makeText(getActivity(), ""+main_type.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                    GetAllSubTypes();
                    new GETAll_Sub_Types().execute();
                }else {
                  //  Toast.makeText(getActivity(), "nup", Toast.LENGTH_SHORT).show();
                    if(sub_main_type.getVisibility() == View.VISIBLE)
                    {
                        sub_main_type.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nxbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length() !=0 && com_name.getText().toString().length()!=0 && email.getText().toString().length()!=0
                        && password.getText().toString().length() !=0) {
                    Toast.makeText(getActivity(), "Valid", Toast.LENGTH_SHORT).show();
                    if(isValidEmail(getActivity(), email.getText().toString()) && mobile.getText().toString().length() ==10)
                    {
                        if(main_type.getSelectedItem().toString().startsWith("Manu")) {
                            Intent intent = new Intent(getActivity(), Registration_Step_1.class);
                            intent.putExtra("name", name.getText().toString());
                            intent.putExtra("com_name", com_name.getText().toString());
                            intent.putExtra("email", email.getText().toString());
                            intent.putExtra("password", password.getText().toString());
                            intent.putExtra("address", address.getText().toString());
                            intent.putExtra("mobile", mobile.getText().toString());
                            startActivity(intent);
                        }else {
                            Toast.makeText(getActivity(), "Other  Moduler are in work", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), "else part", Toast.LENGTH_SHORT).show();
                        if(mobile.getText().toString().length() !=10 && mobile.getText().toString().length() <10)
                        {
                            Toast.makeText(getActivity(), "mobile part", Toast.LENGTH_SHORT).show();
                            mobile.setError("Mobile should be exactly 10 digit");
                            mobile.requestFocus();
                        }
                        Boolean bx =isValidEmail(getActivity(), email.getText().toString());
                        Toast.makeText(getActivity(), "bx is"+bx, Toast.LENGTH_SHORT).show();

                         if(!bx) {
                            Toast.makeText(getActivity(), "Email part", Toast.LENGTH_SHORT).show();
                            email.setError("Email is not valid");
                            email.requestFocus();
                        }
                    }

                }else {
                    Toast.makeText(getActivity(), "Please fill all requirements", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return inflate;
    }
    public static boolean isValidEmail(FragmentActivity activity, CharSequence target) {
        Toast.makeText(activity, "target "+(!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()), Toast.LENGTH_SHORT).show();
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private void GetAllSubTypes() {
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMax(1000);
//        progressDialog.setTitle("Getting Your Data");
//        progressDialog.setCancelable(false);
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .readTimeout(100,TimeUnit.SECONDS).build();
//        Retrofit RetroLogin = new Retrofit.Builder()
//                .baseUrl(REtroURls.The_Base).client(client).addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Api AbloutApi = RetroLogin.create(Api.class);
//        Call<Type_Sub_Users> get_aboutCall = AbloutApi.TYPE_SUB_USERS_CALL("3");
//        get_aboutCall.enqueue(new Callback<Type_Sub_Users>() {
//            @Override
//            public void onResponse(Call<Type_Sub_Users> call, Response<Type_Sub_Users> response) {
//                Toast.makeText(getActivity(), ""+response, Toast.LENGTH_SHORT).show();
//                SubTypestrings = new String[response.body().getData().size()];
//                Log.e("getact" , ""+response.body().getData().size());
//                for(int k=0;k<response.body().getData().size();k++)
//                {
//                    Log.e("getact msain" , ""+response.body().getData().get(k).getTypeId());
//                   // type_sub_user_data.add(new Type_Sub_User_Data(response.body().getData().get(k).getId() ,response.body().getData().get(k).getTypeId(),response.body().getData().get(k).getSubtypename() ));
//                    //SubTypestrings[k] = response.body().getData().get(k).getSubtypename();
//                }
//               // sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_expandable_list_item_1 , SubTypestrings));
//                sub_main_type.setVisibility(View.VISIBLE);
//                progressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<Type_Sub_Users> call, Throwable t) {
//                Toast.makeText(getActivity(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void signUp() {
        Toast.makeText(getContext(), "Sign up", Toast.LENGTH_SHORT).show();
    }

    private class GETAll_Sub_Types extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.show();

        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(" https://sdltechserv.in/dgfeb/api/api/getsubusertype");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("type_id", "3");


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
            if (result != null) {
                dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {
                    jsonObject = new JSONObject(result);
                    String response = jsonObject.getString("responce");
                    Log.e("Response is", response);
//                    String message = jsonObject.getString("message");
                    JSONArray dataarry = jsonObject.getJSONArray("data");
                    SubTypestrings = new String[dataarry.length()];
                 for(int i=0;i<dataarry.length();i++){
                        JSONObject SubcatObject = dataarry.getJSONObject(i);
                        int id = SubcatObject.getInt("id");
                        int type_id = SubcatObject.getInt("type_id");
                        String subtypename = SubcatObject.getString("subtypename");

                     type_sub_user_data.add(new Type_Sub_User_Data(id ,type_id , subtypename ) );
                     SubTypestrings[i] = type_sub_user_data.get(i).getSubtypename();

                 }
                 sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_list_item_1 , SubTypestrings));
                 sub_main_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }
                 });
               //  sub_main_type.setAdapter(new ArrayAdapter<String>(getActivity() ,R.layout.spin , SubTypestrings));
                sub_main_type.setVisibility(View.VISIBLE);
              //  dialog.dismiss();
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

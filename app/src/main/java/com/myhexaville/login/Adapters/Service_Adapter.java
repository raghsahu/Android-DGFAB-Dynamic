package com.myhexaville.login.Adapters;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.myhexaville.login.Activities.LogandReg.Registration_Step_2;
import com.myhexaville.login.Activities.LogandReg.Registration_Step_3;
import com.myhexaville.login.AllParsings.GET_Services_Data;
import com.myhexaville.login.R;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Service_Adapter  extends RecyclerView.Adapter<Service_Adapter.MyViewHolder> {
    private Context mContext;
    DownloadManager downloadManager;
    String image_url = "https://ihisaab.org//uploads/incomereport/";
    String image_url2 = "https://ihisaab.org//uploads/incomereport/";
    URL image_download_url;
    int pos_try;
    String name, email, com_name, password, address, mobile;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();

    String document;

    public List<GET_Services_Data> Doc;
    private ProgressDialog progressBar;


//    public Rec_Reports_adapter(Context gallery_act, List<Reports> Doc) {
//        mContext = gallery_act;
//      this.Doc = Doc;
//    }


    public Service_Adapter(Context context, List<GET_Services_Data> doc) {
        mContext=context;
        this.Doc = doc;

        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_of_doc ;
        ImageView ser_image;
        public TextView  down_btn, email_to;
        RelativeLayout re_layout;
        TextView doc_date;
        String doc_date_str;


        public MyViewHolder(View itemView) {
            super(itemView);
            name_of_doc = (TextView) itemView.findViewById(R.id.name);
            ser_image =  itemView.findViewById(R.id.ser_image);

        }
    }

//    public Rec_Reports_adapter(List<Reports> reportss) {
//        this.Doc = reportss;
//    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);


        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder( final MyViewHolder holder,  final   int position) {
        final GET_Services_Data get_services_data ;
        this.pos_try = position;
        name = ((Activity) mContext).getIntent().getStringExtra("name");
        email = ((Activity) mContext).getIntent().getStringExtra("email");
        com_name = ((Activity) mContext).getIntent().getStringExtra("com_name");
        password = ((Activity) mContext).getIntent().getStringExtra("password");
        address = ((Activity) mContext).getIntent().getStringExtra("address");
        mobile = ((Activity) mContext).getIntent().getStringExtra("mobile");
        Log.e("name", "" + name);
        Log.e("email", "" + email);
        Log.e("com_name", "" + com_name);
        Log.e("password", "" + password);
        Log.e("address", "" + address);
        Log.e("mobile", "" + mobile);
        get_services_data = Doc.get(pos_try);
        Log.e("Position","is "+pos_try);
        document = get_services_data.getService();
        StrictMode.setVmPolicy(builder.build());
        holder.name_of_doc.setText(get_services_data.getService());
//        Glide.with(mContext)
//                .load("https://sdltechserv.in/dgfeb/uploads/"+get_services_data.getImage())
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        // log exception
//                        Log.e("TAG", "Error loading image", e);
//                        return false; // important to return false so the error placeholder can be placed
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
//                .into(holder.ser_image);
        holder.name_of_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext() , Registration_Step_3.class);
                intent.putExtra("name" , name);
                intent.putExtra("com_name" , com_name);
                intent.putExtra("email" , email);
                intent.putExtra("password" , password);
                intent.putExtra("address" , address);
                intent.putExtra("mobile" , mobile);
                v.getContext().startActivity(intent);
            }
        });
        // notifyDataSetChanged();
    }





    @Override
    public int getItemCount() {
        return Doc.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return  position;
    }
}

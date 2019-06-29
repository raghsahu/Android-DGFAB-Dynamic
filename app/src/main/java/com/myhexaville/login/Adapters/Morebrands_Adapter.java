package com.myhexaville.login.Adapters;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myhexaville.login.Activities.LogandReg.Registration_Step_2;
import com.myhexaville.login.AllParsings.AddMoreBrands;
import com.myhexaville.login.AllParsings.GET_Services_Data;
import com.myhexaville.login.R;

import java.net.URL;
import java.util.List;

import static com.myhexaville.login.Activities.LogandReg.Registration_Step_3.Models;

public class Morebrands_Adapter extends RecyclerView.Adapter<Morebrands_Adapter.MyViewHolder> {
    private Context mContext;
    DownloadManager downloadManager;
    String image_url = "https://ihisaab.org//uploads/incomereport/";
    String image_url2 = "https://ihisaab.org//uploads/incomereport/";
    URL image_download_url;

    int pos_try;
    DownloadManager.Request request;
    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    String document;

    public List<AddMoreBrands> addMoreBrandsList;
    private ProgressDialog progressBar;


//    public Rec_Reports_adapter(Context gallery_act, List<Reports> Doc) {
//        mContext = gallery_act;
//      this.Doc = Doc;
//    }


    public Morebrands_Adapter(Context context, List<AddMoreBrands> addMoreBrands) {
        mContext=context;
        this.addMoreBrandsList = addMoreBrands;

        setHasStableIds(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText brandname ;
        ImageView addmore , removeless;

        public MyViewHolder(View itemView) {
            super(itemView);
            brandname =  itemView.findViewById(R.id.brandname);
            addmore =  itemView.findViewById(R.id.addmore);
            removeless =  itemView.findViewById(R.id.removeless);

        }
    }

//    public Rec_Reports_adapter(List<Reports> reportss) {
//        this.Doc = reportss;
//    }



    @Override
    public Morebrands_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addmorebrands, parent, false);


        return new Morebrands_Adapter.MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final Morebrands_Adapter.MyViewHolder holder, final   int position) {
        AddMoreBrands addMoreBrands = addMoreBrandsList.get(position);
       holder.addmore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Toast.makeText(mContext, "add more", Toast.LENGTH_SHORT).show();
               AddMoreBrands addMoreBrands1 = new AddMoreBrands(new EditText(v.getContext()),new ImageView(v.getContext()) ,new ImageView(v.getContext()));
//               addMoreBrands.setBrandname(new EditText(v.getContext()));
//               addMoreBrands.setAddmore(new ImageView(v.getContext()));
//               addMoreBrands.setRemoveless(new ImageView(v.getContext()));
               addMoreBrandsList.add(addMoreBrands1);
               notifyDataSetChanged();
           }
       });
       holder.removeless.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (position != 0) {
                   Toast.makeText(mContext, "add more", Toast.LENGTH_SHORT).show();
                   AddMoreBrands addMoreBrands1 = new AddMoreBrands(new EditText(v.getContext()), new ImageView(v.getContext()), new ImageView(v.getContext()));
//               addMoreBrands.setBrandname(new EditText(v.getContext()));
//               addMoreBrands.setAddmore(new ImageView(v.getContext()));
//               addMoreBrands.setRemoveless(new ImageView(v.getContext()));
                   addMoreBrandsList.remove(position);
                   if (holder.brandname.getText().length() > 0){
                       Models.add(holder.brandname.getText().toString());
                   }else{
                       Toast.makeText(mContext, "Caution! BrandName is empty", Toast.LENGTH_SHORT).show();
                   }
                   notifyDataSetChanged();
               }else {
                   Toast.makeText(mContext, "Atleat one name you should have", Toast.LENGTH_SHORT).show();
               }
           }
       });
        // notifyDataSetChanged();
    }





    @Override
    public int getItemCount() {
        return addMoreBrandsList.size();
    }

    @Override
    public long getItemId(int position) {
//        return super.getItemId(position);
        return  position;
    }
}

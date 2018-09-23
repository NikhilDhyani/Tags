package com.devdnik.coder.shopifytry.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devdnik.coder.shopifytry.DetailProduct;
import com.devdnik.coder.shopifytry.MainActivity;
import com.devdnik.coder.shopifytry.R;
import com.google.common.collect.Multimap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.devdnik.coder.shopifytry.MainActivity.fs;
import static com.devdnik.coder.shopifytry.MainActivity.mIdQTY;
import static com.devdnik.coder.shopifytry.MainActivity.mIdTitle;
import static com.devdnik.coder.shopifytry.MainActivity.multiMap;
import static com.devdnik.coder.shopifytry.MainActivity.mulyId;

/**
 * Created by NIKHIL on 23-09-2018.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {

//    private  Multimap<String, String> multiMap;

    Context context;

    private List<String> multiMaptag;
    /*
    public TagAdapter(Multimap<String, String> multiMap)
    {
        this.multiMap = multiMap;


    }
    */

    public TagAdapter(Context context,List<String >multiMap)
    {
        this.multiMaptag = multiMap;
        this.context  = context;


    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view =  inflater.inflate(R.layout.list_tags,parent,false);

        return new TagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, int position) {

       // Collection<String> tag = multiMap.get(fs.get(position).getTitle());

        //Set<String> keys = multiMap.keySet();


/*
        for (String entry : multiMap.keySet()) {

            Collection<String> value = multiMap.get(entry);
            System.out.print("NAPSTERX= "+entry + "Value Is  = " + value);
            // do stuff
            Log.d("NAPSTERY",entry);

           holder.tv.setText(entry);
       }
       */

         final String tagss = multiMaptag.get(position);
        holder.tv.setText(tagss);

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"ClickedNowYEs",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, DetailProduct.class);


                Bundle args = new Bundle();
                intent.putExtra("SELECTED",tagss);
                intent.putExtra("BUNDLE", (Serializable) multiMap);
                intent.putExtra("BUNDLES", (Serializable) mulyId);
                intent.putExtra("MID", (Serializable) mIdTitle);
                intent.putExtra("qty", (Serializable) mIdQTY);


                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return multiMaptag.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;
        public TagViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tagListTV);

        }
    }
}

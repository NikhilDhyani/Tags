package com.devdnik.coder.shopifytry.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devdnik.coder.shopifytry.R;

import java.util.Collection;
import java.util.List;

/**
 * Created by NIKHIL on 23-09-2018.
 */

public class qty_adapter extends RecyclerView.Adapter<qty_adapter.qtyViewHolder> {

    private List<String> multiMapT;
    private List<Integer> multiMapInventory;
    private List<String> mProductImage;
Context context;



    public qty_adapter(Context context,List<Integer> multiMap, List<String> multiMapT, List<String> mProductImage) {
        this.multiMapInventory = multiMap;
        this.multiMapT = multiMapT;
        this.mProductImage=mProductImage;
        this.context = context;

    }

    @Override
    public qtyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view =  inflater.inflate(R.layout.detail_qty,parent,false);

        return new qtyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(qtyViewHolder holder, int position) {


        Integer tagss = multiMapInventory.get(position);
        String tagTitless = multiMapT.get(position);
        String url =mProductImage.get(position);

       // Log.d("popat",multiMap.get(position).toString());
        Log.d("popa", String.valueOf(position));
        holder.tvs.setText(tagss.toString());
        holder.titleTVS.setText(tagTitless.toString());

        Glide.with((context)).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {

        Log.d("SIZESx", String.valueOf(multiMapInventory.size()));
        return multiMapInventory.size();

    }

    public class qtyViewHolder extends RecyclerView.ViewHolder {

        TextView tvs;
        TextView titleTVS;
        ImageView imageView;
        public qtyViewHolder(View itemView) {
            super(itemView);

            tvs = itemView.findViewById(R.id.detail_qty_TV);
            titleTVS = itemView.findViewById(R.id.detail_qty_Title);
            imageView = itemView.findViewById(R.id.Product_Image);




        }
    }
}

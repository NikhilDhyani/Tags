package com.devdnik.coder.shopifytry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.devdnik.coder.shopifytry.Adapter.qty_adapter;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.devdnik.coder.shopifytry.MainActivity.mIdImage;

public class DetailProduct extends AppCompatActivity {


    List<Long> tgQty ;
    List<Integer> totalInventory;
    List<String> productTitle;

    List<String> productImage;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        recyclerView  = findViewById(R.id.DRV);

        tgQty = new ArrayList<Long>();
        totalInventory = new ArrayList<Integer>();

        productTitle = new ArrayList<String>();

        productImage = new ArrayList<String>();




        Intent intent = getIntent();

        Multimap<String, String> multiMapx = (Multimap<String, String>) getIntent().getSerializableExtra("BUNDLE");
      //  Multimap<String, String> multiMaps = (Multimap<String, String>) getIntent().getSerializableExtra("BUNDLES");

        Multimap<String, Long> multiMaps = (Multimap<String, Long>) getIntent().getSerializableExtra("BUNDLES");

        Multimap<Long, String> midTitleD = (Multimap<Long, String>) getIntent().getSerializableExtra("MID");
        Multimap<Long, Integer> mQty = (Multimap<Long, Integer>) getIntent().getSerializableExtra("qty");
        String xyz = getIntent().getExtras().getString("SELECTED");



        System.out.println("In Detail");


        Set<String> keys = multiMapx.keySet();


        int i = 0;
        Set<Long> keyid = midTitleD.keySet();

       //  mQty.get(0);

        for (Long entry :  multiMaps.get(xyz)) {


            //tgQty.add(entry);
            tgQty.add(entry);
           // totalInventory.add(mQty.get(entry));

            for (Integer abc : mQty.get(entry))
            {
                Log.d("InventoryX", String.valueOf(abc));
                totalInventory.add(abc);


            }

            for (String opo : midTitleD.get(entry))

            {
                Log.d("InventoryTitle", opo);


                productTitle.add(opo);
            }

            for (String image : mIdImage.get(entry))
            {
                Log.d("ProductImageX",image);
                  productImage.add(image);
            }





            Log.d("ENTRYX", String.valueOf(entry));

        }
        multiMaps.get(xyz);



      //  System.out.println("Values at Aerodynamics " + multiMaps.get("Aerodynamic"));
      //  System.out.println("Values at Aerodynamics " + multiMaps.get("Aerodynamic"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  recyclerView.setAdapter(new qty_adapter(tgQty));
        recyclerView.setAdapter(new qty_adapter(getApplicationContext(),totalInventory,productTitle,productImage));


        //    Set<String> keys = multiMap.keySet();


    }


}

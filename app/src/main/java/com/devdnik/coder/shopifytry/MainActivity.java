package com.devdnik.coder.shopifytry;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.devdnik.coder.shopifytry.APIs.ShopifyClient;
import com.devdnik.coder.shopifytry.Adapter.TagAdapter;
import com.devdnik.coder.shopifytry.POJOss.ProductsItem;
import com.devdnik.coder.shopifytry.POJOss.ResponseTags;
import com.devdnik.coder.shopifytry.POJOss.VariantsItem;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    Map<String, List<String>> tagName = new HashMap<String, List<String>>();

    String accessToken = "c32313df0d0ef512ca64d5b336a0d7c6";

    public  static List<ProductsItem> fs;


    public static Multimap<String, String> multiMap = TreeMultimap.create();

    public static Multimap<String, Integer> multiTotal = ArrayListMultimap.create();

   public static Multimap<String, Long> mulyId = ArrayListMultimap.create();

    public  static Multimap<Long, String> mIdTitle = ArrayListMultimap.create();


    public  static Multimap<Long, String> mIdImage = ArrayListMultimap.create();




    public static     Multimap<Long, Integer> mIdQTY = ArrayListMultimap.create();

    List<VariantsItem> lVar;

    RecyclerView rv;

    int total;

    Button btn;

    List<String> tgListArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.mRV);

      //tgListArray = new ArrayList<>();

    tgListArray = new ArrayList<>();




       /*x btn = findViewById(R.id.Mbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DetailProduct.class);


                Bundle args = new Bundle();
                intent.putExtra("BUNDLE", (Serializable) multiMap);
                intent.putExtra("BUNDLES", (Serializable) mulyId);
                intent.putExtra("MID", (Serializable) mIdTitle);
                intent.putExtra("qty", (Serializable) mIdQTY);


                startActivity(intent);


            }
        });
       */

        //Creating An Instance Of Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ShopifyClient.base_url)
                .addConverterFactory(GsonConverterFactory.create()).build();


        ShopifyClient api = retrofit.create(ShopifyClient.class);

        Call<ResponseTags> call = api.getProduct("1", accessToken);




        call.enqueue(new Callback<ResponseTags>() {

            @Override
            public void onResponse(@NonNull Call<ResponseTags> call, @NonNull Response<ResponseTags> response) {
                ResponseTags tags = response.body();


                if (tags != null) {

                    //   Tags has list of all the products : Ex : 50

                    fs = tags.getProducts();
                }

                // Get The Size
                int sizes = fs.size();

                //Run loop for 50 times

                for (int i = 0; i < sizes; i++) {

                    //First Remove any space in array and then store it in an array

                    System.out.println("******I = " + i);

                    String witoutSpaceTags = fs.get(i).getTags().replaceAll(" ", "");

                    long x = fs.get(i).getId();

                    //Store individual tags separately [at different index]

                    String[] splittedV = witoutSpaceTags.split(",");

                    System.out.println("Without Split i =  " + i + " " + splittedV[0]);
                    System.out.println("Without Split i =  " + i + " " + splittedV[1]);
                    System.out.println("Without Split i =  " + i + " " + splittedV[2]);


                    //Get Variants Under Product and store in list

                    lVar = fs.get(i).getVariants();

                    //Fint How Many  Variant Are There

                    int varsize = lVar.size();

                    total = 0;

                    for (int j = 0; j < varsize; j++) {

                        //Get Total Avl. Inventory Under All Variants

                        total = total + lVar.get(j).getInventoryQuantity();

                    }

                    //Find Unique Title Of Product

                    multiTotal.put(fs.get(i).getTitle(), total);


                    //This loop will run 3 times bcoz of 3 tags

                    for (String animalX : splittedV) {


                        multiMap.put(animalX, fs.get(i).getTitle());

                        //Storing  tag as key and  id as value

                        mulyId.put(animalX, fs.get(i).getId());
                        System.out.println("Values of Id = " + mulyId.get(animalX));

                        //Storing id as key and title as value

                        if ((mIdTitle.get(fs.get(i).getId()).isEmpty())) {

                            mIdTitle.put(fs.get(i).getId(), fs.get(i).getTitle());
                            System.out.println("Values of Title = " + mIdTitle.get(fs.get(i).getId()));


                            //storing id as key and total as value

                            mIdQTY.put(fs.get(i).getId(), total);

                            mIdImage.put(fs.get(i).getId(),fs.get(i).getImage().getSrc());
                            System.out.println("Values of Title = " + mIdQTY.get(fs.get(i).getId()));
                        } else {
                            System.out.println("MidTitleNow Is " + mIdTitle.get(fs.get(i).getId()));
                        }
                    }


                }




                System.out.println("Fetching Keys and corresponding [Multiple] Values n");


                System.out.println("Aerodynamic Key = " + mulyId.get("Aerodynamic"));
                System.out.println("knife Key = " + mulyId.get("Knife"));
                System.out.println("Ergonomic Key = " + mulyId.get("Ergonomic"));

                System.out.println("Concerete Key = " + mulyId.get("Concrete"));
                System.out.println("Wallet Key = " + mulyId.get("Wallet"));

                System.out.println("Wallet Key = " + mulyId.get("Plate"));

                System.out.println("NEXTONE");

                for (String entry : multiMap.keySet()) {

                    Collection<String> value = multiMap.get(entry);
                    System.out.print("NAPSTERX= "+entry + "Value Is  = " + value);
                    // do stuff
                    Log.d("NAPSTERZ",entry);

                    tgListArray.add(entry);


                }

                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
             //   rv.setAdapter(new TagAdapter(multiMap));

                rv.setAdapter(new TagAdapter(getApplicationContext(),tgListArray));
                Set<String> keys = multiMap.keySet();

                int i = 0;
                // iterate through the key set and display key and values
                for (String key : keys) {
                    System.out.println("KeyD = " + key);
                    System.out.println("Values at i = " + i + " " + multiMap.get(key));


                    i = i + 1;
                }





            }


            @Override
            public void onFailure(Call<ResponseTags> call, Throwable t) {

            }
        });


    }


    public void perform_action(View view)
    {
        TextView tv= (TextView) findViewById(R.id.tagListTV);

        Log.d("miText",tv.getText().toString());
        //alter text of textview widget
     //   Toast.makeText(getApplicationContext(),"Clciked",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, DetailProduct.class);


        Bundle args = new Bundle();
        intent.putExtra("SELECTED",tv.getText().toString());
        intent.putExtra("BUNDLE", (Serializable) multiMap);
        intent.putExtra("BUNDLES", (Serializable) mulyId);
        intent.putExtra("MID", (Serializable) mIdTitle);
        intent.putExtra("qty", (Serializable) mIdQTY);


        startActivity(intent);

    }


}


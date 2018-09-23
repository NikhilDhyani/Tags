package com.devdnik.coder.shopifytry.APIs;

import com.devdnik.coder.shopifytry.POJOss.ProductsItem;
import com.devdnik.coder.shopifytry.POJOss.ResponseTags;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by NIKHIL on 21-09-2018.
 */

public interface ShopifyClient {

    //Base Url

    String base_url  = "https://shopicruit.myshopify.com/admin/";

    //Access Token

    String accessToken = "c32313df0d0ef512ca64d5b336a0d7c6";

    /* This is the url we want to access

    https://shopicruit.myshopify.com/admin/products.json/?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6

    */
    @GET("products.json")

    Call<ResponseTags>getProduct(@Query("page") String page,@Query("access_token") String accessToken);



   // @GET("products.json?page=1&access_token="+accessToken)

  //  Call<ResponseTags>getProduct();

}

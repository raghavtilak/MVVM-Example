package com.aaraghav.mvvmexample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//https://list.ly/api/v4/search/image?q=basketball
    public static String api="https://list.ly/api/v4/search/";

    private static APIService service=null;

    public static APIService getService() {
        if(service==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(api)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service=retrofit.create(APIService.class);
        }
        return service;
    }
}

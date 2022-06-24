package com.aaraghav.mvvmexample.network;

import com.aaraghav.mvvmexample.model.Content;
import com.aaraghav.mvvmexample.model.OuterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("image")
    Call<OuterModel> getContentList(@Query("q") String name);


}

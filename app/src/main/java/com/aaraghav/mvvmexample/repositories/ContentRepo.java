package com.aaraghav.mvvmexample.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aaraghav.mvvmexample.model.Content;
import com.aaraghav.mvvmexample.model.OuterModel;
import com.aaraghav.mvvmexample.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentRepo {

    private MutableLiveData<List<Content>> mutableLiveData;

    //this method is called everytime orientation changes,or similar changes
    public LiveData<List<Content>> getContents(String q) {
        if(mutableLiveData==null){
            mutableLiveData=new MutableLiveData<>();
            loadData(q);
        }
        return mutableLiveData;
    }

    public void getContentsByQuery(String q) {

            loadData(q);

    }

    //Make the API call/RoomDB/FakeData
    private void loadData(String q) {

        Log.d("TAG", "loadData: ");
        Call<OuterModel> call=RetrofitInstance.getService().getContentList(q);
        call.enqueue(new Callback<OuterModel>() {
            @Override
            public void onResponse(Call<OuterModel> call, Response<OuterModel> response) {
                //postValue()-> when using api calls
                //setValue()-> whne using static data
                Log.d("TAG", "onResponse: "+call.request().url());

                Log.d("TAG", "onResponse: "+response.message());
                OuterModel m= response.body();

                for(Content c: m.getList()){
                    Log.d("TAG", "onResponse: "+c.toString());
                }
                mutableLiveData.postValue(m.getList());
            }

            @Override
            public void onFailure(Call<OuterModel> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.d("TAG", "onFailure:"+t.getMessage());
                t.fillInStackTrace().printStackTrace();

                Log.d("TAG", "onFailure: failed");
            }
        });

    }
}

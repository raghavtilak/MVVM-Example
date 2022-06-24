package com.aaraghav.mvvmexample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aaraghav.mvvmexample.model.Content;
import com.aaraghav.mvvmexample.repositories.ContentRepo;

import java.util.ArrayList;
import java.util.List;

public class ContentViewModel extends ViewModel {

    ContentRepo contentRepo=new ContentRepo();

    public LiveData<List<Content>> getContent(String q){
        return contentRepo.getContents(q);
    }

    public void getContentsByQuery(String q) {

        contentRepo.getContentsByQuery(q);

    }

}

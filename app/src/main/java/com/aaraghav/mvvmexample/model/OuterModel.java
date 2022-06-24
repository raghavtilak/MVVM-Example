package com.aaraghav.mvvmexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OuterModel {

    @SerializedName("results")
    List<Content> list;

    public List<Content> getList() {
        return list;
    }

    public void setList(List<Content> list) {
        this.list = list;
    }
}

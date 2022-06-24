package com.aaraghav.mvvmexample.model;

import android.graphics.Movie;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.aaraghav.mvvmexample.GlideApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

public class Content {

    @SerializedName("media_id")
    String id;
    @SerializedName("media_source")
    String title;
    @SerializedName("image")
    String imgurl;

    public Content(String id, String title, String imgurl) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.imgurl = imgurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgurl='" + imgurl + '\'' +
                '}';
    }

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView view,String url){
        Log.d("URL", "loadImage: "+url);
        GlideApp.with(view).load(url).into(view);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return id.equals(content.id) && title.equals(content.title) && imgurl.equals(content.imgurl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, imgurl);
    }

    public static DiffUtil.ItemCallback<Content> itemCallback=new DiffUtil.ItemCallback<Content>() {
        @Override
        public boolean areItemsTheSame(@NonNull Content oldItem, @NonNull Content newItem) {

            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Content oldItem, @NonNull Content newItem) {
            return oldItem.equals(newItem);
        }
    };
}

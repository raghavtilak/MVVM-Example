package com.aaraghav.mvvmexample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aaraghav.mvvmexample.databinding.ItemLayoutBinding;
import com.aaraghav.mvvmexample.model.Content;

import java.util.ArrayList;

public class ContentAdapter extends ListAdapter<Content, ContentAdapter.ViewHolder> {

    OnItemClickListener onItemClickListener;

    public ContentAdapter(@NonNull DiffUtil.ItemCallback<Content> diffCallback, OnItemClickListener onItemClickListener) {
        super(diffCallback);
        this.onItemClickListener = onItemClickListener;

    }


//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        ItemLayoutBinding binding = ItemLayoutBinding.inflate(inflater, parent, false);
//        return new ViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Content c= list.get(position);
//        holder.binding.setItem(c);
//        holder.binding.executePendingBindings();
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ItemLayoutBinding binding=ItemLayoutBinding.inflate(inflater,parent,false);
        binding.setOnCLickListener(onItemClickListener);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.setItem(this.getItem(position));
        holder.binding.executePendingBindings();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemLayoutBinding binding;

        public ViewHolder(@NonNull ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Content item);
    }
}

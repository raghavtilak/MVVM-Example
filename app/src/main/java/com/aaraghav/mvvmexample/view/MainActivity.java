package com.aaraghav.mvvmexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.aaraghav.mvvmexample.ConnectivityStatus;
import com.aaraghav.mvvmexample.R;
import com.aaraghav.mvvmexample.adapter.ContentAdapter;
import com.aaraghav.mvvmexample.databinding.ActivityMainBinding;
import com.aaraghav.mvvmexample.model.Content;
import com.aaraghav.mvvmexample.viewmodel.ContentViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ContentAdapter.OnItemClickListener {

    ActivityMainBinding binding;
    ContentAdapter adapter;
    ContentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter=new ContentAdapter(Content.itemCallback,this);

        viewModel=new ViewModelProvider(this).get(ContentViewModel.class);

        ConnectivityStatus status=new ConnectivityStatus(this);
        status.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.setStatus(aBoolean);
            }
        });


        viewModel.getContent("basketball").observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contents) {
                adapter.submitList(contents);
            }
        });

        binding.recyclerView.setAdapter(adapter);

        binding.submit.setOnClickListener(view -> {
            String s=binding.editText.getText().toString();
            viewModel.getContentsByQuery(s);
        });
    }

    @Override
    public void onItemClick(Content item) {
        Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
    }


}
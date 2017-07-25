package com.almaz.vktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.almaz.vktest.wallpostdb.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;


public class PostItem  extends AppCompatActivity {


    Post mPost;
    RecyclerView recyclerView;
    PostItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_item);



        recyclerView = (RecyclerView) findViewById(R.id.post_item);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        adapter = new PostItemAdapter(new com.almaz.vktest.wallpostdb.Response());
        recyclerView.setAdapter(adapter);

        VKRequest request = VKApi.wall().getById(VKParameters.from(VKApiConst.POSTS, String.valueOf(getIntent().getIntExtra("EXTRA_ID",0)) + "_" + String.valueOf(getIntent().getIntExtra("EXTRA_POST",0))));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                Log.i("open", String.valueOf(getIntent().getIntExtra("EXTRA_ID",0)));
                Gson gson = new GsonBuilder().create();

                mPost = gson.fromJson(response.responseString, Post.class);

                adapter.changeDataSet(mPost.getResponse().get(0));
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });
    }


}

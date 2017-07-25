package com.almaz.vktest;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.almaz.vktest.newsfeeddb.Item;
import com.almaz.vktest.newsfeeddb.Response;
import com.almaz.vktest.newsfeeddb.VkNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

public class MainActivity extends AppCompatActivity{

    private String[] scope = new String[]{VKScope.FRIENDS,VKScope.WALL};

    RecyclerView recyclerView;
    NewsAdapter adapter;
    VkNews mVkNews;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Boolean isLoading;
    String form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VKSdk.login(this,scope);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                VKRequest request = new VKRequest("newsfeed.get", VKParameters.from(VKApiConst.FILTERS, "post", VKApiConst.COUNT, "20", VKApiConst.FIELDS, "name,first_name,last_name"));
                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        Gson gson = new GsonBuilder().create();

                        mVkNews = gson.fromJson(response.responseString, VkNews.class);
                        adapter.changeDataSet(mVkNews.getResponse());
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });

                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NewsAdapter(new Response(), new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                Intent i = new Intent(MainActivity.this, PostItem.class);
                if(item.getCopyHistory()==null) {
                    i.putExtra("EXTRA_ID", item.getSourceId());
                    i.putExtra("EXTRA_POST", item.getPostId());
                }else{
                    i.putExtra("EXTRA_ID", item.getCopyHistory().get(0).getOwnerId());
                    i.putExtra("EXTRA_POST", item.getCopyHistory().get(0).getId());
                }

                startActivity(i);
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();//смотрим сколько элементов на экране
                int totalItemCount = layoutManager.getItemCount();//сколько всего элементов
                int firstVisibleItems = layoutManager.findFirstVisibleItemPosition();//какая позиция первого элемента

                if (!isLoading) {//проверяем, грузим мы что-то или нет, эта переменная должна быть вне класса  OnScrollListener
                    if ( (visibleItemCount+firstVisibleItems) >= totalItemCount) {
                        isLoading = true;//ставим флаг что мы попросили еще элемены

                        VKParameters parameters = new VKParameters();
                        parameters.put("start_from", form );
                        parameters.put("filters", "post");
                        parameters.put("count", 2);
                        parameters.put("fields", "name,first_name,last_name");

                        VKRequest request = new VKRequest("newsfeed.get", parameters);
                        request.executeWithListener(new VKRequest.VKRequestListener() {
                            @Override
                            public void onComplete(VKResponse response) {
                                super.onComplete(response);

                                Gson gson = new GsonBuilder().create();
                                mVkNews = gson.fromJson(response.responseString, VkNews.class);

                            }
                        });

                        adapter.addDataSet(mVkNews.getResponse(), layoutManager.getItemCount());
                        form = mVkNews.getResponse().getNextFrom();
                        isLoading = false;
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {

                VKRequest request = new VKRequest("newsfeed.get", VKParameters.from(VKApiConst.FILTERS, "post", VKApiConst.COUNT, "2", VKApiConst.FIELDS, "name,first_name,last_name"));
                request.executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);

                        Gson gson = new GsonBuilder().create();

                        mVkNews = gson.fromJson(response.responseString, VkNews.class);
                        adapter.changeDataSet(mVkNews.getResponse());
                        recyclerView.getAdapter().notifyDataSetChanged();
                        isLoading = false;
                        form = mVkNews.getResponse().getNextFrom();
                    }
                });


            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_SHORT).show();
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("LMKey", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Parcelable recyclerViewLayoutState = savedInstanceState.getParcelable("LMKey");
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewLayoutState);
    }


}

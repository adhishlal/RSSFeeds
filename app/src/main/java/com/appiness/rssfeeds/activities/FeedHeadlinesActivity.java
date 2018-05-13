package com.appiness.rssfeeds.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.appiness.rssfeeds.R;
import com.appiness.rssfeeds.adapters.FeedsAdapter;
import com.appiness.rssfeeds.models.FeedsRecyclerModel;
import com.appiness.rssfeeds.networkhelper.responsemodels.RetrofitResponse;
import com.appiness.rssfeeds.networkhelper.retrofithelpers.RetrofitClient;
import com.appiness.rssfeeds.networkhelper.retrofithelpers.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedHeadlinesActivity extends AppCompatActivity {

    RecyclerView rvHeadlines;
    SwipeRefreshLayout swiperefresh;
    FeedsAdapter feedsAdapter;
    List<FeedsRecyclerModel> feedsRecyclerModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_headlines);

        initializeViews();

        feedsRecyclerModelList = new ArrayList<>();



        rvHeadlines.setLayoutManager(new LinearLayoutManager(FeedHeadlinesActivity.this, LinearLayoutManager.VERTICAL, false));

        feedsAdapter = new FeedsAdapter(feedsRecyclerModelList);
        rvHeadlines.setAdapter(feedsAdapter);

        loadFeeds();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                feedsRecyclerModelList.clear();
                loadFeeds();
            }
        });
    }



    private void initializeViews()
    {
        rvHeadlines = findViewById(R.id.rvHeadlines);
        swiperefresh = findViewById(R.id.swiperefresh);
    }




    private void loadFeeds() {

        Call<RetrofitResponse> call = RetrofitClient.getClient().create(RetrofitInterface.class).loadHeadlines();

        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {


                swiperefresh.setRefreshing(false);
                if (response.isSuccessful()) {

                    RetrofitResponse retrofitResponse = response.body();

                    int i;
                    for(i=0 ; i<retrofitResponse.getItemLists().size(); i++)
                    {

                        FeedsRecyclerModel feedsRecyclerModel = new FeedsRecyclerModel();

                        String ID = retrofitResponse.getItemLists().get(i).getId();
                        String LINK = retrofitResponse.getItemLists().get(i).getLink();
                        String TITLE = retrofitResponse.getItemLists().get(i).getTitle();
                        String DATE = retrofitResponse.getItemLists().get(i).getDate();

                        feedsRecyclerModel.setId(ID);
                        feedsRecyclerModel.setTitle(TITLE);
                        feedsRecyclerModel.setDate(DATE);
                        feedsRecyclerModel.setLink(LINK);

                        feedsRecyclerModelList.add(feedsRecyclerModel);

                    }

                    feedsAdapter.notifyDataSetChanged();



                } else {

                    Toast.makeText(FeedHeadlinesActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {

                swiperefresh.setRefreshing(false);
                Toast.makeText(FeedHeadlinesActivity.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

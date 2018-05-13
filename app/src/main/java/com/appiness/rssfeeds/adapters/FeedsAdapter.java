package com.appiness.rssfeeds.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appiness.rssfeeds.R;
import com.appiness.rssfeeds.models.FeedsRecyclerModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.MyViewHolder> {

    private List<FeedsRecyclerModel> feedsList;

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvHeading, tvMonthYear, tvDate;
        public View coloredView;
        public LinearLayout llItem;

        public MyViewHolder(View view) {
            super(view);

            tvHeading = view.findViewById(R.id.tvHeading);
            tvMonthYear = view.findViewById(R.id.tvMonthYear);
            tvDate = view.findViewById(R.id.tvDate);
            coloredView = view.findViewById(R.id.coloredView);
            llItem = view.findViewById(R.id.llItem);

            context = view.getContext();

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.headline_item, parent, false);

        return new MyViewHolder(itemView);
    }


    public FeedsAdapter(List<FeedsRecyclerModel> feedsList) {
        this.feedsList = feedsList;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final FeedsRecyclerModel feedsListModel = feedsList.get(position);


        holder.tvHeading.setText(feedsListModel.getTitle());
        holder.tvDate.setText(feedsListModel.getDate());

        String completeDate = feedsListModel.getDate();

        holder.tvMonthYear.setText(completeDate.substring(7,completeDate.indexOf(" ",14)));


        String date = completeDate.substring(5, completeDate.indexOf(" ",6));

        if(date.length() != 2)
        {
            date = "0"+date;
        }

        holder.tvDate.setText(date);


        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = feedsListModel.getLink();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
        });


        List<Integer> givenList = Arrays.asList(R.color.light_blue_100, R.color.yellow_700, R.color.yellow_900,R.color.light_green_200, R.color.pink_A200);


        Random rand = new Random();
        int randomElement = givenList.get(rand.nextInt(givenList.size()));


        holder.coloredView.setBackgroundColor(context.getResources().getColor(randomElement));







    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }
}
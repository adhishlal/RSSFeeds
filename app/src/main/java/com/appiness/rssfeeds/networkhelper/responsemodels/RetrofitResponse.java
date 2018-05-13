package com.appiness.rssfeeds.networkhelper.responsemodels;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RetrofitResponse {


    @SerializedName("items")
    private ArrayList<ItemList> itemLists;

    public ArrayList<ItemList> getItemLists() {
        return itemLists;
    }

    public class ItemList
    {
        @SerializedName("title")
        private String title;

        @SerializedName("link")
        private String link;

        @SerializedName("date")
        private String date;

        @SerializedName("id")
        private String id;


        public String getTitle() {
            return title;
        }

        public String getLink() {
            return link;
        }

        public String getDate() {
            return date;
        }

        public String getId() {
            return id;
        }
    }
}

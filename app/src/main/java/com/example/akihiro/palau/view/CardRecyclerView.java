package com.example.akihiro.palau.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CardRecyclerView extends RecyclerView {
    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRecyclerAdapter(context);
    }

    public void setRecyclerAdapter(Context context){
        setLayoutManager(new LinearLayoutManager(context));
//        setAdapter(new RankingListRecyclerAdapter(context,new String[]{"aaa","vvv","ddd","vvv","ddd","vvv","ddd","vvv","ddd","vvv","ddd"}));
    }
}
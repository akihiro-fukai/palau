package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.response.item.Ranking;
import com.example.akihiro.palau.net.response.item.RankingDetail;

import java.util.List;

public class RankingCardRecyclerAdapter extends RecyclerView.Adapter<RankingCardRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<RankingDetail> mRankingDetails;

    public RankingCardRecyclerAdapter(Context context, List<RankingDetail> rankingDetails) {
        super();

        this.mRankingDetails = rankingDetails;
        mContext = context;
    }

    @Override
    public int getItemCount() {

        return mRankingDetails.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.textView_main.setText(mRankingDetails.get(position).getTitle());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, mRankingDetails.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RankingCardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_ranking_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_main;
        LinearLayout layout;

        public ViewHolder(View v) {

            super(v);

            textView_main = (TextView) v.findViewById(R.id.textView_main);
            layout = (LinearLayout) v.findViewById(R.id.layout);
        }
    }
}
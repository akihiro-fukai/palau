package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.fragment.dialog.RankingDetailDialogFragment;
import com.example.akihiro.palau.net.common.End;
import com.example.akihiro.palau.net.common.Genre;
import com.example.akihiro.palau.net.response.item.RankingDetail;

import java.util.List;

public class RankingCardRecyclerAdapter extends RecyclerView.Adapter<RankingCardRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private List<RankingDetail> mRankingDetails;

    public RankingCardRecyclerAdapter(Context context, FragmentManager fragmentManager, List<RankingDetail> rankingDetails) {
        super();

        mContext = context;
        mFragmentManager = fragmentManager;
        mRankingDetails = rankingDetails;
    }

    @Override
    public int getItemCount() {

        return mRankingDetails.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final RankingDetail rankingDetail = mRankingDetails.get(position);

        int ranking = rankingDetail.getRank();
        switch (ranking) {

            case 1:
                viewHolder.rankingCrown.setImageResource(R.drawable.ic_crown_gold);
                viewHolder.rankingCrown.setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHolder.rankingCrown.setImageResource(R.drawable.ic_crown_silver);
                viewHolder.rankingCrown.setVisibility(View.VISIBLE);
                break;
            case 3:
                viewHolder.rankingCrown.setImageResource(R.drawable.ic_crown_bronze);
                viewHolder.rankingCrown.setVisibility(View.VISIBLE);
                break;
            default:
                viewHolder.rankingCrown.setVisibility(View.GONE);
                break;
        }

        String rankingOrder = mContext.getResources().getString(R.string.ranking_order);
        viewHolder.ranking.setText(String.format(rankingOrder, ranking));

        viewHolder.novelTitle.setText(rankingDetail.getTitle());
        viewHolder.novelWriter.setText(rankingDetail.getWriter());

        int genreTextId = Genre.getGenreTextId(rankingDetail.getGenre());
        String genre = mContext.getResources().getString(genreTextId);
        viewHolder.novelGenre.setText(genre);

        String novelInfo = mContext.getResources().getString(R.string.ranking_info);
        int statusTextId = End.getStatusTextId(rankingDetail.getEnd(), rankingDetail.getNovelType());
        String status = mContext.getResources().getString(statusTextId);
        viewHolder.novelInfo.setText(String.format(novelInfo, status, rankingDetail.getGeneralAllNo()));

        viewHolder.novelLastUp.setText(rankingDetail.getGeneralLastup());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RankingDetailDialogFragment fragment = new RankingDetailDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("test", rankingDetail);
                fragment.setArguments(bundle);

                fragment.show(mFragmentManager, "tag");
            }
        });
    }

    @Override
    public RankingCardRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_ranking_list_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView rankingCrown;
        TextView ranking;
        TextView novelTitle;
        TextView novelWriter;
        TextView novelGenre;
        TextView novelInfo;
        TextView novelLastUp;

        ViewHolder(View v) {

            super(v);

            cardView = (CardView) v.findViewById(R.id.card_view);
            rankingCrown = (ImageView) v.findViewById(R.id.ranking_crown);
            ranking = (TextView) v.findViewById(R.id.ranking);
            novelWriter = (TextView) v.findViewById(R.id.novel_writer);
            novelGenre = (TextView) v.findViewById(R.id.novel_genre);
            novelTitle = (TextView) v.findViewById(R.id.novel_title);
            novelInfo = (TextView) v.findViewById(R.id.novel_status);
            novelLastUp = (TextView) v.findViewById(R.id.novel_last_up);
        }
    }
}
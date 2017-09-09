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
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.List;

import static com.example.akihiro.palau.common.UICommonUtil.BUNDLE_NNOVEL_DETAIL;

public class RankingListRecyclerAdapter extends RecyclerView.Adapter<RankingListRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private FragmentManager mFragmentManager;
    private List<NovelDetail> mNovelDetails;

    public RankingListRecyclerAdapter(Context context, FragmentManager fragmentManager, List<NovelDetail> novelDetails) {
        super();

        mContext = context;
        mFragmentManager = fragmentManager;
        mNovelDetails = novelDetails;
    }

    @Override
    public int getItemCount() {

        return mNovelDetails.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final NovelDetail novelDetail = mNovelDetails.get(position);

        int ranking = novelDetail.getRank();
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

        viewHolder.novelTitle.setText(novelDetail.getTitle());
        viewHolder.novelWriter.setText(novelDetail.getWriter());

        int genreTextId = Genre.getGenreTextId(novelDetail.getGenre());
        String genre = mContext.getResources().getString(genreTextId);
        viewHolder.novelGenre.setText(genre);

        String novelInfo = mContext.getResources().getString(R.string.ranking_info);
        int statusTextId = End.getStatusTextId(novelDetail.getEnd(), novelDetail.getNovelType());
        String status = mContext.getResources().getString(statusTextId);
        viewHolder.novelInfo.setText(String.format(novelInfo, status, novelDetail.getGeneralAllNo()));

        viewHolder.novelLastUp.setText(novelDetail.getGeneralLastup());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RankingDetailDialogFragment fragment = new RankingDetailDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(BUNDLE_NNOVEL_DETAIL, novelDetail);
                fragment.setArguments(bundle);

                fragment.show(mFragmentManager, "tag");
            }
        });
    }

    @Override
    public RankingListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
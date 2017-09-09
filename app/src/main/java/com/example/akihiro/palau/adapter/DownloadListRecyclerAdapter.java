package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.activity.NovelContentsActivity;
import com.example.akihiro.palau.net.common.End;
import com.example.akihiro.palau.net.common.Genre;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.List;

import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_NCODE;

public class DownloadListRecyclerAdapter extends RecyclerView.Adapter<DownloadListRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<NovelDetail> mNovelDetails;

    public DownloadListRecyclerAdapter(Context context, List<NovelDetail> novelDetails) {
        super();

        mContext = context;
        mNovelDetails = novelDetails;
    }

    @Override
    public int getItemCount() {

        if (null != mNovelDetails) {

            return mNovelDetails.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final NovelDetail novelDetail = mNovelDetails.get(position);

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

                Intent intent = new Intent(mContext, NovelContentsActivity.class);
                intent.putExtra(NOVEL_NCODE, novelDetail.getNCode());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public DownloadListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_download_list_item, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView novelTitle;
        TextView novelWriter;
        TextView novelGenre;
        TextView novelInfo;
        TextView novelLastUp;

        ViewHolder(View v) {

            super(v);

            cardView = (CardView) v.findViewById(R.id.card_view);
            novelWriter = (TextView) v.findViewById(R.id.novel_writer);
            novelGenre = (TextView) v.findViewById(R.id.novel_genre);
            novelTitle = (TextView) v.findViewById(R.id.novel_title);
            novelInfo = (TextView) v.findViewById(R.id.novel_status);
            novelLastUp = (TextView) v.findViewById(R.id.novel_last_up);
        }
    }
}
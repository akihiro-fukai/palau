package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.common.End;
import com.example.akihiro.palau.net.common.Genre;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.List;

import narou4j.entities.NovelBody;

public class NovelPageAdapter extends RecyclerView.Adapter<NovelPageAdapter.ViewHolder> {

    private Context mContext;
    private List<NovelBody> mNovelBodies;

    public NovelPageAdapter(Context context, List<NovelBody> novelBodies) {
        super();

        mContext = context;
        mNovelBodies = novelBodies;
    }

    @Override
    public int getItemCount() {

        return mNovelBodies.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final NovelBody novelBody = mNovelBodies.get(position);

        viewHolder.novelTitle.setText(novelBody.getTitle());
    }

    @Override
    public NovelPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_top_page_download_list_item, parent, false);

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
package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.activity.NovelPageActivity;

import java.util.List;

import narou4j.entities.NovelBody;

import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_NCODE;
import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_PAGE;

/**
 * NovelContentsRecyclerAdapterクラスは目次一覧を表示する機能を提供します。
 */
public class NovelContentsRecyclerAdapter extends RecyclerView.Adapter<NovelContentsRecyclerAdapter.ViewHolder> {

    // コンテキスト
    private Context mContext;
    // Nコード
    private String mNCode;
    // 小説詳細情報
    private List<NovelBody> mNovelBodies;

    /**
     * コンストラクタ
     *
     * @param context     コンテキスト
     * @param nCode       Nコード
     * @param novelBodies 小説詳細情報
     */
    public NovelContentsRecyclerAdapter(Context context, String nCode, List<NovelBody> novelBodies) {
        super();

        mContext = context;
        mNCode = nCode;
        mNovelBodies = novelBodies;
    }

    @Override
    public int getItemCount() {

        return mNovelBodies.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final NovelBody novelBody = mNovelBodies.get(position);
        final int page = position;

        viewHolder.pageTitle.setText(novelBody.getTitle());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, NovelPageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(NOVEL_NCODE, mNCode);
                bundle.putInt(NOVEL_PAGE, page);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public NovelContentsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.view_novel_page_list_item, parent, false);

        return new ViewHolder(view);
    }

    /**
     * ViewHolderクラスは目次情報を保持します。
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        // カードビュー
        CardView cardView;
        // ページタイトル
        TextView pageTitle;

        ViewHolder(View v) {

            super(v);

            cardView = (CardView) v.findViewById(R.id.card_view);
            pageTitle = (TextView) v.findViewById(R.id.page_title);
        }
    }
}
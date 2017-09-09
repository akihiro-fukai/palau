package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akihiro.palau.R;

import java.util.List;

import narou4j.entities.NovelBody;

public class NovelBodyFragment extends Fragment {

    private NovelBody mNovelBody;

    public void setNovelBody(NovelBody novelBody) {

        mNovelBody = novelBody;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_novel_body, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // タイトル
        TextView novelPageTitle = (TextView) getView().findViewById(R.id.novel_page_title);
        novelPageTitle.setText(mNovelBody.getTitle());

        // 本文
        TextView novelBody = (TextView) getView().findViewById(R.id.novel_page_body);
        novelBody.setText(mNovelBody.getBody());
    }
}

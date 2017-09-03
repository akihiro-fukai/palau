package com.example.akihiro.palau.fragment;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.adapter.DownloadListAdapter;
import com.example.akihiro.palau.database.NarouDao;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.List;

public class FragmentTopPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_top_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AsyncNovelLoadTask novelLoadTask = new AsyncNovelLoadTask();
        novelLoadTask.execute();
    }

    private class AsyncNovelLoadTask extends AsyncTask<Void, Void, List<NovelDetail>> {

        @Override
        protected List<NovelDetail> doInBackground(Void... voids) {

            NarouDao dao = new NarouDao(getContext());
            return dao.findAllNovelDetail();
        }

        @Override
        protected void onPostExecute(List<NovelDetail> novelDetails) {

            setExpandableList(novelDetails);
        }

        /**
         * ダウンロードした小説一覧を表示します。
         *
         * @param novelDetails 小説の基本情報
         */
        private void setExpandableList(List<NovelDetail> novelDetails) {

            DownloadListAdapter adapter = new DownloadListAdapter(getContext(), novelDetails);
            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.top_page_download_recycler_view);
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}

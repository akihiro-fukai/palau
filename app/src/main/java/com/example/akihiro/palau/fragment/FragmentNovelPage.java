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
import com.example.akihiro.palau.adapter.NovelPageAdapter;
import com.example.akihiro.palau.database.NarouDao;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.List;

import narou4j.entities.NovelBody;

import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_PAGE_NCODE;

public class FragmentNovelPage extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_novel_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String nCode = null;
        Bundle bundle = getArguments();
        if (null != bundle && bundle.containsKey(NOVEL_PAGE_NCODE)) {

            nCode = bundle.getString(NOVEL_PAGE_NCODE);
        }

        AsyncNovelLoadTask novelLoadTask = new AsyncNovelLoadTask(nCode);
        novelLoadTask.execute();
    }

    private class AsyncNovelLoadTask extends AsyncTask<Void, Void, List<NovelBody>> {

        private String mNCode;

        public AsyncNovelLoadTask(String nCode) {

            mNCode = nCode;
        }

        @Override
        protected List<NovelBody> doInBackground(Void... voids) {

            NarouDao dao = new NarouDao(getContext());
            return dao.findNovelBodyByNCode(mNCode);
        }

        @Override
        protected void onPostExecute(List<NovelBody> novelBodies) {

            setExpandableList(novelBodies);
        }

        /**
         * ダウンロードした小説一覧を表示します。
         *
         * @param novelBodies 小説の基本情報
         */
        private void setExpandableList(List<NovelBody> novelBodies) {

            NovelPageAdapter adapter = new NovelPageAdapter(getContext(), novelBodies);
            RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.novel_page_recycler_view);
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}

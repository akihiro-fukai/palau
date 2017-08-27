package com.example.akihiro.palau.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akihiro.palau.net.HttpApiClient;
import com.example.akihiro.palau.net.common.RequestParam;
import com.example.akihiro.palau.net.common.RequestType;
import com.example.akihiro.palau.net.response.item.RankingDetail;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public abstract class FragmentBase extends Fragment {

    private RequestType mRequestType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Resources r = getResources();

        int titleResId = getTitleResId();

        return inflater.inflate(getLayoutResId(), container, false);
    }

    protected void httpRequest(String host, HashMap<RequestParam, String> params, RequestType requestType) {

        mRequestType = requestType;

        StringBuilder stringBuilder = new StringBuilder(host);

        if (null != params) {

            Set<RequestParam> requestParams = params.keySet();
            for (RequestParam requestParam : requestParams) {

                String key = requestParam.toString();
                String value = params.get(requestParam);
                stringBuilder.append(String.format("&%s=%s", key, value));
            }
        }

        HttpApiClient httpApiClient = new HttpApiClient();
        httpApiClient.setOnHttpApiListener(mOnHttpApiListener);
        httpApiClient.execute(stringBuilder.toString());
    }

    private HttpApiClient.OnHttpApiListener mOnHttpApiListener = new HttpApiClient.OnHttpApiListener() {

        @Override
        public void onPostExecute(String result) {

            if (null != result) {

                onSuccess(mRequestType, result);
            }
        }
    };

    protected static List<RankingDetail> sortAsc(List<RankingDetail> rankingDetails) {

        Collections.sort(rankingDetails, new Comparator<RankingDetail>() {

            public int compare(RankingDetail o1, RankingDetail o2) {

                if (o1.getRank() < o2.getRank()) {

                    return -1;
                }

                if (o1.getRank() > o2.getRank()) {

                    return 1;
                }
                return 0;
            }
        });
        return rankingDetails;
    }

    protected abstract void onSuccess(RequestType type, String result);

    protected abstract int getTitleResId();

    protected abstract int getLayoutResId();
}

package com.example.akihiro.palau.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akihiro.palau.net.HttpApiClient;
import com.example.akihiro.palau.net.NetConfig;
import com.example.akihiro.palau.net.RequestParam;
import com.example.akihiro.palau.net.RequestType;

import java.util.HashMap;
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

    protected abstract void onSuccess(RequestType type, String result);
    protected abstract int getTitleResId();
    protected abstract int getLayoutResId();
}

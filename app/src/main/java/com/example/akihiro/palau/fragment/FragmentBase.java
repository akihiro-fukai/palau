package com.example.akihiro.palau.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.HttpApiClient;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by akihiro on 2017/08/20.
 */

public abstract class FragmentBase extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Resources r = getResources();

        int titleResId = getTitleResId();


        return inflater.inflate(getLayoutResId(), container, false);
    }

    protected void httpRequest(String host, HashMap<String, String> params) {

        StringBuilder stringBuilder = new StringBuilder(host);

        if (null != params) {

            Set<String> keys = params.keySet();
            for (String key : keys) {

                String value = params.get(key);
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

            Log.d("aaa", result);
        }
    };
    protected abstract int getTitleResId();
    protected abstract int getLayoutResId();
}

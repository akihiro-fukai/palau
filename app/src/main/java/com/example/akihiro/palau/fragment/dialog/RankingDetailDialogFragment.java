package com.example.akihiro.palau.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.response.item.RankingDetail;

public class RankingDetailDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        RankingDetail rankingDetail = bundle.getParcelable("test");

        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_ranking_detail, null);

        dialog.setContentView(view);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textView = (TextView) view.findViewById(R.id.dialog_title);
        textView.setText(rankingDetail.getTitle());

        TextView messageView = (TextView) view.findViewById(R.id.dialog_message);
        messageView.setText(rankingDetail.getStory());

        // OK ボタンのリスナ
        dialog.findViewById(R.id.positive_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setWidth(dialog);

        return dialog;
    }

    private void setWidth(Dialog dialog) {

        // AttributeからLayoutParamsを求める
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();

        // display metricsでdpのもとを作る
        Display display = getActivity().getWindowManager().getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        Point point = new Point(0, 0);

        display.getRealSize(point);

        // LayoutParamsにdpを計算して適用する左右のマージンを画面の横幅から減算して設定
        float dialogWidthMargin = 16 * metrics.scaledDensity;
        layoutParams.width = (int) (point.x - dialogWidthMargin * 2);

        //LayoutParamsをセットする
        dialog.getWindow().setAttributes(layoutParams);
    }
}

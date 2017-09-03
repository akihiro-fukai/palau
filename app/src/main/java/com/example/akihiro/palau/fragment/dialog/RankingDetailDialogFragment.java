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
import com.example.akihiro.palau.net.AsyncNarouBodyApiClient;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import static com.example.akihiro.palau.common.UICommonUtil.BUNDLE_NNOVEL_DETAIL;

public class RankingDetailDialogFragment extends DialogFragment implements OnClickListener {

    private NovelDetail mNovelDetail;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        mNovelDetail = bundle.getParcelable(BUNDLE_NNOVEL_DETAIL);

        Dialog dialog = new Dialog(getActivity());

        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        // カスタムビューを設定
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_ranking_detail, null);
        dialog.setContentView(view);

        // 背景を透明に設定
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // タイトルを設定
        TextView textView = (TextView) view.findViewById(R.id.dialog_title);
        textView.setText(mNovelDetail.getTitle());

        // あらすじを設定
        TextView messageView = (TextView) view.findViewById(R.id.dialog_message);
        messageView.setText(mNovelDetail.getStory());

        // 閉じるボタン
        dialog.findViewById(R.id.close).setOnClickListener(this);
        // ダウンロードボタン
        dialog.findViewById(R.id.download).setOnClickListener(this);

        // 画面幅を設定
        setWidth(dialog);

        return dialog;
    }

    /**
     * 画面幅を再設定します。
     *
     * @param dialog ダイアログ
     */
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
        dialog.getWindow().setAttributes(layoutParams);
    }

    // ------------------------------
    // クリックイベント
    // ------------------------------

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id) {

            case R.id.close:

                dismiss();
                break;
            case R.id.download:

                AsyncNarouBodyApiClient client = new AsyncNarouBodyApiClient(getContext(), mNovelDetail);
                client.execute(mNovelDetail.getNCode());
                break;
            default:
                break;
        }
    }
}

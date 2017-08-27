package com.example.akihiro.palau.net.common;

import com.example.akihiro.palau.R;

import static com.example.akihiro.palau.net.common.Type.SERIES;
import static com.example.akihiro.palau.net.common.Type.SHORT_STORY;

public enum End {

    /**
     * 短編小説と完結済小説
     */
    SERIES_OUT(0),
    /**
     * 連載中
     */
    SERIES_IN(1);

    private final int mEnd;

    End(int end) {

        mEnd = end;
    }

    public int toInt() {

        return mEnd;
    }

    public static int getStatusTextId(int end, int novel_type) {

        int statusTextId = R.string.status_series_in;
        if (SERIES_IN.toInt() == end) {

            statusTextId = R.string.status_series_in;
        }
        else if (SERIES_OUT.toInt() == end) {

            if (SERIES.toInt() == novel_type) {

                statusTextId = R.string.status_series_out;
            }
            else if (SHORT_STORY.toInt() == novel_type) {

                statusTextId = R.string.status_short_story;
            }
        }
        return statusTextId;
    }
}

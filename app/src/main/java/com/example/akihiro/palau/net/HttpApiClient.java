package com.example.akihiro.palau.net;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class HttpApiClient extends AsyncTask<String, Void, String> {

    private static final int READ_TIMEOUT = 1000;
    private static final int CONNECT_TIMEOUT = 3000;
    private static final String GET = "GET";

    private OnHttpApiListener mOnHttpApiListener;

    @Override
    protected String doInBackground(String... urls) {

        StringBuilder result = new StringBuilder();

        final URL url;
        try {

            url = new URL(urls[0]);
        } catch (MalformedURLException e) {

            return null;
        }

        HttpURLConnection httpURLConnection = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        GZIPInputStream gzipInputStream = null;

        try {

            result = new StringBuilder();

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setRequestMethod(GET);

            httpURLConnection.connect();

            // HTTPレスポンスコード
            final int status = httpURLConnection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.getInputStream();
                gzipInputStream = new GZIPInputStream(inputStream);
//                inputStreamReader = new InputStreamReader(inputStream);
                inputStreamReader = new InputStreamReader(gzipInputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {

            if (null != httpURLConnection) {

                httpURLConnection.disconnect();
            }

            if (null != bufferedReader) {

                try {
                    bufferedReader.close();
                } catch (IOException e) {

                    // 処理無し
                }
            }

            if (null != inputStreamReader) {

                try {
                    inputStreamReader.close();
                } catch (IOException e) {

                    // 処理無し
                }
            }

            if (null != gzipInputStream) {

                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != inputStream) {

                try {
                    inputStream.close();
                } catch (IOException e) {

                    // 処理無し
                }
            }
        }

        return convertToString(result.toString());
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (null != mOnHttpApiListener) {

            mOnHttpApiListener.onPostExecute(result);
        }
    }

    /**
     * Unicode文字列からUTF-8文字列に変換します。 ("\u3042" -> "あ")
     *
     * @param str unicode文字列
     * @return utf-8文字列
     */
    private static String convertToString(String str) {

        if (null != str) {

            for (; str.contains("\\u"); ) {

                int index = str.indexOf("\\u");

                // unicode文字列長文の文字列を取得
                String unicode = str.substring(index, index + 6);

                // unicode文字を取得して数値へ変換
                int[] codePoints = new int[1];
                codePoints[0] = Integer.parseInt(unicode.substring(2), 16);

                // unicodeを1文字として文字列へ変換
                String encodedText = new String(codePoints, 0, 1);
                str = str.replace(unicode, encodedText);
            }
        }
        return str;
    }

    public void setOnHttpApiListener(OnHttpApiListener listener) {

        mOnHttpApiListener = listener;
    }

    public interface OnHttpApiListener {

        void onPostExecute(String result);
    }
}

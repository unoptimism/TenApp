package com.optimism.mylibrary;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;

/**
 * Created by optimism on 2016/5/27.
 */

/**
 * 实现网址的请求 返回字节数组
 */
public class NetworkTask extends AsyncTask <String,Integer,byte[]>{

    private Context mContext;
    private NetworkTaskCallback mCallback;
    public NetworkTask(Context context,NetworkTaskCallback callback) {
        mContext = context;
        mCallback = callback;
    }

    @Override
    protected byte[] doInBackground(String... params) {
        byte[] ret=null;
        if (params != null) {
            int len = params.length;
            if (len > 0) {
//                每一次执行网络请求之前 建议检测网络 如果没有网络就不加载
                ConnectivityManager manager =
                        (ConnectivityManager) mContext.
                                getSystemService(Context.CONNECTIVITY_SERVICE);
//                返回null代表飞行模式

                NetworkInfo info = manager.getActiveNetworkInfo();

                if (info != null) {
                    ret = HttpToools.doGet(params[0]);

                }

            }
        }

        return ret;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        if (mCallback != null) {

            mCallback.onTaskFinished(bytes);
        }

    }

}

package com.optimism.mylibrary;

/**
 * Created by optimism on 2016/5/27.
 */
public interface NetworkTaskCallback {
//当networktask执行完成 会回调它
    void onTaskFinished(byte[] data);
}

package com.optimism.tenapp.viewpager;


import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.optimism.mylibrary.NetworkTask;
import com.optimism.mylibrary.NetworkTaskCallback;
import com.optimism.tenapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextViewPagerFragment extends Fragment {
    private int id=0;
    private NetworkTask networkTask;

    private TextView tv_title;
    private TextView tv_aut;
    private TextView tv_aut2;
    private TextView tv_jianjie;
    private TextView tv_text;
    private TextView autbf;
    private ImageView iv_zhuanquan;

    private String author;
    private String text;
    private String title;
    private String authorbrief;
    private int  times;
    private String summary;
    private AnimationDrawable mAnimationDrawable;

    public TextViewPagerFragment() {
        // Required empty public constructor
    }

    public TextViewPagerFragment(int id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_view_pager, container, false);
        tv_title = (TextView) view.findViewById(R.id.text_title);
        tv_aut =(TextView) view.findViewById(R.id.text_aut);
        tv_aut2 = (TextView) view.findViewById(R.id.text_aut2);
        tv_jianjie = (TextView) view.findViewById(R.id.text_jianjie);
        tv_text = (TextView) view.findViewById(R.id.text_text);
        autbf = (TextView) view.findViewById(R.id.text_autbf);

        iv_zhuanquan = (ImageView)view.findViewById(R.id.iv_zhuanquan);
        Drawable drawable = iv_zhuanquan.getDrawable();
        mAnimationDrawable = (AnimationDrawable) drawable;
        mAnimationDrawable.start();


        if (id != 0) {
            networkTask = new NetworkTask(getContext(), new NetworkTaskCallback() {
                @Override
                public void onTaskFinished(byte[] data) {
                    try {
                        String msg = new String(data, "UTF-8");
                        JSONObject jsonObject = new JSONObject(msg);
                        title = jsonObject.getString("title");
                        author = jsonObject.getString("author");
                        text = jsonObject.getString("text");
                        times = jsonObject.getInt("times");
                        authorbrief = jsonObject.getString("authorbrief");
                        summary = jsonObject.getString("summary");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    tv_title.setText(title);
                    tv_aut.setText(author+"| 阅读量："+times);
                    tv_aut2.setText(author);
                    tv_jianjie.setText(summary);
                    tv_text.setText(text);
                    autbf.setText(authorbrief);
                    iv_zhuanquan.setVisibility(View.INVISIBLE);

                }
            });
            networkTask.execute("http://api.shigeten.net/api/Novel/GetNovelContent?id=" + id);
        }



        return view;
    }

}

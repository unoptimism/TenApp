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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageViewPagerFragment extends Fragment {
    private int id=0;
    private NetworkTask networkTask;

    private ImageView iv;
    private TextView tv_title;
    private TextView tv_aut;
    private TextView mTextView;

    private ImageView iv_zhuanquan;
    private String ivUrl;
    private String title;
    private String aut;
    private String text1;
    private String mText2;
    private AnimationDrawable mAnimationDrawable;


    public ImageViewPagerFragment() {
        // Required empty public constructor
    }

    public ImageViewPagerFragment(int id) {
        this.id=id;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_view_pager, container, false);
        iv = (ImageView) view.findViewById(R.id.iv);
        tv_title = (TextView) view.findViewById(R.id.image_title);
        tv_aut = (TextView) view.findViewById(R.id.image_aut);
        mTextView = (TextView) view.findViewById(R.id.image_text);

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
                        ivUrl = jsonObject.getString("image1");

                        title = jsonObject.getString("title");
                        aut = jsonObject.getString("authorbrief");
                        text1 = jsonObject.getString("text1");
                        mText2 = jsonObject.getString("text2");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Picasso.with(getContext()).load("http://api.shigeten.net/" + ivUrl).into(iv);
                    tv_title.setText(title);
                    tv_aut.setText(aut);
                    mTextView.setText(text1+"\r\n\r\n"+mText2);
                    iv_zhuanquan.setVisibility(View.INVISIBLE);

                }
            });

            networkTask.execute("http://api.shigeten.net/api/Diagram/GetDiagramContent?id=" + id);


        }


        return view;
    }

}

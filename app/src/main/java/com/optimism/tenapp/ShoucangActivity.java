package com.optimism.tenapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.optimism.tenapp.viewpager.VideoDuixiang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShoucangActivity extends AppCompatActivity {
    private TextView count;
    private SQLiteDatabase db;
    private int index = 0;
    private List<VideoDuixiang> mList1;


    private ListViewAdapter mListViewAdapter1;

    private ListView mListView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);


        mListView1 = (ListView) findViewById(R.id.listview1);
        mList1 = new ArrayList<>();


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        count = (TextView) findViewById(R.id.count);
        String path = this.getCacheDir().getAbsolutePath() + File.separator + "MyDown.db";


        db = SQLiteDatabase.openOrCreateDatabase(path, null);

        String sqlvideo = "select * from video";
        Cursor cursorVideo = db.rawQuery(sqlvideo, null);
        while (cursorVideo.moveToNext()) {
            index++;
            int titleIndex = cursorVideo.getColumnIndex("shoucangTitle");
            String title = cursorVideo.getString(titleIndex);

            int realtitleIndex = cursorVideo.getColumnIndex("shoucangRealtitle");
            String realtitle = cursorVideo.getString(realtitleIndex);

            int publishtimeIndex = cursorVideo.getColumnIndex("publishtime");
            String publishtime = cursorVideo.getString(publishtimeIndex);


            VideoDuixiang duixiang = new VideoDuixiang(title, realtitle, publishtime);
            int idIndex = cursorVideo.getColumnIndex("id");
            String id = cursorVideo.getString(idIndex);

            int authorIndex = cursorVideo.getColumnIndex("author");
            String author = cursorVideo.getString(authorIndex);


            int authorbriefIndex = cursorVideo.getColumnIndex("authorbrief");
            String authorbrief = cursorVideo.getString(authorbriefIndex);

            int text1Index = cursorVideo.getColumnIndex("text1");
            String text1 = cursorVideo.getString(text1Index);

            int text2Index = cursorVideo.getColumnIndex("text2");
            String text2 = cursorVideo.getString(text2Index);

            int text3Index = cursorVideo.getColumnIndex("text3");
            String text3 = cursorVideo.getString(text3Index);

            int text4Index = cursorVideo.getColumnIndex("text4");
            String text4 = cursorVideo.getString(text4Index);

            int text5Index = cursorVideo.getColumnIndex("text5");
            String text5 = cursorVideo.getString(text5Index);

            int image1Index = cursorVideo.getColumnIndex("image1");
            String image1 = cursorVideo.getString(image1Index);
            int image2Index = cursorVideo.getColumnIndex("image2");
            String image2 = cursorVideo.getString(image2Index);
            int image3Index = cursorVideo.getColumnIndex("image3");
            String image3 = cursorVideo.getString(image3Index);
            int image4Index = cursorVideo.getColumnIndex("image4");
            String image4 = cursorVideo.getString(image4Index);
            int image5Index = cursorVideo.getColumnIndex("image5");
            String image5 = cursorVideo.getString(image5Index);



            duixiang.id = id;
            duixiang.aut = author;
            duixiang.autbf = authorbrief;
            duixiang.text1 = text1;
            duixiang.text2 = text2;
            duixiang.text3 = text3;
            duixiang.text4 = text4;
            duixiang.text5 = text5;
            duixiang.image1 = "http://api.shigeten.net/" + image1;
            duixiang.image2 = "http://api.shigeten.net/" + image2;
            duixiang.image3 = "http://api.shigeten.net/" + image3;
            duixiang.image4 = "http://api.shigeten.net/" + image4;
            duixiang.image5 = "http://api.shigeten.net/" + image5;

            mList1.add(duixiang);

        }


        String sqltext = "select * from text";
        Cursor cursorText = db.rawQuery(sqltext, null);
        while (cursorText.moveToNext()) {
            index++;
            int titleIndex = cursorText.getColumnIndex("shoucangTitle");
            String title = cursorText.getString(titleIndex);

            int jianjieIndex = cursorText.getColumnIndex("shoucangJianjie");
            String jianjie = cursorText.getString(jianjieIndex);
            int publishtimeIndex = cursorText.getColumnIndex("publishtime");
            String publishtime = cursorText.getString(publishtimeIndex);

            VideoDuixiang duixiang = new VideoDuixiang(title, jianjie, publishtime);
            mList1.add(duixiang);

        }


        String sqlimage = "select * from image";
        Cursor cursorImage = db.rawQuery(sqlimage, null);
        while (cursorImage.moveToNext()) {
            index++;
            int titleIndex = cursorImage.getColumnIndex("shoucangTitle");
            String title = cursorImage.getString(titleIndex);

            int jianjieIndex = cursorImage.getColumnIndex("shoucangAut");
            String jianjie = cursorImage.getString(jianjieIndex);
            int publishtimeIndex = cursorImage.getColumnIndex("publishtime");
            String publishtime = cursorImage.getString(publishtimeIndex);

            VideoDuixiang duixiang = new VideoDuixiang(title, jianjie, publishtime);
            mList1.add(duixiang);

        }


        mListViewAdapter1 = new ListViewAdapter(this, mList1);
        mListView1.setAdapter(mListViewAdapter1);


        count.setText(index + "");

        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoucangActivity.this, ShoucangZhanshi.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Video", mList1.get(position));

                intent.putExtra("k", bundle);

                startActivity(intent);

            }
        });



    }

    public void back(View view) {
        finish();
    }


    public class ListViewAdapter extends BaseAdapter {
        private Context mContext;
        private List<VideoDuixiang> mList;

        public ListViewAdapter(Context context, List<VideoDuixiang> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(ShoucangActivity.this, R.layout.mulu_list, null);
            TextView textViewTitle = (TextView) convertView.findViewById(R.id.shoucang_title);
            TextView textViewJianjie = (TextView) convertView.findViewById(R.id.shoucang_jianjie);

            textViewTitle.setText(mList.get(position).title);
            textViewJianjie.setText(mList.get(position).jianjie);

            return convertView;
        }
    }

}

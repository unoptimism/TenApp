package com.optimism.tenapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    private int videoCount = 0;
    private int textCount = 0;
    private int imageCount = 0;

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
        while (cursorVideo!=null&&cursorVideo.moveToNext()) {
            videoCount++;
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

            int authorIndex = cursorVideo.getColumnIndex("shoucangAut");
            String author = cursorVideo.getString(authorIndex);


            int authorbriefIndex = cursorVideo.getColumnIndex("shoucangAutbf");
            String authorbrief = cursorVideo.getString(authorbriefIndex);

            int text1Index = cursorVideo.getColumnIndex("shoucangText1");
            String text1 = cursorVideo.getString(text1Index);

            int text2Index = cursorVideo.getColumnIndex("shoucangText2");
            String text2 = cursorVideo.getString(text2Index);

            int text3Index = cursorVideo.getColumnIndex("shoucangText3");
            String text3 = cursorVideo.getString(text3Index);

            int text4Index = cursorVideo.getColumnIndex("shoucangText4");
            String text4 = cursorVideo.getString(text4Index);

            int text5Index = cursorVideo.getColumnIndex("shoucangText5");
            String text5 = cursorVideo.getString(text5Index);

            int image1Index = cursorVideo.getColumnIndex("shoucangImage1");
            String image1 = cursorVideo.getString(image1Index);
            int image2Index = cursorVideo.getColumnIndex("shoucangImage2");
            String image2 = cursorVideo.getString(image2Index);
            int image3Index = cursorVideo.getColumnIndex("shoucangImage3");
            String image3 = cursorVideo.getString(image3Index);
            int image4Index = cursorVideo.getColumnIndex("shoucangImage4");
            String image4 = cursorVideo.getString(image4Index);
            int image5Index = cursorVideo.getColumnIndex("shoucangImage5");
            String image5 = cursorVideo.getString(image5Index);


            duixiang.id = id;
            duixiang.aut = author;
            duixiang.autbf = authorbrief;
            duixiang.text1 = text1;
            duixiang.text2 = text2;
            duixiang.text3 = text3;
            duixiang.text4 = text4;
            duixiang.text5 = text5;
            duixiang.image1 = image1;
            duixiang.image2 = image2;
            duixiang.image3 = image3;
            duixiang.image4 = image4;
            duixiang.image5 = image5;

            mList1.add(duixiang);

        }


        String sqltext = "select * from text";
        Cursor cursorText = db.rawQuery(sqltext, null);
        while (cursorText!=null&&cursorText.moveToNext()) {
            textCount++;
            index++;
            int titleIndex = cursorText.getColumnIndex("shoucangTitle");
            String title = cursorText.getString(titleIndex);

            int jianjieIndex = cursorText.getColumnIndex("shoucangJianjie");
            String jianjie = cursorText.getString(jianjieIndex);
            int publishtimeIndex = cursorText.getColumnIndex("publishtime");
            String publishtime = cursorText.getString(publishtimeIndex);

            VideoDuixiang duixiang = new VideoDuixiang(title, jianjie, publishtime);

            int shoucangAutIndex = cursorText.getColumnIndex("shoucangAut");
            String shoucangAut = cursorText.getString(shoucangAutIndex);
            int textAutIndex = cursorText.getColumnIndex("shoucangText");
            String shoucangText = cursorText.getString(textAutIndex);
            int shoucangAutbfIndex = cursorText.getColumnIndex("shoucangAutbf");
            String shoucangAutbf = cursorText.getString(shoucangAutbfIndex);

            int timesIndex = cursorText.getColumnIndex("shoucangTimes");
            String shoucangTimes = cursorText.getString(timesIndex);


            duixiang.aut = shoucangAut;
            duixiang.text = shoucangText;
            duixiang.autbf = shoucangAutbf;
            duixiang.times = shoucangTimes;

            mList1.add(duixiang);

        }


        String sqlimage = "select * from image";
        Cursor cursorImage = db.rawQuery(sqlimage, null);
        while (cursorImage!=null &&cursorImage.moveToNext()) {
            imageCount++;
            index++;
            int titleIndex = cursorImage.getColumnIndex("shoucangTitle");
            String title = cursorImage.getString(titleIndex);

            int jianjieIndex = cursorImage.getColumnIndex("shoucangAut");
            String jianjie = cursorImage.getString(jianjieIndex);
            int publishtimeIndex = cursorImage.getColumnIndex("publishtime");
            String publishtime = cursorImage.getString(publishtimeIndex);

            VideoDuixiang duixiang = new VideoDuixiang(title, jianjie, publishtime);

            int autIndex = cursorImage.getColumnIndex("shoucangAut");
            String shoucangAut = cursorImage.getString(autIndex);
            int text1Index = cursorImage.getColumnIndex("shoucangText1");
            String shoucangText1 = cursorImage.getString(text1Index);
            int text2Index = cursorImage.getColumnIndex("shoucangText2");
            String shoucangText2 = cursorImage.getString(text2Index);
            int imageIndex = cursorImage.getColumnIndex("shoucangImage1");
            String shoucangImage1 = cursorImage.getString(imageIndex);


            duixiang.aut = shoucangAut;
            duixiang.text1 = shoucangText1;
            duixiang.text2 = shoucangText2;
            duixiang.image1 = shoucangImage1;
            mList1.add(duixiang);

        }


        mListViewAdapter1 = new ListViewAdapter(this, mList1);
        mListView1.setAdapter(mListViewAdapter1);


        count.setText(index + "");

        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position < videoCount) {
                    Intent intent = new Intent(ShoucangActivity.this, VideoShoucangZhanshi.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Video", mList1.get(position));

                    intent.putExtras(bundle);

                    startActivity(intent);
                } else if (position >= (videoCount + textCount)) {
                    Intent intent = new Intent(ShoucangActivity.this, ImageShoucangZhanshi.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Image", mList1.get(position));

                    intent.putExtras(bundle);

                    startActivity(intent);


                } else {

                    Intent intent = new Intent(ShoucangActivity.this, TextShoucangZhanshi.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Text", mList1.get(position));

                    intent.putExtras(bundle);

                    startActivity(intent);

                }


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
            TextView textViewType = (TextView) convertView.findViewById(R.id.shoucang_type);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.shoucang_image);

            if (position < videoCount) {
                textViewType.setText("影评");
                imageView.setBackgroundColor(Color.BLACK);
            }
            if (position >= videoCount && position < textCount+videoCount) {
                textViewType.setText("文章");
                imageView.setBackgroundColor(Color.BLUE);
            }
            if (position >= textCount+videoCount) {
                textViewType.setText("图片");
                imageView.setBackgroundColor(Color.RED);
            }


            textViewTitle.setText(mList.get(position).title);
            textViewJianjie.setText(mList.get(position).jianjie);

            return convertView;
        }
    }

}

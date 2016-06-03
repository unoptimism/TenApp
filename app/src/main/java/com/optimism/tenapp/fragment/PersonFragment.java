package com.optimism.tenapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.optimism.tenapp.GuanyuActivity;
import com.optimism.tenapp.MainActivity;
import com.optimism.tenapp.R;
import com.optimism.tenapp.YijianActivity;
import com.optimism.tenapp.ZitiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private Button yijian;
    private Button guanyu;
    private Button ziti;
    private RadioGroup rg;




    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        yijian = (Button) view.findViewById(R.id.yijian);
        yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), YijianActivity.class);
                startActivity(it);

            }
        });


        guanyu = (Button) view.findViewById(R.id.guanyu);
        guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), GuanyuActivity.class);
                startActivity(it);

            }
        });

        ziti = (Button) view.findViewById(R.id.ziti);
        ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getActivity(), ZitiActivity.class);
                startActivity(it);

            }
        });

        rg.setOnCheckedChangeListener(this);

        return view;

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index=0;

        switch (checkedId) {
            case R.id.rb1:
                index=0;
                break;
            case R.id.rb2:
                index=1;
                break;
            case R.id.rb3:
                index=2;
                break;
            case R.id.rb4:
                index=3;
                break;

        }

        checkedRB(index);

    }

    private void checkedRB(int index) {
        if (index >= 0 && index < MainActivity.mFragments.size()) {


            int size = MainActivity.mFragments.size();
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            for (int i = 0; i < size; i++) {
                Fragment f = MainActivity.mFragments.get(i);

                if (i == index) {
//                    显示
                    transaction.show(f);

                } else {
//                    隐藏
                    transaction.hide(f);
                }

            }

            transaction.commit();
        }


    }
}

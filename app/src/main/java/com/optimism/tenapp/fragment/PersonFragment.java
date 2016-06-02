package com.optimism.tenapp.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.optimism.tenapp.GuanyuActivity;
import com.optimism.tenapp.MainActivity;
import com.optimism.tenapp.R;
import com.optimism.tenapp.YijianActivity;
import com.optimism.tenapp.ZitiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    private Button yijian;
    private Button guanyu;
    private Button ziti;






    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);

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





        return view;

    }

}

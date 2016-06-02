package com.optimism.tenapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.optimism.tenapp.GuanyuActivity;
import com.optimism.tenapp.R;
import com.optimism.tenapp.YijianActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {
    private Button yijian;
    private Button guanyu;


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


        return view;

    }

}

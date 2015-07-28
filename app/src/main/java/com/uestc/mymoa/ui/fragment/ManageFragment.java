package com.uestc.mymoa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.uestc.mymoa.R;
import com.uestc.mymoa.ui.FileManageActivity;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class ManageFragment extends Fragment {

    private LinearLayout docManageLinear;
    private LinearLayout personalCenterLinear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_manage_fragment, null);

        initLayout(view);
        initListener();
        return view;
    }

    private void initLayout(View view) {
        docManageLinear = (LinearLayout) view.findViewById(R.id.docManageLinear);
        personalCenterLinear = (LinearLayout) view.findViewById(R.id.personalCenterLinear);
    }

    private void initListener() {
        docManageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FileManageActivity.class));
            }
        });

        personalCenterLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),));
            }
        });
    }
}

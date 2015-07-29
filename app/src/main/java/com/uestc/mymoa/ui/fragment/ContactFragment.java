package com.uestc.mymoa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.uestc.mymoa.R;
import com.uestc.mymoa.ui.ContactGroupListActivity;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class ContactFragment extends Fragment {

    private LinearLayout myGroupLinear;
    private ListView allContactList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_contact_fragment, null);

        initLayout(view);

        initListener();
        return view;
    }

    private void initLayout(View view) {
        myGroupLinear = (LinearLayout) view.findViewById(R.id.myGroupLinear);
        allContactList = (ListView) view.findViewById(R.id.allContactList);
    }

    private void initListener(){
        myGroupLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactGroupListActivity.class);
                startActivity(intent);
            }
        });
    }
}

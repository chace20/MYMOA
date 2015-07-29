package com.uestc.mymoa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.ui.ContactDetailActivity;
import com.uestc.mymoa.ui.ContactGroupListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class ContactFragment extends Fragment {

    private LinearLayout myGroupLinear;
    private ListView allContactList;
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

    private void getContactList(){

        new ContactHandler().getContactList(new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {

                list = result;
                refreshAdapter();
            }

            @Override
            public void onSuccess(HashMap<String, Object> result) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
    private void refreshAdapter(){
        allContactList.setAdapter(new SimpleAdapter(this.getActivity(),
                list, R.layout.item_contact,
                new String[]{"uname"}, new int[]{R.id.itemText}));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_contact_fragment, null);

        initLayout(view);

        initListener();
        getContactList();
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
        allContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),
                        ContactDetailActivity.class);

                intent.putExtra("uid", list.get(position).get("uid").toString());
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == 2){
            String uid = data.getStringExtra("uid");
            int i = 0;
            for(HashMap<String, Object> map : list){

                if(map.get("uid").equals(uid)){

                    list.remove(i);
                    refreshAdapter();
                }
                i++;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

package com.uestc.mymoa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.MailQueryMailListHandler;
import com.uestc.mymoa.io.UserQueryUserHandler;
import com.uestc.mymoa.ui.MailItemContentActivity;
import com.uestc.mymoa.ui.adapter.MailListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nothisboy on 2015/7/26.
 */
public class MailFragment extends Fragment {

    private static final int SEND = 0;
    private static final int RESIEVE = 0;

    private ListView mailHistoryList;
    private MailQueryMailListHandler handler;
    private List<HashMap<String, Object>> mailList;

    private String uname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_main_mail_fragment, null);


        initLayout(view);
        initValues();
        initListener();
        return view;
    }

    private void initLayout(View view) {
        mailHistoryList = (ListView) view.findViewById(R.id.mailHistoryList);
    }

    private void initValues() {
        mailList = new ArrayList<>();
        handler = new MailQueryMailListHandler();
        getData();
    }

    private void initListener() {
        mailHistoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MailItemContentActivity.class);
                intent.putExtra("mailid", String.valueOf(mailList.get(position).get("mailid")));
                startActivity(intent);
            }
        });
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("uid", Id.userId);

        handler.process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {
                List<HashMap<String, Object>> list = result;
                mailList = list;
                mailHistoryList.setAdapter(new MailListAdapter(getActivity(),list));
            }

            @Override
            public void onSuccess(Object result) {
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(), "internet error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNameById(String uid) {

        RequestParams params = new RequestParams();
        params.addQueryStringParameter("id", uid);
        new UserQueryUserHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {
            }

            @Override
            public void onSuccess(Object result) {
                HashMap<String, Object> map = (HashMap<String, Object>) result;
                uname = null;
                uname = String.valueOf(map.get("uname"));

            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getActivity(), "internet error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

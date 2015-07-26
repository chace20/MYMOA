package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class GroupDetailActivity extends BaseActivity{

    private ListView groupdetailList;
    private ArrayList<HashMap<String, Object>> list;
    private int groupid;
    private void getData(){
        //TODO
        Intent intent = getIntent();
        groupid = intent.getIntExtra("groupid", -1);
    }
    private void refreshAdapter(){
        groupdetailList.setAdapter(new SimpleAdapter(GroupDetailActivity.this,
                list, R.layout.item_contact,
                new String[]{"uid"}, new int[]{R.id.itemText}));
    }
    @Override
    protected void initLayout() {
        setContentView(R.layout.layout_groupdetail);
        groupdetailList = (ListView) findViewById(R.id.groupdetailList);
    }

    @Override
    protected void initListener() {
        groupdetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(GroupDetailActivity.this,
                        ContactDetailActivity.class);

                intent.putExtra("uid", (Integer) list.get(position)
                        .get("uid"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return 0;
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
        refreshAdapter();
    }
}

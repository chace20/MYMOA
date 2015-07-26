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
public class MyGroupActivity extends BaseActivity{

    private ListView groupList;
    private ArrayList<HashMap<String, Object>> list;

    private void getData(){
        //TODO
    }
    private void refreshAdapter(){
        groupList.setAdapter(new SimpleAdapter(MyGroupActivity.this,
                list, R.layout.item_contact,
                new String[]{"groupname"}, new int[]{R.id.itemText}));
    }
    @Override
    protected void initLayout() {
        setContentView(R.layout.layout_mygroup);
        groupList = (ListView) findViewById(R.id.groupList);
    }


    @Override
    protected void initListener() {
        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MyGroupActivity.this,
                        GroupDetailActivity.class);

                intent.putExtra("groupid", (Integer) list.get(position)
                        .get("groupid"));
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
        refreshAdapter();
    }
}


package com.uestc.mymoa.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
public class MyGroupActivity extends Activity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_mygroup);
        groupList = (ListView) findViewById(R.id.groupList);

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
    protected void onResume() {
        super.onResume();
        refreshAdapter();
    }
}


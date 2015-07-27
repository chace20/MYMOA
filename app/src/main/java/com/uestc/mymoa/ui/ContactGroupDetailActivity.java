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
public class ContactGroupDetailActivity extends Activity {

    private ListView groupdetailList;
    private ArrayList<HashMap<String, Object>> list;
    private int groupid;
    private void getData(){
        //TODO
        Intent intent = getIntent();
        groupid = intent.getIntExtra("groupid", -1);
    }
    private void refreshAdapter(){
        groupdetailList.setAdapter(new SimpleAdapter(ContactGroupDetailActivity.this,
                list, R.layout.item_contact,
                new String[]{"uid"}, new int[]{R.id.itemText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_groupdetail);
        groupdetailList = (ListView) findViewById(R.id.groupdetailList);

        groupdetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ContactGroupDetailActivity.this,
                        ContactDetailActivity.class);

                intent.putExtra("uid", (Integer) list.get(position)
                        .get("uid"));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
        refreshAdapter();
    }
}

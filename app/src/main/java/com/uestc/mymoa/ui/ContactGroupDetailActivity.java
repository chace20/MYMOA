package com.uestc.mymoa.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactGroupDetailActivity extends Activity {

    private ListView groupdetailList;
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    private String groupid;
    private void getGroupDetailList(){


        RequestParams params = new RequestParams();

        params.addQueryStringParameter("groupid", groupid);
        Log.e("null", "groupid2:" + groupid);

        new ContactHandler().getGroupDetailList(params, new IOCallback<HashMap<String, Object>>() {
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

    private void addGroupContact(String uid){

        RequestParams params = new RequestParams();

        params.addBodyParameter("uid", uid);
        params.addBodyParameter("groupid",groupid);

        new ContactHandler().addGroupContact(params, new IOCallback<RequestStatus>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<RequestStatus> result) {

            }

            @Override
            public void onSuccess(RequestStatus result) {
                if (result.code == 200) {
                    Toast.makeText(ContactGroupDetailActivity.this, "添加群组联系人成功",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactGroupDetailActivity.this, "添加群组联系人失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }
    private void refreshAdapter(){
        groupdetailList.setAdapter(new SimpleAdapter(ContactGroupDetailActivity.this,
                list, R.layout.item_contact,
                new String[]{"uid"}, new int[]{R.id.itemText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        groupid = intent.getStringExtra("groupid");
        Log.e("null", "groupid1:" + groupid);
        setContentView(R.layout.layout_groupdetail);
        groupdetailList = (ListView) findViewById(R.id.groupdetailList);
        Log.e("null", "oncreate");

        groupdetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ContactGroupDetailActivity.this,
                        ContactDetailActivity.class);

                intent.putExtra("uid", list.get(position).get("uid").toString());
                startActivity(intent);
            }
        });
        getGroupDetailList();
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_add, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {

            final EditText addContactEdit = new EditText(ContactGroupDetailActivity.this);
            addContactEdit.setHint("请输入联系人手机号");

            new  AlertDialog.Builder(ContactGroupDetailActivity.this)
                    .setTitle("新建联系人群组")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setView(addContactEdit)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            addGroupContact(addContactEdit.getText().toString());
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
            getGroupDetailList();
            refreshAdapter();
        }
        return super.onOptionsItemSelected(item);
    }
}

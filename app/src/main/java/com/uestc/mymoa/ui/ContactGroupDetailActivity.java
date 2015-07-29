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
import com.uestc.mymoa.common.view.InputDialog;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactGroupDetailActivity extends BaseActivity {

    private ListView groupdetailList;
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    private String groupid;
    private void getGroupDetailList(){


        RequestParams params = new RequestParams();

        params.addQueryStringParameter("groupid", groupid);

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
        params.addBodyParameter("groupid", groupid);

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
                new String[]{"uname"}, new int[]{R.id.itemText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        groupid = intent.getStringExtra("groupid");
        groupdetailList = (ListView) findViewById(R.id.groupdetailList);

        groupdetailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ContactGroupDetailActivity.this,
                        ContactDetailActivity.class);

                intent.putExtra("uid", list.get(position).get("uid").toString());
                startActivityForResult (intent, 1);
            }
        });
        list.clear();
        getGroupDetailList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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

    @Override
    protected void initLayout() {
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_groupdetail;
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_add, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {


            final InputDialog inputDialog = new  InputDialog(ContactGroupDetailActivity.this);
            inputDialog.setInputEditHint("请输入联系人手机号");
                    inputDialog.buider.setTitle("新建群组联系人")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            addGroupContact(inputDialog.getInputEditText());
                            list.clear();
                            getGroupDetailList();
                        }
                    })
                    .setNegativeButton("取消", null)
                            .show();
        }
        return super.onOptionsItemSelected(item);
    }
}

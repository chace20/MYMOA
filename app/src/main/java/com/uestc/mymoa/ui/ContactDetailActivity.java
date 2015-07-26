package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.HashMap;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactDetailActivity extends BaseActivity{

    private TextView nameText;
    private TextView phonenumText;
    private Button delcontactButton;
    private int uid;

    private HashMap<String, Object> getData(){
        //TODO
        HashMap<String, Object> map = new HashMap<String, Object>();
        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", -1);
        return map;
    }
    @Override
    protected void initLayout() {

        setContentView(R.layout.layout_contactdetail);
        nameText = (TextView) findViewById(R.id.nameText);
        phonenumText = (TextView) findViewById(R.id.phonenumText);
        delcontactButton = (Button) findViewById(R.id.delcontactButton);

    }

    @Override
    protected void initListener() {
        delcontactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map = getData();
        if(map.get("uid") != -1){
            nameText.setText(map.get("uname").toString());
            phonenumText.setText(map.get("phonenum").toString());
        }
    }
}

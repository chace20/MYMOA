package com.uestc.mymoa.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.HashMap;

/**
 * Created by HeGang on 2015/7/29.
 */
public class PostDetailActivity extends Activity {
    private TextView title;
    private TextView date;
    private TextView article;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details);
        title=(TextView)findViewById(R.id.title);
        date=(TextView)findViewById(R.id.date);
        article=(TextView)findViewById(R.id.article);
        title.
    }

    protected void onResume(){
        HashMap<String,Object> map =new HashMap<>();
    }
}

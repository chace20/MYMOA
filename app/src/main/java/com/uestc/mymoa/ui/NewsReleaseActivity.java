package com.uestc.mymoa.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.uestc.mymoa.R;

/**
 * Created by SinLapis on 2015/7/29.
 */
public class NewsReleaseActivity extends BaseActivity {

    private EditText newsReleaseTitleText;
    private EditText newsReleaseContentText;
    private Spinner newsReleaseCategorySpinner;
    private Button newsReleaseButton;

    private String title;
    private String content;
    private String typeid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsReleaseTitleText = (EditText) findViewById(R.id.newsReleaseTitleText);
        newsReleaseCategorySpinner = (Spinner) findViewById(R.id.newsReleaseCategorySpinner);
        newsReleaseContentText = (EditText) findViewById(R.id.newsReleaseContentText);
        newsReleaseButton = (Button) findViewById(R.id.newsReleaseButton);

        newsReleaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_news_release;
    }
}

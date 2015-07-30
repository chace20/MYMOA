package com.uestc.mymoa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.NewsAddNewsHandler;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/29.
 */
public class NewsReleaseActivity extends BaseActivity {

    private EditText newsReleaseTitleText;
    private EditText newsReleaseContentText;
    private Spinner newsReleaseCategorySpinner;
    private Button newsReleaseButton;

    private String typeid;

    private void addNews() {

        RequestParams params = new RequestParams();

        params.addBodyParameter("typeid", typeid);
        params.addBodyParameter("uid", Id.userId);
        params.addBodyParameter("title", newsReleaseTitleText.getText().toString());
        params.addBodyParameter("content", newsReleaseContentText.getText().toString());

        new NewsAddNewsHandler().process(params, new IOCallback<RequestStatus>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<RequestStatus> result) {

            }

            @Override
            public void onSuccess(RequestStatus result) {
                if (result.code == 200) {
                    Toast.makeText(NewsReleaseActivity.this, "发布新闻成功",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NewsReleaseActivity.this, "发布新闻失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(NewsReleaseActivity.this, "发布新闻失败",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsReleaseTitleText = (EditText) findViewById(R.id.newsReleaseTitleText);
        newsReleaseCategorySpinner = (Spinner) findViewById(R.id.newsReleaseCategorySpinner);
        newsReleaseContentText = (EditText) findViewById(R.id.newsReleaseContentText);
        newsReleaseButton = (Button) findViewById(R.id.newsReleaseButton);

        newsReleaseCategorySpinner.setAdapter(ArrayAdapter.createFromResource(
                this, R.array.news_category, R.layout.item_news_release_category));

        newsReleaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addNews();
            }
        });

        newsReleaseCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("select","typeid--"+position+1);
                typeid = String.valueOf((position + 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                newsReleaseCategorySpinner.setSelection(0);
                typeid = String.valueOf(1);
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

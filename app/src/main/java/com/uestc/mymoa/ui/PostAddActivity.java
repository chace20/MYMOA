package com.uestc.mymoa.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uestc.mymoa.AfficheDBAdapter;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.model.Post;

/**
 * Created by HeGang on 2015/7/27.
 */
public class PostAddActivity extends Activity {
    private EditText etTitle;
    private EditText etArticle;
    private Button btnStart;
    private Button btnFinal;
    private Button btnSelect;
    private Button btnIssus;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        initLayout();
        initListener();
    }

    private void initLayout(){
        etTitle=(EditText)findViewById(R.id.title);
        etArticle=(EditText)findViewById(R.id.article);
        btnStart=(Button)findViewById(R.id.start_date);
        btnFinal=(Button)findViewById(R.id.final_date);
        btnSelect=(Button)findViewById(R.id.select_file);
        btnIssus=(Button)findViewById(R.id.issue);
    }

    private void initListener(){
        btnIssus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post =new Post();
                AfficheDBAdapter afficheDBAdapter=new AfficheDBAdapter();
                post.title=String.valueOf(etTitle.getText());
                post.article=String.valueOf(etArticle.getText());
            }
        });

    }
}

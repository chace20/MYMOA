package com.uestc.mymoa.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.PostAddPostHandler;
import com.uestc.mymoa.io.model.Post;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HeGang on 2015/7/27.
 */
public class PostAddActivity extends BaseActivity {
    private EditText etTitle;
    private EditText etArticle;
    private Button btnStart;
    private Button btnFinal;
    private Button btnIssus;
    private LinearLayout postContentLinear;

    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_DATAPICK = 0;

    private static final int CHOOSE_START_DATE = 0X10;
    private static final int CHOOSE_FINAL_DATE = 0X11;
    private int currentChoose = -1;


    private int mYear;
    private int mMonth;
    private int mDay;
    private String startdate;
    private String finaldate;


    @Override
    protected void initLayout() {
        etTitle = (EditText) findViewById(R.id.postTitleEdit);
        etArticle = (EditText) findViewById(R.id.postContentEdit);
        btnStart = (Button) findViewById(R.id.startDateButton);
        btnFinal = (Button) findViewById(R.id.endDateButton);
        btnIssus = (Button) findViewById(R.id.postReleaseButton);
        postContentLinear = (LinearLayout) findViewById(R.id.postContentLinear);
    }

    @Override
    protected void initListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                currentChoose = CHOOSE_START_DATE;
            }
        });
        btnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                currentChoose = CHOOSE_FINAL_DATE;
            }
        });

        btnIssus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.title = String.valueOf(etTitle.getText());
                post.article = String.valueOf(etArticle.getText());

                if (etTitle.getText().length() == 0) {
                    Toast.makeText(PostAddActivity.this, "要有标题哟~", Toast.LENGTH_SHORT).show();
                } else if (etArticle.getText().length() == 0) {
                    Toast.makeText(PostAddActivity.this, "要有内容哟~", Toast.LENGTH_SHORT).show();
                } else if (startdate.length() == 0) {
                    Toast.makeText(PostAddActivity.this, "好像要写一个起始时间~", Toast.LENGTH_SHORT).show();
                } else if (finaldate.length() == 0) {
                    Toast.makeText(PostAddActivity.this, "好像要写一个截止时间~", Toast.LENGTH_SHORT).show();
                } else {
                    releaseNewPost();
                }

            }

        });
        postContentLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etArticle.requestFocus();
                etArticle.setActivated(true);
            }
        });
    }

    @Override
    protected void initValue() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_post_add;
    }

    private void releaseNewPost() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("uid", Id.userId);
        params.addBodyParameter("title", String.valueOf(etTitle.getText()));
        params.addBodyParameter("content", String.valueOf(etArticle.getText()));
        params.addBodyParameter("starttime", startdate);
        params.addBodyParameter("endtime", finaldate);
        new PostAddPostHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {
            }

            @Override
            public void onSuccess(Object result) {
                if (((RequestStatus) result).code == 200) {
                    Toast.makeText(PostAddActivity.this, "发布成功啦~", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    };
                    timer.schedule(task, 500);
                } else {
                    Toast.makeText(PostAddActivity.this, "由于服务器某种原因，发布失败啦~", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(PostAddActivity.this, "internet error ~", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 日期控件的事件
     */

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,

                              int dayOfMonth) {

            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;

            if (currentChoose == CHOOSE_START_DATE) {
                startdate = String.valueOf(new StringBuilder().append(mYear).append("-").
                        append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").
                        append((mDay < 10) ? "0" + mDay : mDay));
                btnStart.setText(startdate);
            } else if (currentChoose == CHOOSE_FINAL_DATE) {
                finaldate = String.valueOf(new StringBuilder().append(mYear).append("-").
                        append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").
                        append((mDay < 10) ? "0" + mDay : mDay));
                btnFinal.setText(finaldate);
            }
        }

    };

    @Override

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
                break;
        }
    }
}

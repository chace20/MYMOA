package com.uestc.mymoa.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.uestc.mymoa.R;
import com.uestc.mymoa.io.model.Post;

import java.util.Calendar;

/**
 * Created by HeGang on 2015/7/27.
 */
public class PostAddActivity extends Activity {
    private EditText etTitle;
    private EditText etArticle;
    private Button btnStart;
    private Button btnFinal;
    private Button btnIssus;

    private Button pickDate = null;
    private EditText showDate;
    private static final int DATE_DIALOG_ID = 1;
    private static final int SHOW_DATAPICK = 0;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String startdate;
    private String finaldate;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_add);

        etTitle=(EditText)findViewById(R.id.title);
        etArticle=(EditText)findViewById(R.id.article);
        btnStart=(Button)findViewById(R.id.start_date);
        btnFinal=(Button)findViewById(R.id.final_date);



        pickDate = (Button) findViewById(R.id.start_date);
        pickDate.setOnClickListener(new DateButtonOnClickListener());
        final Calendar c1 = Calendar.getInstance();
        mYear = c1.get(Calendar.YEAR);
        mMonth = c1.get(Calendar.MONTH);
        mDay = c1.get(Calendar.DAY_OF_MONTH);
        startdate = ""+mYear+"-"+mMonth+"-"+mDay;
        btnStart.setText(startdate);


        pickDate = (Button) findViewById(R.id.final_date);
        pickDate.setOnClickListener(new DateButtonOnClickListener());
        final Calendar c2 = Calendar.getInstance();
        mYear = c2.get(Calendar.YEAR);
        mMonth = c2.get(Calendar.MONTH);
        mDay = c2.get(Calendar.DAY_OF_MONTH);
        finaldate = ""+mYear+"-"+mMonth+"-"+mDay;
        btnFinal.setText(finaldate);

        setDateTime();


        btnIssus=(Button)findViewById(R.id.issue);
        btnIssus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post();
                post.title = String.valueOf(etTitle.getText());
                post.article = String.valueOf(etArticle.getText());

            }

        });
    }







    private void setDateTime() {

        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);

        mMonth = c.get(Calendar.MONTH);

        mDay = c.get(Calendar.DAY_OF_MONTH);



        updateDisplay();

    }



    /**

     * 更新日期

     */

    private void updateDisplay() {

        showDate.setText(new StringBuilder().append(mYear).append(

                (mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append(

                (mDay < 10) ? "0" + mDay : mDay));

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

            updateDisplay();

        }

    };



    /**

     * 选择日期Button的事件处理

     *

     * @author Raul

     *

     */

    class DateButtonOnClickListener implements

            android.view.View.OnClickListener {

        @Override

        public void onClick(View v) {

            Message msg = new Message();

            if (pickDate.equals((Button) v)) {

                msg.what = PostAddActivity.SHOW_DATAPICK;

            }
            PostAddActivity.this.saleHandler.sendMessage(msg);

        }

    }



    @Override

    protected Dialog onCreateDialog(int id) {

        switch (id) {

            case DATE_DIALOG_ID:

                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,

                        mDay);

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



    /**

     * 处理日期控件的Handler

     */

    Handler saleHandler = new Handler() {

        @Override

        public void handleMessage(Message msg) {

            switch (msg.what) {

                case PostAddActivity.SHOW_DATAPICK:

                    showDialog(DATE_DIALOG_ID);

                    break;

            }

        }

    };
}

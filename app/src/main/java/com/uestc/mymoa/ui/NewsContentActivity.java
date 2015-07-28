package com.uestc.mymoa.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.uestc.mymoa.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DBJ_MAC on 2015/7/27.
 */
public class NewsContentActivity extends BaseActivity {
    @Override
    protected void initLayout() {
        Button backBtn01=(Button)findViewById(R.id.backButton01) ;
        TextView text01=(TextView)findViewById(R.id.realeseText);
        TextView text02=(TextView)findViewById(R.id.titleText03);
        TextView text03=(TextView)findViewById(R.id.authorText02);
        TextView text04=(TextView)findViewById(R.id.dateText02);
        TextView text05=(TextView)findViewById(R.id.commentText);
        ListView lv=(ListView)findViewById(R.id.commentList);

    }

    @Override
    protected void initListener() {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
        ListView lv=(ListView)this.findViewById(R.id.commentList);
        SimpleAdapter mSimpleAdapter = new SimpleAdapter
                (this,listItem, R.layout.layout_comment_item, new String[] {"commentorText","dateText03", "commentText01"},
                        new int[] {R.id.commentorText,R.id.dateText03,R.id.commentText01});
        lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                setTitle("你点击了第"+arg2+"行评论");//设置标题栏显示点击的行
            }
        });
    }

    @Override
    protected void initValue() {
        /*在数组中存放数据*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
        for(int i=0;i<10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("commentorText", "周杰宝");
            map.put("dateText03","2015-07-28    20:58:33");
            map.put("commentText01", "好好好，说的真特么好！！！从来没听过这么清新脱俗的话，吊吊吊！！");
            listItem.add(map);
        }
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_content;
    }
}

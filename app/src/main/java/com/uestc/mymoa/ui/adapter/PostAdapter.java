package com.uestc.mymoa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uestc.mymoa.R;
import com.uestc.mymoa.ui.PostDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nothisboy on 2015/7/27.
 */
public class PostAdapter extends PagerAdapter {

    private Context context;
    private List<Map<String, Object>> list;
    private List<View> views = new ArrayList<>();

    public PostAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.item_post, null);

        TextView titleText = (TextView) convertView.findViewById(R.id.titleText);
        TextView authorText = (TextView) convertView.findViewById(R.id.authorText);

        final Map<String,Object> map =list.get(position);
        titleText.setText(map.get("title").toString());
        authorText.setText("—— " + map.get("uname").toString());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 添加跳转到post详情页
                int id = (int)((double)map.get("postid")+0.5);
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("postid", map.get("postid").toString());
                context.startActivity(intent);
            }
        });

        container.addView(convertView);
        views.add(convertView);
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(views.get(position));
    }

}

package com.uestc.mymoa.ui.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.uestc.mymoa.io.model.Post;

/**
 * Created by HeGang on 2015/7/27.
 */
public class PostDBAdapter {
    private Context context;
    private PostDBHelper postDBHelper;
    private SQLiteDatabase db;

    public PostDBAdapter(Context context){
        this.context=context;
    }
    public void open(){
        postDBHelper=new PostDBHelper(context,PostStaticContact.DB_NAME,null,1);
        db=postDBHelper.getWritableDatabase();
    }

    public void close(){
        if(db !=null){
            postDBHelper.close();
            db=null;
        }
    }


    public long insertOnePost(Post post){
        ContentValues newValue=new ContentValues();
        newValue.put(PostStaticContact.KEY_TITLE,post.title);
        newValue.put(PostStaticContact.KEY_ARTICLE,post.article);
        return db.insert(PostStaticContact.DB_TABLE, null, newValue);
    }

    public Cursor queryAllPosts() {
        return db.query(PostStaticContact.DB_TABLE,new String[]{PostStaticContact.KEY_ID,PostStaticContact.KEY_TITLE,PostStaticContact.KEY_ARTICLE},null,null,PostStaticContact.KEY_ID,null,null,null);
    }



}

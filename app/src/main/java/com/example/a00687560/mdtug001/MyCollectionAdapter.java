package com.example.a00687560.mdtug001;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00687560.model.LibsCollection;

import java.util.List;

/**
 * Created by 00687560 on 2017/3/30.
 */

public class MyCollectionAdapter extends BaseAdapter {
    private List<LibsCollection> libsCollectionList;
    private Context context;
    public MyCollectionAdapter(Context context,List<LibsCollection> libsCollectionList) {
        super();
        this.libsCollectionList=libsCollectionList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return libsCollectionList.size();
    }

    @Override
    public Object getItem(int i) {
        return libsCollectionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_view_collection_item,viewGroup,false);
        }
        ImageView imageView= (ImageView) view.findViewById(R.id.image);
        TextView tvName= (TextView) view.findViewById(R.id.collection_name);

        //随机为学生匹配头像
        if(libsCollectionList.get(i).getId()%2==0)
        {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else{
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        tvName.setText(libsCollectionList.get(i).getLib_name());
        return view;
    }
}
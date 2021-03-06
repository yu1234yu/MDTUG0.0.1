package com.example.a00687560.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsInfo;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    private List<LibsInfo> libsInfoList;
    private Context context;

    public MyAdapter(Context context, List<LibsInfo> libsInfoList) {
        this.libsInfoList = libsInfoList;
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(View.inflate(context, android.R.layout.activity_list_item, null));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        String lib_name = libsInfoList.get(position).getLib_name();
        holder.mTextView.setText(lib_name);
    }
    @Override
    public int getItemCount() {
        return libsInfoList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView mTextView;
        VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.book_name);

        }
    }
}
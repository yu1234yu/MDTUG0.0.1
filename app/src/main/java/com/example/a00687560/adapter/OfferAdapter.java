package com.example.a00687560.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsOffer;
import java.util.List;

public class OfferAdapter extends BaseAdapter{
    private List<LibsOffer> libsOfferList;
    private Context context;

    public OfferAdapter(Context context, List<LibsOffer> libsOfferList) {
        super();
        this.libsOfferList = libsOfferList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return libsOfferList.size();
    }

    @Override
    public Object getItem(int i) {
        return libsOfferList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.list_view_offer_me_item,viewGroup,false);
        }
        TextView book_name= (TextView) view.findViewById(R.id.lib_name);
        TextView book_author= (TextView) view.findViewById(R.id.lib_author);
        TextView book_ISBN= (TextView) view.findViewById(R.id.lib_ISBN);

        book_name.setText(libsOfferList.get(i).getBook_name());
        book_author.setText(libsOfferList.get(i).getBook_author());
        book_ISBN.setText(libsOfferList.get(i).getBook_id());
        return view;
    }
}
package com.moviedemo.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moviedemo.R;
import com.moviedemo.data.SearchResultItem;

import java.util.List;

/**
 * Created by Administrator on 2015/9/2.
 */
public class SearchResultAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<SearchResultItem> list;



    public SearchResultAdapter(Context context, List<SearchResultItem> data) {
        this.mInflater = LayoutInflater.from(context);
        this.list = data;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SearchResultItemViewHolder holder = null;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.search_result_item, null);
            holder = new SearchResultItemViewHolder();
            holder.itemName = (TextView) view.findViewById(R.id.search_item_name);
            holder.itemExtra = (TextView) view.findViewById(R.id.search_item_extra);
            view.setTag(holder);
        }else {
            holder = (SearchResultItemViewHolder) view.getTag();
        }

        SearchResultItem item = this.list.get(i);
        holder.itemName.setText(item.getName());
        holder.itemExtra.setText(item.getActor());

        return view;
    }

    private static class SearchResultItemViewHolder {
        public TextView itemName;
        public TextView itemExtra;
    }
}

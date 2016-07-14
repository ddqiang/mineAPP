package com.example.dllo.a36kr.main.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/21.
 */
public class DrawerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> drawerTitle;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void setDrawerTitle(ArrayList<String> drawerTitle) {
        this.drawerTitle = drawerTitle;
        notifyDataSetChanged();
    }

    public DrawerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return drawerTitle == null ? 0 : drawerTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DrawerViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.new_drawer_lv_item, parent, false);
            holder = new DrawerViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (DrawerViewHolder) convertView.getTag();
        }
        holder.drawerTitleTv.setText(drawerTitle.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(position);
            }
        });
        return convertView;
    }

    class DrawerViewHolder {
        TextView drawerTitleTv;

        public DrawerViewHolder(View view) {
            drawerTitleTv = (TextView) view.findViewById(R.id.new_drawer_title);
        }
    }
}

package com.example.dllo.a36kr.main.person.mine_collect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/7/1.
 */
public class MineCollectAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<BmobCollectBean> bmobCollectBeanArrayList;
    private MyClickListener myClickListener;


    public void setBmobCollectBeanArrayList(ArrayList<BmobCollectBean> bmobCollectBeanArrayList) {
        this.bmobCollectBeanArrayList = bmobCollectBeanArrayList;
        notifyDataSetChanged();
    }

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MineCollectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bmobCollectBeanArrayList == null ? 0 : bmobCollectBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bmobCollectBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CollectViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_mine_collect_item, null);
            holder = new CollectViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CollectViewHolder) convertView.getTag();
        }

        holder.author.setText(bmobCollectBeanArrayList.get(position).getAuthor());
        holder.title.setText(bmobCollectBeanArrayList.get(position).getTextTitle());
        holder.time.setText(bmobCollectBeanArrayList.get(position).getTime());
        Picasso.with(context).load(bmobCollectBeanArrayList.get(position).getImage()).resize(100, 100).into(holder.head);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(position);
            }
        });
        return convertView;
    }

    class CollectViewHolder {
        ImageView head;
        TextView title, author, time;

        public CollectViewHolder(View view) {
            head = (ImageView) view.findViewById(R.id.mine_collect_head);
            title = (TextView) view.findViewById(R.id.mine_collect_context);
            author = (TextView) view.findViewById(R.id.mine_collect_author);
            time = (TextView) view.findViewById(R.id.mine_collect_time);
        }
    }
}

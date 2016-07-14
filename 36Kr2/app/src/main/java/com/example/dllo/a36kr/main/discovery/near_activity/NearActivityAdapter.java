package com.example.dllo.a36kr.main.discovery.near_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/28.
 */
public class NearActivityAdapter extends BaseAdapter {

    private Context context;
    private Bean bean;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
        notifyDataSetChanged();
    }

    public void setBean(Bean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public NearActivityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.discover_near_activity_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.getData().getData().get(position).getActivityImg()).into(holder.imageView);
        holder.nameTv.setText(bean.getData().getData().get(position).getActivityName());
        holder.desc.setText(bean.getData().getData().get(position).getActivityDesc());
        holder.status.setText(bean.getData().getData().get(position).getActivityStatus());

        //设置颜色
        if (holder.status.getText() == "报名中") {
            holder.status.setTextColor(android.graphics.Color.parseColor("#ffcc00"));
        } else if (holder.status.getText() == "活动中") {
            holder.status.setTextColor(android.graphics.Color.parseColor("#ffcc00"));
        } else if (holder.status.getText() == "已结束") {
            holder.status.setTextColor(android.graphics.Color.parseColor("#ffcc00"));
        }

        holder.city.setText(bean.getData().getData().get(position).getActivityCity());
        holder.time.setText(bean.getData().getData().get(position).getActivityTime());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(position);
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView nameTv, desc, status, city, time;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.discover_nearActivity_item_actImg);
            nameTv = (TextView) view.findViewById(R.id.discover_nearActivity_item_actName);
            desc = (TextView) view.findViewById(R.id.discover_nearActivity_item_actDesc);
            status = (TextView) view.findViewById(R.id.discover_nearActivity_item_actStatus);
            city = (TextView) view.findViewById(R.id.discover_nearActivity_item_actCity);
            time = (TextView) view.findViewById(R.id.discover_nearActivity_item_actTime);
        }
    }
}

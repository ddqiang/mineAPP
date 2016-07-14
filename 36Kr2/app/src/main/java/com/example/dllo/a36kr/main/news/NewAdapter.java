package com.example.dllo.a36kr.main.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by dllo on 16/6/17.
 */
public class NewAdapter extends BaseAdapter {

    private NewBean newBeen;
    private Context context;
    MyClickListener clickListener;
    private Animation animation, animationTwo;


    public void setClickListener(MyClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public NewAdapter(Context context) {
        this.context = context;
        animation = AnimationUtils.loadAnimation(context, R.anim.in_anim);
        animationTwo = AnimationUtils.loadAnimation(context, R.anim.out_anim);
    }

    public void setNewBeen(NewBean newBeen) {
        this.newBeen = newBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return newBeen == null ? 0 : newBeen.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return newBeen.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        NewViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.new_lv_item, parent, false);
            holder = new NewViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NewViewHolder) convertView.getTag();
        }
        holder.nameTv.setText(newBeen.getData().getData().get(position).getUser().getName());
        holder.titleTv.setText(newBeen.getData().getData().get(position).getTitle());
        holder.columnTv.setText(newBeen.getData().getData().get(position).getColumnName());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(newBeen.getData().getData().get(position).getPublishTime());
        holder.timeTv.setText(time);
        Picasso.with(context).load(newBeen.getData().getData().get(position).getFeatureImg()).into(holder.imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(position);
            }
        });

//        convertView.startAnimation(animation);
//        if (position % 2 == 0) {
//        } else {
//            convertView.startAnimation(animationTwo);
//        }
        return convertView;
    }

    class NewViewHolder {
        ImageView imageView;
        TextView titleTv, nameTv, timeTv, columnTv;

        public NewViewHolder(View view) {
            nameTv = (TextView) view.findViewById(R.id.new_lv_name_tv);
            titleTv = (TextView) view.findViewById(R.id.new_lv_title_tv);
            imageView = (ImageView) view.findViewById(R.id.new_lv_icon_iv);
            timeTv = (TextView) view.findViewById(R.id.new_lv_time_tv);
            columnTv = (TextView) view.findViewById(R.id.new_lv_column_tv);
        }
    }
}

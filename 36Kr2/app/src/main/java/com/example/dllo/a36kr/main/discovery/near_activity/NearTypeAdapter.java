package com.example.dllo.a36kr.main.discovery.near_activity;

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
 * Created by dllo on 16/6/29.
 */
public class NearTypeAdapter {

//    private String[] typeTitle = {"全部", "DemoDay", "氪空间", "股权投资", "企业服务", "极速融资"};
//    private Context context;
//    private MyClickListener myClickListener;
//
//    public void setMyClickListener(MyClickListener myClickListener) {
//        this.myClickListener = myClickListener;
//    }
//
//    public void setTypeTitle(String[] typeTitle) {
//        this.typeTitle = typeTitle;
//        notifyDataSetChanged();
//    }
//
//    public NearTypeAdapter(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return typeTitle == null ? 0 : typeTitle.length;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return typeTitle[position];
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder holder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.near_activity_item_typepopu, null);
//            holder = new ViewHolder(convertView);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        holder.all.setText(typeTitle[0]);
//        holder.demoDay.setText(typeTitle[1]);
//        holder.space.setText(typeTitle[2]);
//        holder.invest.setText(typeTitle[3]);
//        holder.service.setText(typeTitle[4]);
//        holder.financing.setText(typeTitle[5]);
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myClickListener.onClick(position);
//            }
//        });
//
//        return convertView;
//    }
//
//    class ViewHolder {
//        TextView all, demoDay, space, invest, service, financing;
//
//        public ViewHolder(View view) {
//            all = (TextView) view.findViewById(R.id.near_activity_typeLv_all);
//            demoDay = (TextView) view.findViewById(R.id.near_activity_typeLv_demoDay);
//            space = (TextView) view.findViewById(R.id.near_activity_typeLv_space);
//            invest = (TextView) view.findViewById(R.id.near_activity_typeLv_invest);
//            service = (TextView) view.findViewById(R.id.near_activity_typeLv_service);
//            financing = (TextView) view.findViewById(R.id.near_activity_typeLv_financing);
//        }
//    }

}

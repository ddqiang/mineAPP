package com.example.dllo.a36kr.main.news.search;

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

import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/6/28.
 */
public class SearchAdapter extends BaseAdapter {

    private Context context;
    private SearchBean searchBean;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        notifyDataSetChanged();
    }

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return searchBean == null ? 0 : searchBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return searchBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(searchBean.getData().getData().get(position).getUser().getAvatar()).resize(100, 100).into(holder.headIv);
        holder.title.setText(searchBean.getData().getData().get(position).getTitle());
        holder.name.setText(searchBean.getData().getData().get(position).getUser().getName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        holder.time.setText(dateFormat.format(searchBean.getData().getData().get(position).getPublishTime()));
        holder.columnName.setText(searchBean.getData().getData().get(position).getColumnName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView headIv;
        TextView title, name, time, columnName;

        public ViewHolder(View view) {
            headIv = (ImageView) view.findViewById(R.id.search_iv);
            title = (TextView) view.findViewById(R.id.search_title_tv);
            name = (TextView) view.findViewById(R.id.search_name_tv);
            time = (TextView) view.findViewById(R.id.search_time_tv);
            columnName = (TextView) view.findViewById(R.id.search_column_tv);
        }
    }
}

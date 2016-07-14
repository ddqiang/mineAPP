package com.example.dllo.a36kr.main.news.krtv;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dllo.a36kr.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/25.
 */
public class KrTvAdapter extends BaseAdapter {

    private DrawerKeTvBean bean;
    private Context context;
    ViewHolder myViewHolder = null;
    private int palay = -1;

    public KrTvAdapter(Context context) {
        this.context = context;
    }

    public void setBean(DrawerKeTvBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_new_drawer_krtv_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.videoView.setVideoURI(Uri.parse(bean.getData().getData().get(position).getTv().getVideoSource()));
        holder.krTitle.setText(bean.getData().getData().get(position).getTv().getTitle());
        Picasso.with(context).load(bean.getData().getData().get(position).getTv().getFeatureImg()).into(holder.krIv);

        holder.videoView.setMediaController(new MediaController(context));//设置视频播放
        holder.videoView.pause();//暂停

        if (position == palay) {
            holder.videoView.start();
        } else {
            holder.videoView.pause();
        }

        final ViewHolder finalMyViewHolder = holder;
        holder.krIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewHolder != null) {
                    palay = position;
                    myViewHolder.videoView.pause();
                    myViewHolder.krIv.setVisibility(View.VISIBLE);
                    myViewHolder.videoView.setVisibility(View.GONE);
                    myViewHolder.krTitle.setVisibility(View.VISIBLE);
                }
                finalMyViewHolder.videoView.setVisibility(View.VISIBLE);
                finalMyViewHolder.krIv.setVisibility(View.GONE);
                finalMyViewHolder.krTitle.setVisibility(View.GONE);
                finalMyViewHolder.videoView.start();
                myViewHolder = finalMyViewHolder;
                palay = -1;
            }
        });
        return convertView;
    }

    class ViewHolder {
        VideoView videoView;
        ImageView krIv;
        TextView krTitle;

        public ViewHolder(View view) {
            videoView = (VideoView) view.findViewById(R.id.new_drawer_lv_krTv);
            krIv = (ImageView) view.findViewById(R.id.new_drawer_lv_krIv);
            krTitle = (TextView) view.findViewById(R.id.new_drawer_lv_krTitleTv);
        }
    }
}

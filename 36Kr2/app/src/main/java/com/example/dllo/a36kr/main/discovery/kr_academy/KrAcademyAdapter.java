package com.example.dllo.a36kr.main.discovery.kr_academy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/7/5.
 */
public class KrAcademyAdapter extends RecyclerView.Adapter<KrAcademyAdapter.MyViewHolder> {


    private Context context;
    private KrAcademyBean krAcademyBean;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public void setKrAcademyBean(KrAcademyBean krAcademyBean) {
        this.krAcademyBean = krAcademyBean;
        notifyDataSetChanged();
    }

    public KrAcademyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.new_lv_item, null);

        myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Picasso.with(context).load(krAcademyBean.getData().getData().get(position).getFeatureImg()).resize(200, 200).into(holder.imageView);
        holder.titleTv.setText(krAcademyBean.getData().getData().get(position).getTitle());
        holder.nameTv.setText(krAcademyBean.getData().getData().get(position).getUser().getName());
        SimpleDateFormat format = new SimpleDateFormat("HH:ss");
        holder.timeTv.setText(format.format(krAcademyBean.getData().getData().get(position).getPublishTime()));
        holder.columnTv.setText(null);

        if (myClickListener != null) {
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    myClickListener.onClick(pos);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return krAcademyBean == null ? 0 : krAcademyBean.getData().getData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTv, nameTv, timeTv, columnTv;
        RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.new_lv_name_tv);
            titleTv = (TextView) itemView.findViewById(R.id.new_lv_title_tv);
            imageView = (ImageView) itemView.findViewById(R.id.new_lv_icon_iv);
            timeTv = (TextView) itemView.findViewById(R.id.new_lv_time_tv);
            columnTv = (TextView) itemView.findViewById(R.id.new_lv_column_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.new_lv_RelativeLayout);
        }
    }
}

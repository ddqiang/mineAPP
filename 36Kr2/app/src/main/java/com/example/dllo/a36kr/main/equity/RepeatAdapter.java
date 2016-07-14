package com.example.dllo.a36kr.main.equity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.MyClickListener;
import com.example.dllo.a36kr.main.equity.equity_all_data.AllBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/6/26.
 */
public class RepeatAdapter extends BaseAdapter {

    private AllBean allBean;
    private Context context;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
        notifyDataSetChanged();
    }

    public void setAllBean(AllBean allBean) {
        this.allBean = allBean;
        notifyDataSetChanged();
    }

    public RepeatAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return allBean == null ? 0 : allBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return allBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.equity_lv_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(allBean.getData().getData().get(position).getCompany_logo()).into(holder.head);
        Picasso.with(context).load(allBean.getData().getData().get(position).getFile_list_img()).into(holder.details);
        holder.name.setText(allBean.getData().getData().get(position).getCompany_name());
        holder.introduce.setText(allBean.getData().getData().get(position).getCompany_brief());

        holder.invest.setText(allBean.getData().getData().get(position).getLead_name());
        holder.founderBefore.setText(allBean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        holder.founder.setText(allBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        holder.teamBefore.setText(allBean.getData().getData().get(position).getCf_advantage().get(1).getAdname());
        holder.team.setText(allBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());

        //设置progressBar
        int rate= (int) (allBean.getData().getData().get(position).getRate()*100);
        holder.rate.setText(String.valueOf(rate));
        holder.progressBar.setProgress(rate);

        return convertView;
    }

    class ViewHolder {
        ImageView head, details;
        TextView name, introduce, investBefore, invest, founderBefore, founder, teamBefore, team;
        TextView rate;
        ProgressBar progressBar;

        public ViewHolder(View view) {
            head = (ImageView) view.findViewById(R.id.equity_item_head);
            details = (ImageView) view.findViewById(R.id.equity_item_details);
            name = (TextView) view.findViewById(R.id.equity_item_name);
            introduce = (TextView) view.findViewById(R.id.equity_item_introduce);
            investBefore = (TextView) view.findViewById(R.id.equity_item_invest_before);
            invest = (TextView) view.findViewById(R.id.equity_item_invest);
            founderBefore = (TextView) view.findViewById(R.id.equity_item_founder_before);
            founder = (TextView) view.findViewById(R.id.equity_item_founder);
            teamBefore = (TextView) view.findViewById(R.id.equity_item_team_before);
            team = (TextView) view.findViewById(R.id.equity_item_team);

            rate= (TextView) view.findViewById(R.id.item_equip_all_rate);
            progressBar= (ProgressBar) view.findViewById(R.id.progress_bar);



        }
    }
}

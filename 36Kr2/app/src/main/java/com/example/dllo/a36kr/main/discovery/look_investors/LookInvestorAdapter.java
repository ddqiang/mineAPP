package com.example.dllo.a36kr.main.discovery.look_investors;

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
 * Created by dllo on 16/6/29.
 */
public class LookInvestorAdapter extends BaseAdapter {

    private Context context;
    private LookInvestorBean investorBeen;
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public LookInvestorAdapter(Context context) {
        this.context = context;
    }

    public void setInvestorBeen(LookInvestorBean investorBeen) {
        this.investorBeen = investorBeen;
    }

    @Override
    public int getCount() {
        return investorBeen == null ? 0 : investorBeen.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return investorBeen.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_look_investor_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(investorBeen.getData().getData().get(position).getUser().getAvatar()).resize(100, 100).into(holder.head);
        holder.name.setText(investorBeen.getData().getData().get(position).getUser().getName());
        if (investorBeen.getData().getData().get(position).getFocusIndustry().size() == 3) {
            String a = investorBeen.getData().getData().get(position).getFocusIndustry().get(0) + "," + investorBeen.getData().getData().get(position).getFocusIndustry().get(1) +
                    "," + investorBeen.getData().getData().get(position).getFocusIndustry().get(2);
            holder.focusIndustry.setText(a);

        } else if (investorBeen.getData().getData().get(position).getFocusIndustry().size() == 2) {
            String b = investorBeen.getData().getData().get(position).getFocusIndustry().get(0) + "," + investorBeen.getData().getData().get(position).getFocusIndustry().get(1);
            holder.focusIndustry.setText(b);
        } else if (investorBeen.getData().getData().get(position).getFocusIndustry().size() == 1) {
            String c = investorBeen.getData().getData().get(position).getFocusIndustry().get(0);
            holder.focusIndustry.setText(c);
        }

        if (investorBeen.getData().getData().get(position).getInvestPhases().size() == 3) {
            String d = investorBeen.getData().getData().get(position).getInvestPhases().get(0) + "," + investorBeen.getData().getData().get(position).getInvestPhases().get(1) +
                    "," + investorBeen.getData().getData().get(position).getInvestPhases().get(2);
            holder.investPhases.setText(d);
        } else if (investorBeen.getData().getData().get(position).getInvestPhases().size() == 2) {
            String e = investorBeen.getData().getData().get(position).getInvestPhases().get(0) + "," + investorBeen.getData().getData().get(position).getInvestPhases().get(1);
            holder.investPhases.setText(e);
        } else if (investorBeen.getData().getData().get(position).getInvestPhases().size() == 1) {
            String f = investorBeen.getData().getData().get(position).getInvestPhases().get(0);
            holder.investPhases.setText(f);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onClick(position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView head;
        TextView name, focusIndustry, investPhases;

        public ViewHolder(View view) {
            head = (ImageView) view.findViewById(R.id.discover_lookInvestor_item_head);
            name = (TextView) view.findViewById(R.id.discover_lookInvestor_item_name);
            focusIndustry = (TextView) view.findViewById(R.id.discover_lookInvestor_item_focusIndustry);
            investPhases = (TextView) view.findViewById(R.id.discover_lookInvestor_item_investPhases);
        }
    }
}

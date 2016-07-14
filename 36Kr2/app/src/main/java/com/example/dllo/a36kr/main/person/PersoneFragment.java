package com.example.dllo.a36kr.main.person;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseFragment;
import com.example.dllo.a36kr.main.person.login.BmobBean;
import com.example.dllo.a36kr.main.person.login.EditorMineActivity;
import com.example.dllo.a36kr.main.person.login.RegisteredAndLoginActivity;
import com.example.dllo.a36kr.main.person.mine_collect.MineCollectActivity;
import com.example.dllo.a36kr.main.person.mine_orders.MineOrdersActivity;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by dllo on 16/6/17.
 */
public class PersoneFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout myAccount, myMessage, myCompany, myhotLine, collectText, myLogin;
    private PopupWindow popupWindow;
    private TextView callNumber, undo, loginName;
    private ImageView setIv, headIv;
    private BmobBean bmobBean;
    private MyLoginAndOutSucceedBroadCast myLoginSucceedBroadCast;

    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        myAccount = (RelativeLayout) view.findViewById(R.id.my_account_relativeLayout);
        myMessage = (RelativeLayout) view.findViewById(R.id.my_message_reLayout);
        myCompany = (RelativeLayout) view.findViewById(R.id.my_company_relative);
        myhotLine = (RelativeLayout) view.findViewById(R.id.my_hotLine_relativeLayout);
        myLogin = (RelativeLayout) view.findViewById(R.id.my_login);
        collectText = (RelativeLayout) view.findViewById(R.id.my_company_collectText);
        setIv = (ImageView) view.findViewById(R.id.my_set_up);
        headIv = (ImageView) view.findViewById(R.id.fragment_my_round);
        loginName = (TextView) view.findViewById(R.id.my_login_name);

        setIv.setOnClickListener(this);
        headIv.setOnClickListener(this);
        myLogin.setOnClickListener(this);
        myAccount.setOnClickListener(this);
        myMessage.setOnClickListener(this);
        myCompany.setOnClickListener(this);
        myhotLine.setOnClickListener(this);
        collectText.setOnClickListener(this);
    }

    @Override
    public void initData() {
        bmobBean = BmobUser.getCurrentUser(context, BmobBean.class);
        if (bmobBean != null) {
            //查询云数据
            BmobQuery<BmobBean> query = new BmobQuery<>();
            query.addWhereEqualTo("username", bmobBean.getUsername());
            query.findObjects(context, new FindListener<BmobBean>() {
                @Override
                public void onSuccess(List<BmobBean> list) {

                    headIv.setImageBitmap(bmobBean.getImage());
                    loginName.setText(bmobBean.getName());
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            headIv.setImageResource(R.mipmap.common_avatar);
            loginName.setText("未登录");
        }
        myLoginSucceedBroadCast = new MyLoginAndOutSucceedBroadCast();
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(TheValues.LOGI_NAN_DOUT);
        context.registerReceiver(myLoginSucceedBroadCast, filter2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(myLoginSucceedBroadCast);
    }

    class MyLoginAndOutSucceedBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            bmobBean = BmobUser.getCurrentUser(context, BmobBean.class);
            if (bmobBean != null) {
                headIv.setImageBitmap(bmobBean.getImage());
                loginName.setText(bmobBean.getName());
            } else {
                headIv.setImageResource(R.mipmap.common_avatar);
                loginName.setText("未登录");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_account_relativeLayout:
                //跳转到我的订单
                startActivity(new Intent(context, MineOrdersActivity.class));
                break;
            case R.id.my_message_reLayout:
                //跳转到我的信息
                startActivity(new Intent(getContext(), MineMessageActivity.class));
                break;
            case R.id.my_company_relative:
                //跳转我投资的公司
                Intent intent = new Intent(context, EquityCompanyActivity.class);
                startActivityForResult(intent, TheValues.REQUEST_CODE);
                break;
            case R.id.my_hotLine_relativeLayout:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showMineHotLinePopu();
                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.mine_hot_line_undo:
                popupWindow.dismiss();
                break;
            case R.id.mine_hot_line_popu:

                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(TheValues.CALL_NUMBER));
                startActivity(callIntent);
                break;
            case R.id.my_set_up:
                //跳转到设置里
                startActivity(new Intent(context, MineSetActivity.class));
                break;
            case R.id.my_company_collectText:
                startActivity(new Intent(context, MineCollectActivity.class));
                break;
            case R.id.my_login:
                if (bmobBean != null) {
                    startActivity(new Intent(context, EditorMineActivity.class));
                } else {
                    startActivity(new Intent(context, RegisteredAndLoginActivity.class));
                }
                break;
        }
    }

    private void showMineHotLinePopu() {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_hot_line_popu, null);
        callNumber = (TextView) view.findViewById(R.id.mine_hot_line_popu);
        undo = (TextView) view.findViewById(R.id.mine_hot_line_undo);
        undo.setOnClickListener(this);
        callNumber.setOnClickListener(this);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        //popu 从顶部弹出
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }
}

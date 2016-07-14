package com.example.dllo.a36kr.main.news.new_item;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.MyApplication;
import com.example.dllo.a36kr.main.person.login.RegisteredAndLoginActivity;
import com.example.dllo.a36kr.main.person.mine_collect.BmobCollectBean;
import com.example.dllo.a36kr.main.tools.VolleySingle;
import com.example.dllo.a36kr.main.tools.lite_orm.SingleLiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/6/25.
 * 新闻item activity
 */

public class NewItemActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private ImageView headIv, backIv, commentIv, shareIv, setPopuIv;
    private RelativeLayout showDownPopu;
    private TextView nameTv, titleTv, timeTv, contextTv;
    private ScrollView scrollView;
    private LinearLayout toolbarLinea;
    private PopupWindow popupWindow;
    int page = 20;
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private String feedId;
    private CheckBox collectCb;
    private boolean isChecked = false;
    private BmobCollectBean bmobCollectBean;
    private SeekBar seekBar;
    private TextView smallText, bigText;
    private Html.ImageGetter imgGetter;
//    private MyTextIsCheck myTextIsCheck;

    @Override
    public int getLayout() {
        return R.layout.activity_new_item;
    }

    @Override
    public void initView() {
        headIv = (ImageView) findViewById(R.id.new_item_head);
        showDownPopu = (RelativeLayout) findViewById(R.id.new_item_showDownPopu);
        backIv = (ImageView) findViewById(R.id.new_item_back);
        commentIv = (ImageView) findViewById(R.id.new_item_comment);
        collectCb = (CheckBox) findViewById(R.id.new_item_collect);
        shareIv = (ImageView) findViewById(R.id.new_item_share);
        setPopuIv = (ImageView) findViewById(R.id.new_item_setPopu);
        nameTv = (TextView) findViewById(R.id.new_item_name);
        titleTv = (TextView) findViewById(R.id.new_item_title);
        timeTv = (TextView) findViewById(R.id.new_item_time);
        contextTv = (TextView) findViewById(R.id.new_item_context);
        scrollView = (ScrollView) findViewById(R.id.new_item_scrollV);
        toolbarLinea = (LinearLayout) findViewById(R.id.new_item_toolbar);

        shareIv.setOnClickListener(this);
        collectCb.setOnClickListener(this);
        backIv.setOnClickListener(this);
        showDownPopu.setOnClickListener(this);
        scrollView.setOnTouchListener(this);
        setPopuIv.setOnClickListener(this);
    }

    @Override
    public void initData() {
        feedId = getIntent().getStringExtra("feedId");
        bmobCollectBean = new BmobCollectBean();
        initResponse();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 查询数据库
        List<BmobCollectBean> liteTextBeen = SingleLiteOrm.getSingleLiteOrm().getLiteOrm().query(BmobCollectBean.class);
        // 遍历数据库信息 (查重)
        for (BmobCollectBean bean : liteTextBeen) {
            if (feedId.equals(bean.getFeedId())) {
                collectCb.setChecked(true);
                isChecked = true;
            }
        }
    }

    //顶部 popuWindow
    private void showpopu() {
        View view = LayoutInflater.from(this).inflate(R.layout.new_item_showpopu, null);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(showDownPopu, 0, 0);
    }


    private void showBottonPopu() {
        View view = LayoutInflater.from(this).inflate(R.layout.new_item_showbottompopu, null);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        seekBar = (SeekBar) view.findViewById(R.id.new_item_popu_seekbar);
        smallText = (TextView) view.findViewById(R.id.new_item_textSmall);
        bigText = (TextView) view.findViewById(R.id.new_item_textBig);
        smallText.setOnClickListener(this);
        bigText.setOnClickListener(this);

        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int cur = seekBar.getProgress();
                NewItemActivity.this.setScreenBrightness(cur / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    //设置屏幕亮度的函数
    private void setScreenBrightness(float num) {
        WindowManager.LayoutParams layoutParams = super.getWindow().getAttributes();
        layoutParams.screenBrightness = num;//设置屏幕的亮度
        super.getWindow().setAttributes(layoutParams);
    }

    public static void struct() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }


    //获取图片
    private void getImage() {
        imgGetter = new Html.ImageGetter() {
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                URL url;
                try {
                    url = new URL(source);
                    drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
//              Drawable的setBounds方法有四个参数，setBounds(int left, int top, int right, int bottom),
//              这个四参数指的是drawable将在被绘制在canvas的哪个矩形区域内。
//              getIntrinsicWidth() 用着个话的比实际的小

                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                return drawable;
            }
        };
        struct();
    }

    private void initResponse() {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news/" + feedId, new Response.Listener<NewItemBean>() {
            @Override
            public void onResponse(NewItemBean response) {

                //获取图片
                getImage();

                Picasso.with(MyApplication.getContext()).load(response.getData().getUser()
                        .getAvatar()).resize(100, 100).into(headIv);
                nameTv.setText(response.getData().getUser().getName());
                titleTv.setText(response.getData().getTitle());

                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                timeTv.setText(format.format(response.getData().getPublishTime()));
                contextTv.setText(Html.fromHtml(response.getData().getContent(), imgGetter, null));

                //设置到数据库
                bmobCollectBean.setAuthor(response.getData().getUser().getName());
                bmobCollectBean.setTextTitle(response.getData().getTitle());
                bmobCollectBean.setTime(format.format(response.getData().getPublishTime()));
                bmobCollectBean.setImage(response.getData().getFeatureImg());
                bmobCollectBean.setFeedId(feedId);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewItemActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }, NewItemBean.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_item_showDownPopu:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showpopu();
                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.new_item_back:
                finish();
                break;
            case R.id.new_item_collect:
                isCheck();
                break;
            case R.id.new_item_setPopu:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showBottonPopu();
                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.new_item_textSmall:
                contextTv.setTextSize(10);
                popupWindow.dismiss();
                break;
            case R.id.new_item_textBig:
                contextTv.setTextSize(20);
                popupWindow.dismiss();
                break;
            case R.id.new_item_share:
                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

                // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                //oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sharesdk.cn");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("我是分享文本");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sharesdk.cn");

                // 启动分享GUI
                oks.show(this);
                break;
        }
    }

    private void isCheck() {
        if (isChecked) {
            // 说明本地数据库里已经有了这条数据,就把这条数据在数据库中删除
            SingleLiteOrm.getSingleLiteOrm().getLiteOrm().delete(new WhereBuilder(BmobCollectBean.class)
                    .where("feedId" + " = ?", new String[]{feedId}));
            Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();

            //删除云端数据库
            BmobQuery<BmobCollectBean> query = new BmobQuery<>();
            query.addWhereEqualTo("feedId", feedId);
            query.findObjects(this, new FindListener<BmobCollectBean>() {
                @Override
                public void onSuccess(List<BmobCollectBean> list) {
                    for (BmobCollectBean collectBean : list) {
                        collectBean.delete(NewItemActivity.this);
                    }
                        //通知<我的收藏页面>的数据刷新
                        Intent intent = new Intent("com.example.dllo.a36kr.db.MyCollectIsCheckBroadCast");
                        sendBroadcast(intent);
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {

            //存入云端数据库中
            //获得用户名
            if (BmobUser.getCurrentUser(this) == null) {
                collectCb.setChecked(false);
                startActivity(new Intent(NewItemActivity.this, RegisteredAndLoginActivity.class));
            } else {
                //存入本地数据库中
                SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(bmobCollectBean);

                bmobCollectBean.setUserName(BmobUser.getCurrentUser(this).getUsername());
                bmobCollectBean.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MyApplication.context, "收藏成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(NewItemActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
                    }
                });
                isChecked = !isChecked;
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (popupWindow != null) {

            popupWindow.dismiss();
        }
//继承了Activity的onTouchEvent方法，直接监听点击事件
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if (y1 - y2 > 50) {
                toolbarLinea.setVisibility(View.GONE);
            } else if (y2 - y1 > 50) {
                toolbarLinea.setVisibility(View.VISIBLE);
            } else if (x1 - x2 > 50) {
//                Toast.makeText(NewItemActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if (x2 - x1 > 50) {
//                Toast.makeText(NewItemActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onTouchEvent(event);
    }
}

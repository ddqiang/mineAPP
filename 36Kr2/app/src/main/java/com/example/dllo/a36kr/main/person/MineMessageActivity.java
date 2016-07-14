package com.example.dllo.a36kr.main.person;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.person.login.BmobBean;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by dllo on 16/6/30.
 */
public class MineMessageActivity extends BaseActivity implements View.OnClickListener {

    private ImageView backOne;
    private ImageView headImage;
    private EditText name, trueName, introduction;
    private TextView saveTv;
    private BmobBean bmobBean;
    private PopupWindow popupWindow;

    @Override
    public int getLayout() {
        return R.layout.activity_mine_message;
    }

    @Override
    public void initView() {
        backOne = (ImageView) findViewById(R.id.mine_message_backOne);
        headImage = (ImageView) findViewById(R.id.mine_message_headIv);
        name = (EditText) findViewById(R.id.mine_message_name);
        trueName = (EditText) findViewById(R.id.mine_message_trueName);
        introduction = (EditText) findViewById(R.id.mine_message_introduction);
        saveTv = (TextView) findViewById(R.id.mine_message_save);
        saveTv.setOnClickListener(this);
        backOne.setOnClickListener(this);
        headImage.setOnClickListener(this);
    }

    @Override
    public void initData() {

        bmobBean = BmobUser.getCurrentUser(this, BmobBean.class);

        if (bmobBean != null) {
            //查询云数据
            BmobQuery<BmobBean> query = new BmobQuery<>();
            query.addWhereEqualTo("username", bmobBean.getUsername());
            query.findObjects(this, new FindListener<BmobBean>() {
                @Override
                public void onSuccess(List<BmobBean> list) {
                    name.setText(bmobBean.getName());
                    trueName.setText(bmobBean.getTrueName());
                    introduction.setText(bmobBean.getIntroduction());
                    headImage.setImageBitmap(bmobBean.getImage());
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        } else {
            headImage.setImageResource(R.mipmap.common_avatar);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_message_backOne:
                finish();
                break;
            case R.id.mine_message_save:
                bmobBean.setName(name.getText().toString());
                bmobBean.setTrueName(trueName.getText().toString());
                bmobBean.setIntroduction(introduction.getText().toString());
                bmobBean.update(this, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        //发送广播通知主页改变头像
                        Intent intent = new Intent(TheValues.LOGI_NAN_DOUT);
                        intent.putExtra("headIv", bmobBean.getImage());
                        sendBroadcast(intent);
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });

                finish();
                break;
            case R.id.mine_message_headIv:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    showPopu();

                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.mine_message_close:
                popupWindow.dismiss();
                break;
            case R.id.mine_message_localImage:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, TheValues.IMAGE_UNSPECIFIED);
                startActivityForResult(intent, TheValues.ALBUM_REQUEST_CODE);
                break;
        }
    }

    //获取本地图片或拍照
    private void showPopu() {

        View view = LayoutInflater.from(this).inflate(R.layout.mine_message_image, null);
        TextView camear = (TextView) view.findViewById(R.id.mine_message_camera);
        TextView locrdImage = (TextView) view.findViewById(R.id.mine_message_localImage);
        TextView close = (TextView) view.findViewById(R.id.mine_message_close);

        camear.setOnClickListener(this);
        locrdImage.setOnClickListener(this);
        close.setOnClickListener(this);
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 开始裁剪
     *
     * @param uri
     */
    private void startCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");// 调用Android系统自带的一个图片剪裁页面,
        intent.setDataAndType(uri, TheValues.IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");// 进行修剪
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, TheValues.CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case TheValues.ALBUM_REQUEST_CODE:
                // Log.i(TAG, "相册，开始裁剪");
                // Log.i(TAG, "相册 [ " + data + " ]");
                if (data == null) {
                    return;
                }
                startCrop(data.getData());
                break;
            case TheValues.CAMERA_REQUEST_CODE:
                // Log.i(TAG, "相机, 开始裁剪");
                File picture = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
                startCrop(Uri.fromFile(picture));
                break;
            case TheValues.CROP_REQUEST_CODE:
                // Log.i(TAG, "相册裁剪成功");
                // Log.i(TAG, "裁剪以后 [ " + data + " ]");
                if (data == null) {
                    // TODO 如果之前以后有设置过显示之前设置的图片 否则显示默认的图片
                    return;
                }
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                    // 此处可以把Bitmap保存到sd卡中
                    headImage.setImageBitmap(photo); // 把图片显示在ImageView控件上
                    bmobBean.setImage(photo);
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}

package com.example.dllo.a36kr.main.person.login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.ByteArrayOutputStream;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/7/4.
 * 云端数据->我的信息
 */
public class BmobBean extends BmobUser {
    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private byte[] photoImage;
    private String introduction;
    private String trueName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Bitmap getImage() {
        if (photoImage == null) {
            return null;
        }
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(photoImage, 0, photoImage.length);
        return imageBitmap;
    }
    public void setImage(Bitmap image) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, output);
        //转换成功了
        photoImage = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

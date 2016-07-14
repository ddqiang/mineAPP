package com.example.dllo.a36kr.main.tools.lite_orm;

import android.content.Context;

import com.example.dllo.a36kr.main.base.MyApplication;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by dllo on 16/7/1.
 */
@Table("collection")
public class SingleLiteOrm {

    private LiteOrm liteOrm;
    private static SingleLiteOrm singleliteOrm = new SingleLiteOrm();
    private Context context;

    private SingleLiteOrm() {
        context = MyApplication.getContext();
    }

    public static SingleLiteOrm getSingleLiteOrm() {
        return singleliteOrm;
    }

    public LiteOrm getLiteOrm() {
        if (liteOrm == null) {
            liteOrm = LiteOrm.newCascadeInstance(context, "dbName.db");
        }
        return liteOrm;
    }
}

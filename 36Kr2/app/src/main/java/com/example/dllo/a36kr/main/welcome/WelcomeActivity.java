package com.example.dllo.a36kr.main.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.main.MainActivity;

/**
 * Created by dllo on 16/6/17.
 */
public class WelcomeActivity extends AppCompatActivity {
    private TextView jumpTv;
    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        jumpTv = (TextView) findViewById(R.id.welcome_time_jump_tv);
        jumpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //避免重复跳转()
                timer.cancel();
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
//                WelcomeActivity.this.overridePendingTransition();
                finish();
            }
        });
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                jumpTv.setText(millisUntilFinished / 1000 + "s 跳转");
            }

            @Override
            public void onFinish() {
                jumpTv.setText("");
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));

            }
        }.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        timer.start();
    }
}

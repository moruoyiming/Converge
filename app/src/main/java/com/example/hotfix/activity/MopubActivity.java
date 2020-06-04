package com.example.hotfix.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/4 6:13 PM
 *     Description:
 * </pre>
 */
public class MopubActivity extends AppCompatActivity implements View.OnClickListener {
    TextView banner, interstitial, rewardedvideo, nativead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);
        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);
        interstitial = findViewById(R.id.interstitial);
        interstitial.setOnClickListener(this);
        rewardedvideo = findViewById(R.id.rewardedvideo);
        rewardedvideo.setOnClickListener(this);
        nativead = findViewById(R.id.nativead);
        nativead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                break;
            case R.id.interstitial:
                break;
            case R.id.rewardedvideo:
                break;
            case R.id.nativead:
                break;
        }
    }
}

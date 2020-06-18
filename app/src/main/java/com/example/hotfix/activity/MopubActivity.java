package com.example.hotfix.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;


/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/4 6:13 PM
 *     Description:
 * </pre>
 */
public class MopubActivity extends AppCompatActivity implements View.OnClickListener {
    public String TAG = MopubActivity.class.getSimpleName();
    TextView banner, interstitial, rewardedvideo, nativead;
        public static final String bannerId = "ca-app-pub-3940256099942544/6300978111";
    public static final String interstitialId = "ca-app-pub-3940256099942544/1033173712";
    public static final String rewardedvideoId = "ca-app-pub-3940256099942544/5224354917";
//    public static final String bannerId = "ca-app-pub-8241795889030186/7863074195";
//    public static final String interstitialId = "ca-app-pub-8241795889030186/6482791336";
//    public static final String rewardedvideoId = "ca-app-pub-8241795889030186/6285202985";
//    public static final String bannerId = "ca-app-pub-1498325132484748/1213683867";
//    public static final String interstitialId = "ca-app-pub-1498325132484748/4961357181";
//    public static final String rewardedvideoId = "ca-app-pub-1498325132484748/1022112177";



    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;
    RewardedAdCallback adCallback = new RewardedAdCallback() {
        @Override
        public void onRewardedAdOpened() {
            Log.i(TAG, "RewardedAd - Ad opened.");
        }

        @Override
        public void onRewardedAdClosed() {
            Log.i(TAG, "RewardedAd - Ad closed.");
            //TODO onCLose 通知游戏
            rewardedAd = createAndLoadRewardedAd();
        }

        @Override
        public void onUserEarnedReward(@NonNull RewardItem reward) {
            Log.i(TAG, "RewardedAd - User earned reward.");
            //TODO onShow 通知游戏
        }

        @Override
        public void onRewardedAdFailedToShow(int errorCode) {
            Log.i(TAG, "RewardedAd - Ad failed to display.");
        }
    };

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
        adView = findViewById(R.id.adView);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                Log.i(TAG, "Banner - Code to be executed when an ad finishes loading.   Banner adapter class name: " + adView.getResponseInfo().getMediationAdapterClassName());
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i(TAG, "Banner - Code to be executed when an ad request fails.");
            }

            @Override
            public void onAdOpened() {
                Log.i(TAG, "Banner - Code to be executed when an ad opens an overlay that");
            }

            @Override
            public void onAdClicked() {
                Log.i(TAG, "Banner - Code to be executed when the user clicks on an ad.");
            }

            @Override
            public void onAdLeftApplication() {
                Log.i(TAG, "Banner - Code to be executed when the user has left the app.");
            }

            @Override
            public void onAdClosed() {
                Log.i(TAG, "Banner - Code to be executed when the user is about to return to the app after tapping on an ad.");
            }
        });
        mInterstitialAd = crateInterstitialAd();
        rewardedAd = createAndLoadRewardedAd();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                Bundle bundle = new MoPubAdapter.BundleBuilder()
                        .setPrivacyIconSize(15)
                        .build();
                AdRequest adRequest = new AdRequest.Builder()
//                        .addNetworkExtrasBundle(MoPubAdapter.class, bundle)
//                        .addTestDevice("FA4B13F3B07E1C7D96F15E14E3B7A390")
                        .build();
//                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                break;
            case R.id.interstitial:
                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                break;
            case R.id.rewardedvideo:
                if (rewardedAd.isLoaded()) {
                    rewardedAd.show(MopubActivity.this, adCallback);
                }
                break;
            case R.id.nativead:
//                Intent intent= new Intent(MopubActivity.this,NativeActivity.class);
//                startActivity(intent);
                break;
        }
    }

    public RewardedAd createAndLoadRewardedAd() {
        RewardedAd rewardedAd = new RewardedAd(this, rewardedvideoId);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                //TODO onLoad 通知游戏
                Log.i(TAG, "RewardedAd - Ad successfully loaded.  " + rewardedAd.getResponseInfo().getMediationAdapterClassName());
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                //TODO onError 通知游戏
                Log.i(TAG, "RewardedAd - Ad failed to load.  "+errorCode);
            }
        };
        Bundle bundle = new MoPubAdapter.BundleBuilder()
                .setPrivacyIconSize(15)
                .build();
        AdRequest adRequest2 = new AdRequest.Builder()
//                .addNetworkExtrasBundle(MoPubAdapter.class, bundle)
//                .addTestDevice("FA4B13F3B07E1C7D96F15E14E3B7A390")
                .build();
        rewardedAd.loadAd(adRequest2, adLoadCallback);
        return rewardedAd;
    }

    public InterstitialAd crateInterstitialAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "InterstitialAd - Code to be executed when an ad finishes loading. " + mInterstitialAd.getResponseInfo().getMediationAdapterClassName());

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.i(TAG, "InterstitialAd - Code to be executed when an ad request fails.");
            }

            @Override
            public void onAdOpened() {
                Log.i(TAG, "InterstitialAd - Code to be executed when an ad opens an overlay that");
            }

            @Override
            public void onAdClicked() {
                Log.i(TAG, "InterstitialAd - Code to be executed when the user clicks on an ad.");
            }

            @Override
            public void onAdLeftApplication() {
                Log.i(TAG, "InterstitialAd - Code to be executed when the user has left the app.");
            }

            @Override
            public void onAdClosed() {
                Log.i(TAG, "InterstitialAd - Code to be executed when the user is about to return.");
                mInterstitialAd = null;
                mInterstitialAd = crateInterstitialAd();
            }
        });
        mInterstitialAd.setAdUnitId(interstitialId);
        Bundle bundle2 = new MoPubAdapter.BundleBuilder()
                .setPrivacyIconSize(15)
                .build();
        AdRequest adRequest2 = new AdRequest.Builder()
//                .addNetworkExtrasBundle(MoPubAdapter.class, bundle2)
//                .addTestDevice("FA4B13F3B07E1C7D96F15E14E3B7A390")
                .build();
        mInterstitialAd.loadAd(adRequest2);
        return mInterstitialAd;
    }


}

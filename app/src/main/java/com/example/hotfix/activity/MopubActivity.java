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
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    private RewardedAd rewardedAd;

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
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.i(TAG, "InterstitialAd - Code to be executed when an ad finishes loading.");
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
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
            }
        });
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
                        .addNetworkExtrasBundle(MoPubAdapter.class, bundle)
                        .build();
//                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                break;
            case R.id.interstitial:
                mInterstitialAd.setAdUnitId(interstitialId);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                break;
            case R.id.rewardedvideo:
                if (rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            Log.i(TAG, "RewardedAd - Ad opened.");
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            Log.i(TAG, "RewardedAd - Ad closed.");
                            rewardedAd = createAndLoadRewardedAd();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem reward) {
                            Log.i(TAG, "RewardedAd - User earned reward.");
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            Log.i(TAG, "RewardedAd - Ad failed to display.");
                        }
                    };
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
                Log.i(TAG, "RewardedAd - Ad successfully loaded.");
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                Log.i(TAG, "RewardedAd - Ad failed to load.");
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }


}

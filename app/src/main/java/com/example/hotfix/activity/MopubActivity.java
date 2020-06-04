package com.example.hotfix.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotfix.R;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.mobileads.MoPubView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *     author: jian
 *     Date  : 2020/6/4 6:13 PM
 *     Description:
 * </pre>
 */
public class MopubActivity extends AppCompatActivity implements View.OnClickListener, MoPubView.BannerAdListener, MoPubInterstitial.InterstitialAdListener {
    public String TAG = MopubActivity.class.getSimpleName();
    TextView banner, interstitial, rewardedvideo, nativead;
    public static final String bannerId = "6c6130db18c84b28827605f1198b164b";
    public static final String interstitialId = "1aeb47f0fcd9416dbfb5630d0fc503bd";
    public static final String rewardedvideoId = "9982562e23234980baeae2078875651d";
    private MoPubView moPubView;
    private MoPubInterstitial mInterstitial;
    private MoPubRewardedVideoListener rewardedVideoListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mopub);
        init();
        MoPub.onCreate(this);
        initVide();
        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);
        interstitial = findViewById(R.id.interstitial);
        interstitial.setOnClickListener(this);
        rewardedvideo = findViewById(R.id.rewardedvideo);
        rewardedvideo.setOnClickListener(this);
        nativead = findViewById(R.id.nativead);
        nativead.setOnClickListener(this);
        moPubView = findViewById(R.id.mb);
        moPubView.setBannerAdListener(this);
    }

    public void init() {
        // configurations required to initialize
        Map<String, String> mediatedNetworkConfiguration1 = new HashMap<>();
        mediatedNetworkConfiguration1.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>");
        Map<String, String> mediatedNetworkConfiguration2 = new HashMap<>();
        mediatedNetworkConfiguration2.put("<custom-adapter-class-data-key>", "<custom-adapter-class-data-value>");

        SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder("bfcdae4e4fa142c0941dd4dcde3a48d6")
//                .withMediationSettings("MEDIATION_SETTINGS")
//                .withAdditionalNetworks(CustomAdapterConfiguration.class.getName())
//                .withMediatedNetworkConfiguration(CustomAdapterConfiguration1.class.getName(), mediatedNetworkConfiguration)
//                .withMediatedNetworkConfiguration(CustomAdapterConfiguration2.class.getName(), mediatedNetworkConfiguration)
//                .withMediatedNetworkConfiguration(CustomAdapterConfiguration1.class.getName(), mediatedNetworkConfiguration1)
//                .withMediatedNetworkConfiguration(CustomAdapterConfiguration2.class.getName(), mediatedNetworkConfiguration2)
                .withLogLevel(MoPubLog.LogLevel.DEBUG)
                .withLegitimateInterestAllowed(false)
                .build();

        MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());


    }

    private SdkInitializationListener initSdkListener() {
        return new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                Toast.makeText(getApplicationContext(), "MoPub SDK initialized", Toast.LENGTH_SHORT).show();
           /* MoPub SDK initialized.
           Check if you should show the consent dialog here, and make your ad requests. */
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                moPubView.setAutorefreshEnabled(true);
                moPubView.setAdUnitId(bannerId); // Enter your Ad Unit ID from www.mopub.com
                moPubView.setAdSize(MoPubView.MoPubAdSize.HEIGHT_50); // Call this if you are not setting the ad size in XML or wish to use an ad size other than what has been set in the XML. Note that multiple calls to `setAdSize()` will override one another, and the MoPub SDK only considers the most recent one.
//                moPubView.loadAd(MoPubAdSize); // Call this if you are not calling setAdSize() or setting the size in XML, or if you are using the ad size that has not already been set through either setAdSize() or in the XML
                moPubView.loadAd();
                break;
            case R.id.interstitial:
                mInterstitial = new MoPubInterstitial(this, interstitialId);
                mInterstitial.setInterstitialAdListener(this);
                mInterstitial.load();
                break;
            case R.id.rewardedvideo:
                if(MoPubRewardedVideos.hasRewardedVideo(rewardedvideoId)){
                    Log.i(TAG, "hasRewardedVideo");
                    MoPubRewardedVideos.showRewardedVideo(rewardedvideoId);
                }else{
                    Log.i(TAG, "no hasRewardedVideo");
                }
                break;
            case R.id.nativead:
                break;
        }
    }

    @Override
    public void onBannerLoaded(@NonNull MoPubView banner) {
        Log.i(TAG, "onBannerLoaded" + banner);
        Toast.makeText(getApplicationContext(),
                "Banner successfully loaded.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        Log.i(TAG, "onBannerFailed " + errorCode.toString() + "   " + errorCode.getIntCode());
        Toast.makeText(getApplicationContext(),
                "Banner onBannerFailed errorCode" + errorCode.getIntCode() + "  " + errorCode.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBannerClicked(MoPubView banner) {
        Log.i(TAG, "onBannerClicked" + banner);
    }

    @Override
    public void onBannerExpanded(MoPubView banner) {
        Log.i(TAG, "onBannerExpanded" + banner);
    }

    @Override
    public void onBannerCollapsed(MoPubView banner) {
        Log.i(TAG, "onBannerCollapsed" + banner);
    }

    @Override
    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialLoaded");
        Toast.makeText(getApplicationContext(),
                "Interstitial successfully loaded.", Toast.LENGTH_SHORT).show();
        if (mInterstitial.isReady()) {
            mInterstitial.show();
        } else {
            // Caching is likely already in progress if `isReady()` is false.
            // Avoid calling `load()` here and instead rely on the callbacks as suggested below.
        }
    }

    @Override
    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Log.i(TAG, "onInterstitialFailed");
        Toast.makeText(getApplicationContext(),
                "Interstitial Failed loaded.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInterstitialShown(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialShown");
    }

    @Override
    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialClicked");
    }

    @Override
    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialDismissed");
    }

    public void initVide() {
        rewardedVideoListener = new MoPubRewardedVideoListener() {
            @Override
            public void onRewardedVideoLoadSuccess(String adUnitId) {
                // Called when the video for the given adUnitId has loaded. At this point you should be able to call MoPubRewardedVideos.showRewardedVideo(String) to show the video.
                Log.i(TAG, "onRewardedVideoLoadSuccess");
            }

            @Override
            public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
                // Called when a video fails to load for the given adUnitId. The provided error code will provide more insight into the reason for the failure to load.
                Log.i(TAG, "onRewardedVideoLoadFailure");
            }

            @Override
            public void onRewardedVideoStarted(String adUnitId) {
                // Called when a rewarded video starts playing.
                Log.i(TAG, "onRewardedVideoStarted");
            }

            @Override
            public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
                //  Called when there is an error during video playback.
                Log.i(TAG, "onRewardedVideoPlaybackError");
            }

            @Override
            public void onRewardedVideoClicked(@NonNull String adUnitId) {
                //  Called when a rewarded video is clicked.
                Log.i(TAG, "onRewardedVideoClicked");
            }

            @Override
            public void onRewardedVideoClosed(String adUnitId) {
                // Called when a rewarded video is closed. At this point your application should resume.
                Log.i(TAG, "onRewardedVideoClosed");
            }

            @Override
            public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
                // Called when a rewarded video is completed and the user should be rewarded.
                // You can query the reward object with boolean isSuccessful(), String getLabel(), and int getAmount().
                Log.i(TAG, "onRewardedVideoCompleted");
            }
        };

        MoPubRewardedVideos.setRewardedVideoListener(rewardedVideoListener);
        MoPubRewardedVideos.loadRewardedVideo(rewardedvideoId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MoPub.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MoPub.onStop(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MoPub.onResume(this);
    }
}

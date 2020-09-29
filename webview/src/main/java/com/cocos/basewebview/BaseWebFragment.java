package com.cocos.basewebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.cocos.base.loadsir.ErrorCallback;
import com.cocos.base.loadsir.LoadingCallback;
import com.cocos.basewebview.command.ShowDialogCommand;
import com.cocos.basewebview.command.ToastCommand;
import com.cocos.basewebview.databinding.FragmentCommonWebviewBinding;
import com.cocos.basewebview.mainprocess.CommandsManager;
import com.cocos.basewebview.utils.WebConstants;
import com.cocos.basewebview.widget.BaseWebView;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

public abstract class BaseWebFragment extends Fragment implements WebViewCallBack , OnRefreshListener {
    public static final String ACCOUNT_INFO_HEADERS = "account_header";
    //    protected BaseWebView webView;
    protected HashMap<String, String> accountInfoHeaders;
    public static final int REQUEST_CODE_LOLIPOP = 1;
    private LoadService loadService;
    private String webUrl;
    private boolean mIsError = false;
    private boolean mCanNativeRefresh = false;
    FragmentCommonWebviewBinding binding;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            webUrl = bundle.getString(WebConstants.INTENT_TAG_URL);
            mCanNativeRefresh = bundle.getBoolean(WebConstants.INTENT_TAG_CAN_NATIVE_REFRESH);
            if (bundle.containsKey(ACCOUNT_INFO_HEADERS)) {
                accountInfoHeaders = (HashMap<String, String>) bundle.getSerializable(ACCOUNT_INFO_HEADERS);
            }
        }
        CommandsManager.getInstance().registerCommand(new ToastCommand());
        CommandsManager.getInstance().registerCommand(new ShowDialogCommand());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutRes(), container, false);
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
//        webView = view.findViewById(R.id.web_view);
        if (accountInfoHeaders != null) {
            binding.webView.setHeaders(accountInfoHeaders);
        }
        binding.smartrefreshlayout.setOnRefreshListener(this);
        binding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        binding.smartrefreshlayout.setEnableLoadMore(false);
        loadService = LoadSir.getDefault().register(binding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                binding.webView.reload();
            }
        });
        return loadService.getLoadLayout();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.webView.registerdWebViewCallBack(this);
        loadUrl();
    }

    protected void loadUrl() {
        binding.webView.loadUrl(webUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.webView.dispatchEvent("pageResume");
        binding.webView.onResume();
    }

    @Override
    public void pageStarted(String url) {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if(mIsError) {
            binding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            binding.smartrefreshlayout.setEnableRefresh(mCanNativeRefresh);
        }
        binding.smartrefreshlayout.finishRefresh();
        if (loadService != null) {
            if(mIsError){
                loadService.showCallback(ErrorCallback.class);
            }   else {
                loadService.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onError() {
        mIsError = true;
        binding .smartrefreshlayout.finishRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.webView.dispatchEvent("pagePause");
        binding.webView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.webView.dispatchEvent("pageStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.webView.dispatchEvent("pageDestroy");
        clearWebView(binding.webView);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return onBackHandle();
        }
        return false;
    }

    protected boolean onBackHandle() {
        if (binding.webView != null) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private void clearWebView(WebView m) {
        if (m == null)
            return;
        if (Looper.myLooper() != Looper.getMainLooper())
            return;
        m.stopLoading();
        if (m.getHandler() != null) {
            m.getHandler().removeCallbacksAndMessages(null);
        }
        m.removeAllViews();
        ViewGroup mViewGroup = null;
        if ((mViewGroup = ((ViewGroup) m.getParent())) != null) {
            mViewGroup.removeView(m);
        }
        m.setWebChromeClient(null);
        m.setWebViewClient(null);
        m.setTag(null);
        m.clearHistory();
        m.destroy();
        m = null;
    }

    @Override
    public void onShowFileChooser(Intent cameraIntent, ValueCallback<Uri[]> filePathCallback) {
        //整个弹出框为:相机、相册、文件管理
        //如果安装了其他的相机、文件管理程序，也有可能会弹出
        //selectionIntent(相册、文件管理)
        //Intent selectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        //selectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        //selectionIntent.setType("image/*");
        mFilePathCallback = filePathCallback;
        //------------------------------------
        //如果通过下面的方式，则弹出的选择框有:相机、相册(Android9.0,Android8.0)
        //如果是小米Android6.0系统上，依然是：相机、相册、文件管理
        //如果安装了其他的相机(百度魔拍)、文件管理程序(ES文件管理器)，也有可能会弹出
        Intent selectionIntent = new Intent(Intent.ACTION_PICK, null);
        selectionIntent.setType("image/*");
        //------------------------------------

        Intent[] intentArray;
        if (cameraIntent != null) {
            intentArray = new Intent[]{cameraIntent};
        } else {
            intentArray = new Intent[0];
        }

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.file_chooser));
        chooserIntent.putExtra(Intent.EXTRA_INTENT, selectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);

        startActivityForResult(chooserIntent, REQUEST_CODE_LOLIPOP);

    }

    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_LOLIPOP:
                Uri[] results = null;
                // Check that the response is a good one
                if (resultCode == Activity.RESULT_OK) {
                    if (data == null) {
                        // If there is not data, then we may have taken a photo
                        if (mCameraPhotoPath != null) {
                            Log.d("AppChooserFragment", mCameraPhotoPath);
                            results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                        }
                    } else {
                        String dataString = data.getDataString();
                        if (dataString != null) {
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
                mFilePathCallback.onReceiveValue(results);
                mFilePathCallback = null;
                break;
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        binding.webView.reload();
    }
}

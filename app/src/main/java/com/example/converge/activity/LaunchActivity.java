package com.example.converge.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.converge.R;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.net.Uri;
import java.io.File;
import android.content.pm.PackageManager;

public class LaunchActivity extends AppCompatActivity {

    //---京东和淘宝的商铺及商品ID
    private String TaoBaoShopId = "131259851";   //--耐凡眼镜店
    private String JDShopId = "1000004123";     //--京东小米官方旗舰店
    private String TaoBaoGoodsId = "525249416835";  //--时尚潮流复古学生...眼镜框
    private String JDGoodsId = "4099139";       //--小米6详情页

    //--1.打开京东或淘宝的店铺
    private String taobaoAppStr_shop = "taobao://shop.m.taobao.com/shop/shop_index.htm?shop_id=" + TaoBaoShopId + "";
    private String taobaoWebStr_shop = "https://shop.m.taobao.com/shop/shop_index.htm?shop_id=" + TaoBaoShopId + "";
    private String jdAppStr_shop = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"jshopMain\",\"shopId\":\"" + JDShopId + "\",\"sourceType\":\"M_sourceFrom\",\"sourceValue\":\"dp\"}";
    private String jdWebStr_shop = "http://shop.m.jd.com/?shopId=" + JDShopId + "";

    //--2.打开京东或淘宝的商品详情页
    private String taobaoAppStr_goods = "taobao://item.taobao.com/item.htm?id=" + TaoBaoGoodsId + "";
    private String taobaoWebStr_goods = "https://item.taobao.com/item.htm?id=" + TaoBaoGoodsId + "";
    private String jdAppStr_goods = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"" + JDGoodsId + "\",\"sourceType\":\"JSHOP_SOURCE_TYPE\",\"sourceValue\":\"JSHOP_SOURCE_VALUE\"}";
    private String jdWebStr_goods = "https://item.m.jd.com/product/" + JDGoodsId + ".html";

    //--3.京东和淘宝的包名
    private String mJDMall = "com.jingdong.app.mall";
    private String mTaoBao = "com.taobao.taobao";

    /**
     * 1. 可以从第三方应用跳转到QQ界面，并可以进入指定的QQ号码的聊天界面（可以是陌生人QQ号）.
     * String url="mqqwpa://im/chat?chat_type=wpa&uin=1642084864";
     * startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
     * //指定的QQ号只需要修改uin后的值即可。
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        initView();
    }

    private void initView() {
        findViewById(R.id.jump_to_jd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---jd
                if (isInstallByread(mJDMall)) {
                    Toast.makeText(LaunchActivity.this, "京东已经安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jdAppStr_shop));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LaunchActivity.this, "京东没有安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jdWebStr_shop));
                    startActivity(intent);
//                    loadApp(mJDMall);
                }
            }
        });

        findViewById(R.id.jump_to_taobao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---taobao
                if (isInstallByread(mTaoBao)) {
                    Toast.makeText(LaunchActivity.this, "淘宝已经安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(taobaoAppStr_shop));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LaunchActivity.this, "淘宝没有安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(taobaoWebStr_shop));
                    startActivity(intent);
//                    loadApp(mTaoBao);
                }
            }
        });

        findViewById(R.id.jump_to_jd_goods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---jd
                if (isInstallByread(mJDMall)) {
                    Toast.makeText(LaunchActivity.this, "京东已经安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jdAppStr_goods));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LaunchActivity.this, "京东没有安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jdWebStr_goods));
                    startActivity(intent);
//                    loadApp(mJDMall);
                }
            }
        });

        findViewById(R.id.jump_to_taobao_goods).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//---taobao
                if (isInstallByread(mTaoBao)) {
                    Toast.makeText(LaunchActivity.this, "淘宝已经安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(taobaoAppStr_goods));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LaunchActivity.this, "淘宝没有安装", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(taobaoWebStr_goods));
                    startActivity(intent);
//                    loadApp(mTaoBao);
                }
            }
        });

    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 启动目标应用
     *
     * @param packageName 目标应用安装后的包名
     */
    private void launchApp(String packageName) {
        PackageManager packageManager = this.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(packageName);
        startActivity(intent);
    }

    /**
     * 下载目标应用
     *
     * @param packageName 目标应用安装后的包名
     */
    private void loadApp(String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);//id后面接包名
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
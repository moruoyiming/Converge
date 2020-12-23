package com.cocos.basewebview;

/**
 * @Desc
 * @Author songxg
 * @Date 2018-11-23 16:00
 */
public class CoreConstant {

    public static final int MAX_NEWS_NUM = 99;

    // 是否需要处理账户退出事件
    private static boolean sShouldHandleLogout = false;

    //是否 打印 retrfit 拦截器log
    public static final boolean OPENLOGINTERCEPTION = true;
    //是否 打印 加密 解密log
    public static final boolean OPENINTERCEPTION = true;

    public static final int TARGET_OFTEN_GAME = 1;
    public static final int SET_OFTEN_GAME = 2;
    //好友操作-1未申请 ,0申请，1同意，2拒绝，3删除
    public static final int FRENDS_STATE_NO_APPLY = -1;
    public static final int FRENDS_STATE_APPLY = 0;
    public static final int FRENDS_STATE_ADD_OK = 1;
    public static final int FRENDS_STATE_ADD_REFUSE = 2;
    public static final int FRENDS_STATE_DELETE = 3;

    //性别，1代表男，2代表女
    public static final String BOY = "m";
    public static final String GIRL = "f";
    public static final String SECRET = "x";

    //    //true，测试vivo的sdk，false，联运的sdk
    public static final boolean IS_VIVO_TEST = false;
    //是否开启检测内存泄漏
    public static final boolean OPEN_LEAKCANARY = false;
    //0 vivo联运sdk  1 帐户sdk
    public static final int SDK_STATUS = 0;

    //是否 开启 gamezip 包秘钥验证
    public static final boolean VERIFY_GAMEZIP = false;

    //聊天历史消息每页拉取条数
    public static final int HISTORY_MESSAGE_PAGE_NUM = 20;

    //好友列表每页数据个数
    public static final int FRIEND_LIST_PAGE_NUM = 20;

    //启动游戏界面REQUESTCODE
    public static final int REQUEST_CODE_NORMAL = 0;

    public static final int REQUEST_CODE_CHAT = 1;
    //单机游戏
    public static final int REQUEST_CODE_SINGLE = 2;

    public static final int REQUEST_CODE_LOCATION_SERVICE = 3;
    /*********************** 游戏加载界面状态 ***********************/
    //游戏加载页面 对战类游戏拉起
    public static final int LOADING_NORML = 0x00;
    //游戏加载页面 聊天拉起
    public static final int LOADING_CHAT = 0x01;
    //游戏加载页面 非对战类游戏拉起
    public static final int LOADING_SINGLE = 0x02;

    /***********************游戏相关**********************/
    public static final String START_STATE = "START_STATE";

    public static final String DOWNLOADURL_ACTION = "downloadUrl";

    public static final String COLUD_GAME_URL = "coludGameUrl";

    public static final String GAMEURL_ACTION = "gameUrl";

    public static final String GAMEID_ACTION = "gameId";

    public static final String ROOMID_ACTION = "roomId";

    public static final String USERID_ACTION = "userId";

    public static final String NICKNAME_ACTION = "nickName";

    public static final String SEX_ACTION = "sex";

    public static final String HEADURL_ACTION = "headUrl";
    //玩家类型 1 玩家 2 人机
    public static final String USERTYPE_ACTION = "userType";

    public static final String ADTYPE_ACTION = "adType";

    public static final int USERTYPE_PLAYER = 1;

    public static final int USERTYPE_ROBOT = 2;

    public static final String ROOMKEY_ACTION = "roomKey";
    // 1 胜利  2 失败 3 平局
    public static final String STATUS_ACTION = "status";
    // 0 默认启动页面    1.从聊天页面拉起
    public static final String WHERE_ACTION = "where";
    // 游戏类型 有服务器 0  无服务器 1
    public static final String GAME_TYPE_ACTION = "gameType";
    // 0默认拉起 匹配   1 拉起匹配 直接匹配游戏
    public static final String MATCH_TYEP = "match_type";
    //
    public static final String MATCH_TEST = "match_test";
    //首页模块类型
    public static final int HOME_TYPE_CP = 11;
    public static final int HOME_TYPE_RECOMMEND = 2;
    public static final int HOME_TYPE_LIST = 3;
    public static double LATITUDE = 0;

    public static double LONGITUDE = 0;
    //游戏胜利
    public static final int GAME_WIN = 1;
    //游戏失败
    public static final int GAME_FAIL = 2;
    //游戏平局
    public static final int GAME_FLAT = 3;
    //换个游戏
    public static final int GAME_CHANGE = 4;
    //对方想换个游戏
    public static final int GAME_WANT_CHANGE = 5;
    //换个对手
    public static final int GAME_CHANGE_PLAYER = 6;
    //再来一局
    public static final int GAME_CONTENIU = 7;
    //对方邀你再来一局
    public static final int GAME_WANT_CONTENIU = 8;
    //对方离开
    public static final int GAME_OPPONENT_LEAVE = 9;
    //重制再来一局
    public static final int RESET_GAME_CONTENIU = 10;
    //重制换个游戏
    public static final int RESET_GAME_CHANGE = 11;
    //结算界面匹配游戏成功
    public static final int MATCH_GAME_SUCCESS = 12;

    //对战游戏胜负 胜利 1  失败 2  平局 3
    public static final int FIGHT_WIN = 1;

    public static final int FIGHT_FAIL = 2;

    public static final int FIGHT_DRAW = 3;

    public static final String SOCKET_DEBUG_URL = "117.121.42.96";
    public static final String SOCKET_BACKUP_URL = "117.121.42.96";

    public static final int SOCKET_PORT = 9999;

    public static final int GAME_AUDIO_CLOSE = 0;

    public static final int GAME_AUDIO_OPEN = 1;
    //标示android设备id
    public static final int DEVICE_TYPE_ANDROID = 1;


    //启动游戏界面REQUESTCODE
    public static final String REQUEST_ACTION = "requestCode";
    //标记是否人机  0表示AI,1表示正常用户
    public static final int USER_AI = 0;
    //ai的自动离线时间,10秒
    public static final int AI_OFF_LINE_TIME = 10000;

    //游戏类型
    //1 单机游戏
    //2 对战游戏
    //3 棋牌游戏
    //4 IO游戏
    //5 竞技游戏
    //6 网络游戏
    //7 runtime游戏
//    public static final int SINGLE_PLAYER_GAME = 1;
//    public static final int MATCH_GAME = 2;
//    public static final int CHESS_GAME = 3;
//    public static final int IO_GAME = 4;
//    public static final int COMPETITIVE_GAME = 5;
//    public static final int ONLINE_GAME = 6;
//    public static final int RUNTIME_GAME = 7;
//    public static final int RUNTIME_BATTLE_GAME = 8;

    public static final String GAME_MODE = "game_mode";
    //gameMode类型 1表示runtime游戏，2表示h5游戏 3 标识外链游戏 4 标识云游戏
    public static final int GAME_MODE_RUNTIME = 1;
    public static final int GAME_MODE_H5 = 2;
    public static final int GAME_MODE_URL = 3;
    public static final int GMAE_MODE_CLOUD_GAME = 4;
    public static final int GMAE_MODE_H5GAME_CENTER = 5;
    //gameType类型 1表示对战游戏，其他表示非对战游戏。
    public static final int GAME_TYPE_BATTLE = 1;
    public static final int GAME_TYPE_UN_BATTLE = 2;
    //loadType类型 0表示文件加载，1标识url加载。
    public static final int GAME_LOAD_TYPE_FILE = 1;//"file"
    public static final int GAME_LOAD_TYPE_URL = 2;//"url"
    public static int RINGER_SIZE = 0;

    public static int TEMP_RINGER_SIZE = 0;

    /*********************** NATIVE&H5交互相关 **********************/
    //JS 库方法名字
    public static final String JS = "js";
    public static final String JS_METHOD = "callNative";
    public static final String JS_FEILD = "cmd";
    public static final String JS_PARAM = "param";
    public static final String JS_METHOD_CB = "GameSDK.nativeCallback";

    //JS FILED CP游戏有服务器
    public static final String JS_INIT = "init";
    public static final String JS_ROOMINFO = "getRoomInfo";
    public static final String JS_QUIT = "quit";
    public static final String JS_FINISH = "finish";
    public static final String JS_SETORIENTATION = "setOrientation";
    public static final String JS_SETAUDIO = "setAudio";
    public static final String JS_SETMIC = "setMic";
    public static final String JS_GETADRELATEDPARAMS="getAdRelatedParams";
    public static final String JS_AD_STATE_ERROR="onAdError";
    public static final String JS_AD_STATE_COMPLETE="onAdComplete";
    public static final String JS_AD_STATE_SKIP="onAdSkipped";
    public static final String JS_AD_STATE_ONLOAD="onAdLoaded";
    //JS FILED CP游戏无服务器
    public static final String JS_GAME_READY = "ready";
    public static final String JS_GAME_BROADCAST = "broadcast";
    public static final String JS_GAME_GAMEOVER = "gameOver";
    //JS FILED CP支付
    public static final String JS_GAME_PAY = "pay";
    //NATIVE FILED CP支付结果回传
    public static final String NATIVE_ON_PAY_CB = "onPay";
    //JS FILED 登录
    public static final String JS_GAME_LOGIN = "login";
    //NATIVE FILED 登录结果回传
    public static final String NATIVE_ON_LOGIN_CB = "onLogin";

    //NATIVE FILED CP游戏有服务器
    public static final String NATIVE_ONINIT = "onInit";
    public static final String NATIVE_ONROOMINFO = "onRoomInfo";
    public static final String NATIVE_ONADPARAMS="onAdParams";
    //NATIVE FILED CP游戏无服务器
    public static final String NATIVE_ONREADY = "onReady";
    public static final String NATIVE_ONSTART = "onStart";
    public static final String NATIVE_ONMESSAGE = "onMessage";
    public static final String NATIVE_ONFINISH = "onFinish";
    public static final String NATIVE_ONAUDIO = "onAudio";
    public static final String NATIVE_ONRESUME = "onResume";
    public static final String NATIVE_ONPAUSE = "onPause";
    //NATIVE FILED CP游戏loading显示与关闭
    public static final String NATIVE_LOADPROGRESS = "setLoadProgress";
    public static final String NATIVE_HIDELOAD = "hideLoadProgress";
    //NATIVE FILED Banner广告
    public static final String JS_AD_BANNER_CREATE = "ad_banner_create";
    public static final String JS_AD_BANNER_SHOW = "ad_banner_show";
    public static final String JS_AD_BANNER_HIDE = "ad_banner_hide";
    public static final String JS_AD_BANNER_DESTROY = "ad_banner_destroy";
    public static final String NATIVE_AD_BANNER_ONLOAD = "ad_banner_onLoad";
    public static final String NATIVE_AD_BANNER_ONERROR = "ad_banner_onError";
    public static final String NATIVE_AD_BANNER_ONRESIZE = "ad_banner_onResize";
    //插屏广告
    public static final String JS_AD_INTERSTITIAL_CREATE = "ad_interstitial_create";
    public static final String JS_AD_INTERSTITIAL_SHOW = "ad_interstitial_show";
    public static final String JS_AD_INTERSTITIAL_HIDE = "ad_interstitial_hide";
    public static final String JS_AD_INTERSTITIAL_DESTROY = "ad_interstitial_destroy";
    public static final String NATIVE_AD_INTERSTITIAL_ONLOAD = "ad_interstitial_onLoad";
    public static final String NATIVE_AD_INTERSTITIAL_ONERROR = "ad_interstitial_onError";
    public static final String NATIVE_AD_INTERSTITIAL_ONRESIZE = "ad_interstitial_onResize";
    //激励视频广告
    public static final String JS_AD_REWARDEDVIDEO_CREATE = "ad_rewardedVideo_create";
    public static final String JS_AD_REWARDEDVIDEO_SHOW = "ad_rewardedVideo_show";
    public static final String JS_AD_REWARDEDVIDEO_HIDE = "ad_rewardedVideo_hide";
    public static final String JS_AD_REWARDEDVIDEO_DESTROY = "ad_rewardedVideo_destroy";
    public static final String NATIVE_AD_REWARDEDVIDEO_ONLOAD = "ad_rewardedVideo_onLoad";
    public static final String NATIVE_AD_REWARDEDVIDEO_ONERROR = "ad_rewardedVideo_onError";
    public static final String NATIVE_AD_REWARDEDVIDEO_ONCLOSE = "ad_rewardedVideo_onClose";

    public static final String AD_ONSHOW = "ad_onShow";

    public static final int AD_BANNER = 1;

    public static final int AD_INTERSTITIAL = 2;

    public static final int AD_VIDEO = 3;

    public static final int AD_NATIVE= 4;

    //游戏版本为1，此处是为了标识版本更新之后，游戏为2，显示gameloading页面，为1的时候，游戏loading页面是cp自己的。
    public static final int GAME_VERSION_ONE = 1;

    public static boolean shouldHandleLogout() {
        return sShouldHandleLogout;
    }

    public static void setHandleLogoutState(boolean state) {
        sShouldHandleLogout = state;
    }

//    //vivo登录
//    public static final String LOGIN_VIVO = "vivo_login";
//    //游戏中心登录
//    public static final String LOGIN_GAME_CENTER = "game_center_login";
//
//    //获取当前登录方式
//    public static String getLoginMode(){
//        return LOGIN_GAME_CENTER;
//    }

    public static final String CHANNEL_QIEZI = "qiezi";
    public static final String CHANNEL_NESTIA = "nestia";
    public static final String CHANNEL_HUPO = "hupo";
    public static final String CHANNEL_BUZZBREAK = "buzzbreak";
    public static final String CHANNEL_ONEPLUS = "oneplus";
    //礼包类别 唯一礼包
    public static final String GIFT_CATEGORY_UNIQUE = "unique";
    //礼包类别 通用礼包
    public static final String GIFT_CATEGORY_COMMON = "common";
    //领取状态 0未领取，1已领取
    public static final int GIFT_GET_STATUS_NOT = 0;
    public static final int GIFT_GET_STATUS_YES = 1;
    //标记是否新用户
    public static final int NEW_REGISTER = 1;
    public static final int OLD_USERD = 0;
    //请求时长限制
    public static final int REQUEST_DURATION = 200;

    //版本升级级别 O无需下载，1可选升级，2，强制升级
    public static final int VERSION_NUM_LEVEL_NO = 0;
    public static final int VERSION_NUM_LEVEL_OPTIONAL = 1;
    public static final int VERSION_NUM_LEVEL_FORCE = 2;

    //版本升级类型 1下载链接，2页面链接
    public static final int VERSION_NUM_TYPE_URL = 1;
    public static final int VERSION_NUM_TYPE_LINK = 2;

    //针对茄子统计
    public static final String USER_ID = "userId";
    public static final String LOGIN_START_TIME = "loginStart";
    public static final String LOGIN_END_TIME = "loginEnd";
    public static final String GAME_ID = "gameId";
    public static final String DOWNLOAD_GAME_TIME = "downloadGameTime";
    public static final String START_GAME_TIME = "startGame";
    public static final String START_LOAD_GAME_TIME = "startLoadGame";
    public static final String LOAD_GAME_END_TIME = "loadGameEnd";
    public static final String GAME_TIME = "gameTime";


}

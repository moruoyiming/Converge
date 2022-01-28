package com.cocos.base.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AssemblyUtils {

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath
     * 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    static File file;
    static boolean flag;

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                } // 删除子目录
                else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                }
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        flag = false;
        file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 校验字符是否包含中文
     *
     * @param checkString 要检测的字符
     * @return 否包含中文
     */
    public static boolean isContainChinese(String checkString) {
        String regEx = "[\u4e00-\u9fff]";
        Pattern pat = Pattern.compile(regEx);
        Matcher matcher = pat.matcher(checkString);
        return matcher.find();
    }

    /**
     * 获取错误码的返回信息
     *
     * @param errorList
     * @return
     */
    public static String GetErrorString(ArrayList<Integer> errorList) {
        String str = "";
        for (int i = 0; i < errorList.size(); i++) {
            if (errorList.get(i) == -1001) {
                str = "跟别人重复啦，用户名得是独一无二的才行";
                break;
            } else if (errorList.get(i) == -1002) {
                str = str + "连接服务器出错";
                break;
            } else if (errorList.get(i) == -1003) {
                str = str + "请先登录";
                break;
            } else if (errorList.get(i) == -1004) {
                str = str + "程序出现参数错误";
                break;
            } else if (errorList.get(i) == -1005) {
                str = str + "内容中有敏感词";
                break;
            } else if (errorList.get(i) == -1006) {
                str = str + "用户名不合法";
                break;
            } else if (errorList.get(i) == -1007) {
                str = str + "密码不一致";
                break;
            } else if (errorList.get(i) == -1008) {
                str = str + "密码不正确或者格式错误(正常为6-16个字符）";
                break;
            } else if (errorList.get(i) == -1009) {
                str = str + "邮箱名不合法";
                break;
            } else if (errorList.get(i) == -1010) {
                str = str + "邮箱已经被注册";
                break;
            } else if (errorList.get(i) == -1011) {
                str = str + "用户名必填";
                break;
            } else if (errorList.get(i) == -1012) {
                str = str + "邮箱必填";
                break;
            } else if (errorList.get(i) == -1013) {
                str = str + "密码必填";
                break;
            } else if (errorList.get(i) == -1014) {
                str = str + "激活码已过期";
                break;
            } else if (errorList.get(i) == -1015) {
                str = str + "用户不存在";
                break;
            } else if (errorList.get(i) == -1016) {
                str = str + "密码错误.";
                break;
            } else if (errorList.get(i) == -1017) {
                str = str + "用户已激活";
                break;
            } else if (errorList.get(i) == -1018) {
                str = str + "插入数据库失败";
                break;
            } else if (errorList.get(i) == -1019) {
                str = str + "SESSION已经过期";
                break;
            } else if (errorList.get(i) == -1020) {
                str = str + "更新数据库失败";
                break;
            } else if (errorList.get(i) == -1021) {
                str = str + "题目不存在";
                break;
            } else if (errorList.get(i) == -1022) {
                str = str + "结果为空";
                break;
            } else if (errorList.get(i) == -1023) {
                str = str + "帐号已经绑定";
                break;
            } else if (errorList.get(i) == -1024) {
                str = str + "连接第三方网站登录失败";
                break;
            } else if (errorList.get(i) == -1025) {
                str = str + "不是第三方网站登录的临时用户";
                break;
            } else if (errorList.get(i) == -1026) {
                str = str + "题目清单为公开";
                break;
            } else if (errorList.get(i) == -1027) {
                str = str + "手机号不合法";
                break;
            } else if (errorList.get(i) == -1028) {
                str = str + "数据不一致";
                break;
            } else if (errorList.get(i) == -1029) {
                str = str + "创建socket失败";
                break;
            } else if (errorList.get(i) == -1030) {
                str = str + "连接socket失败";
                break;
            } else if (errorList.get(i) == -1031) {
                str = str + "socket发送数据失败";
                break;
            } else if (errorList.get(i) == -1032) {
                str = str + "socket接受数据失败";
                break;
            } else if (errorList.get(i) == -1034) {
                str = str + "用户帐号已经过期";
                break;
            } else if (errorList.get(i) == -1035) {
                str = str + "当前用户没有操作权限";
                break;
            } else if (errorList.get(i) == -1036) {
                str = str + "激活链接错误";
                break;
            } else if (errorList.get(i) == -1037) {
                str = str + "用户名长度大于16";
                break;
            } else if (errorList.get(i) == -1038) {
                str = str + "用户名长度小于2";
                break;
            } else if (errorList.get(i) == -1039) {
                str = str + "注册时输入确认密码为空";
                break;
            } else if (errorList.get(i) == -1040) {
                str = str + "邮编不合法";
                break;
            } else if (errorList.get(i) == -1041) {
                str = str + "用户名或者密码错误";
                break;
            } else if (errorList.get(i) == -1042) {
                str = str + "用户名不可修改";
                break;
            } else if (errorList.get(i) == -1043) {
                str = str + "邮箱不可修改";
                break;
            } else if (errorList.get(i) == -1044) {
                str = str + "用户名不能以数字开头";
                break;
            } else if (errorList.get(i) == -1045) {
                str = str + "手机号已经被注册";
                break;
            } else if (errorList.get(i) == -1046) {
                str = str + "手机验证码已过期";
                break;
            } else if (errorList.get(i) == -1047) {
                str = str + "手机验证码已过期";
                break;
            } else if (errorList.get(i) == -1048) {
                str = str + "用户名不能以数字开头";
                break;
            } else if (errorList.get(i) == -1049) {
                str = str + "验证码输入错误";
                break;
            } else if (errorList.get(i) == -1050) {
                str = str + "输入长度超过限制";
                break;
            } else if (errorList.get(i) == -1051) {
                str = str + "邮箱或手机号码错误";
                break;
            } else if (errorList.get(i) == -1060) {
                str = str + "用户帐号余额不足,请充值";
                break;
            } else if (errorList.get(i) == -1061) {
                str = str + "支付失败/订单号不存在";
                break;
            } else if (errorList.get(i) == -1062) {
                str = str + "订单已经完成";
                break;
            } else if (errorList.get(i) == -1063) {
                str = str + "对象已经购买";
                break;
            } else if (errorList.get(i) == -1064) {
                str = str + "评论分数不能为空";
                break;
            } else if (errorList.get(i) == -1065) {
                str = str + "评论内容不能为空";
                break;
            } else if (errorList.get(i) == -1066) {
                str = str + "题目不存在父题目";
                break;
            } else if (errorList.get(i) == -1067) {
                str = str + "题目不存在解析";
                break;
            } else if (errorList.get(i) == -1068) {
                str = str + "定制url不能重复";
                break;
            } else if (errorList.get(i) == -1069) {
                str = str + "用户对课程的评价超出限定次数";
                break;
            } else if (errorList.get(i) == -1070) {
                str = str + "优惠码不存在";
                break;
            } else if (errorList.get(i) == -1071) {
                str = str + "优惠码已经使用";
                break;
            } else if (errorList.get(i) == -1072) {
                str = str + "优惠码已经过期";
                break;
            } else if (errorList.get(i) == -1073) {
                str = str + "该优惠码不可用";
                break;
            } else if (errorList.get(i) == -1074) {
                str = str + "超过小组最大人数限制";
                break;
            } else if (errorList.get(i) == -1075) {
                str = str + "该帐号已经绑定第三方的别的帐号";
                break;
            } else if (errorList.get(i) == -5001) {
                str = str + "未通过签名验证";
                break;
            } else if (errorList.get(i) == -9999) {
                str = str + "你的操作过于频繁，请稍后再试~";
                break;
            } else {
                str = str + "获取信息失败.";
                break;
            }
        }
        if ("".equals(str)) {
            str = "获取信息失败!";
        }
        return str;

    }

    /**
     * 校验微信号是否合法
     * 1）微信帐号支持6-20个字母、数字、下划线和减号,必须以字母开头
     * 2）支持 11 位数的手机号
     * 3）初期微信注册，以 wxid 开头的原始 ID 不支持；
     *
     * @param s
     * @return
     */
    public static boolean checkWeiXinNumber(String s) {
        boolean isLegal = false;
        if (TextUtils.isEmpty(s) || s.length() < 6 || s.length() > 20) {
            return false;
        }
        isLegal = matchingTextForLetter(s.subSequence(0, 1).toString());//首个输入的是否为字母
        if (isLegal) {
            //字母开头
            if (s.startsWith("wxid_")) {
                return false;
            } else {
                char[] chars = s.toCharArray();
                for (char c : chars) {
                    String str = String.valueOf(c);
                    if (matchingTextForLetter(str) || matchingTextForNumber(str) || TextUtils.equals("-", str) || TextUtils.equals("_", str)) {
                        //字母数字下划线减号
                        isLegal = true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            //手机号
            isLegal = isMobileNumber(s);
        }
        return isLegal;
    }

    /**
     * 验证 是否为 字母
     *
     * @param cs
     * @return
     */
    public static boolean matchingTextForLetter(String cs) {
        Pattern compile = Pattern.compile("[a-zA-Z]");
        Matcher matcher = compile.matcher(cs);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证 是否为 数字
     *
     * @param cs
     * @return
     */
    public static boolean matchingTextForNumber(String cs) {
        Pattern compile = Pattern.compile("[0-9]");
        Matcher matcher = compile.matcher(cs);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "[1]\\d{10}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
    /**
     * 获取手机屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.x;
    }

    /**
     * 获取手机屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        return size.y;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
        return Math.round(px);
    }


    /**
     * 获取应用的版本号
     *
     * @param context 上下文
     * @return 应用的版本号
     */
    private static String version = "";
    public static String getAppVersionName(Context context) {
        if(version.isEmpty()) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
                packageInfo.getClass().getName();
                version = packageInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                version = "1.0.0";
            }
            return version;
        }else{
            return version;
        }

    }
}

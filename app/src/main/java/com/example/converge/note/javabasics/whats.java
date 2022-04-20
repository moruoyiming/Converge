package com.example.converge.note.javabasics;

import android.text.TextUtils;

/**
 * @Date: 2022/4/15
 * @Time: 10:18
 * @Author: Jian
 */
public class whats {
    public static void main(String[] args) {
        String url;
        String t = "https://m.kaoyan.com/seniorh5/apply/step2?kyb_login_session=tal173fygniBx0okXiYZvOl2ErdMWGDtiRPI1Ot71sVHRDTtbMHUVsjYr5MfIN7JERM5ZP2qyu4LZE-M8PETUI_5xdEeaYJnssxf1lS9kSicgTCkeClt-MEg9ofVqyTtkuxLtDnyLhQHmVc_VYXvznNImVg2TpKAvX2t24P1dU6Pwfyc0epit7jpfW4id31fI0b0YKY1_VRpXDOKvH9pI_7-3Ho-nhAqq6nL0es37SAoiQh649g&sd=sdfa&url=http://sdsdsdfasd?fd=dddd&sdf=2";
        url = getUrlFilterToken(t);
        System.out.println(t);
        System.out.println(url);
        String t2 = "https://m.kaoyan.com/seniorh5/apply/step2?kyb_login_session=qfI0b0YKY&sd=sdfa&2232=232223";
        url = getUrlFilterToken(t2);
        System.out.println(t2);
        System.out.println(url);
        String t3 = "https://m.kaoyan.com/seniorh5/ky_schoolmajor?url=http://m.kaoyan.com?type=1";
        url = getUrlFilterToken(t3);
        System.out.println(t3);
        System.out.println(url);

        String t4 = "https://m.kaoyan.com/seniorh5/apply/step2";
        url = getUrlFilterToken(t4);
        System.out.println(t4);
        System.out.println(url);
        String t5 = "https://m.kaoyan.com/seniorh5/apply/step2?kyb_login_session=tal173fygn";
        url = getUrlFilterToken(t5);
        System.out.println(t5);
        System.out.println(url);
        String t6 = "https://m.kaoyan.com/seniorh5/apply/step2?url=http://m.kaoyan.com?type=1&2323=32";
        url = getUrlFilterToken(t6);
        System.out.println(t6);
        System.out.println(url);
    }

    /**
     * 从url过滤token字段
     *
     * @param value
     * @return
     */
    public static String getUrlFilterToken(String value) {
        String url = value;
        if (null == value || "".equals(value)) {
            return url;
        }
        try {
            if (value.contains("?")) {//包含参数的url https://m.kaoyan.com/seniorh5/apply/step2?kyb_login_session=tal173fygn
                int startIndex = value.indexOf("?");//获取一个？地址
                String tempUrl = value.substring(0, startIndex);//获取域名地址
                String params = value.substring(startIndex, value.length());//获取域名地址后的 ?kyb_login_session=tal173fygn
                StringBuilder sb = new StringBuilder();
                sb.append(tempUrl);
                if (null != params && !"".equals(params)) {//判断是否包含数据
                    if (params.contains("kyb_login_session")) {//包含数据 ，并包含token
                        if (params.contains("&")) {//多个参数
                            String[] tempArray = params.split("&");
                            if (tempArray.length > 0) {
                                sb.append("?");
                                for (int i = 0; i < tempArray.length; i++) {
                                    if (!tempArray[i].contains("kyb_login_session")) {
                                        sb.append(tempArray[i]);
                                        if (i != tempArray.length - 1) {
                                            sb.append("&");
                                        }
                                    }
                                }
                            }
                        }
                    } else {//包含数据，不包含token ，直接拼接返回
                        sb.append(params);
                    }
                }
                url = sb.toString();
            }//不包含参数的url 直接返回
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}

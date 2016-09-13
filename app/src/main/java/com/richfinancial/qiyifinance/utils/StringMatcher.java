package com.richfinancial.qiyifinance.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringMatcher
 * 项目名称:  QYManager
 * 类描述:    封装了项目中使用到的正则匹配
 * 创建人:    xujiayin
 * 创建时间:  2016-04-03
 * 修改人:    luqb
 * 修改时间:  2016-04-03
 * 修改备注:  封装了项目中使用到的正则匹配
 * 版本:      v1.0
 */
public class StringMatcher {
    /**
     * 判断是否是手机号码
     *
     * @param mobiles 输入数字
     * @return 是／否
     */
    public static boolean isMobileNO(String mobiles) {
        String strPattern = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|147|(17[0-9]))\\d{8}$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 判断是否是邮箱地址
     *
     * @param strEmail 输入邮箱地址
     * @return 是／否
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 判断是否是URL
     *
     * @param strUrl 判断格式是否为URL
     * @return 是／否
     */
    public static boolean isUrl(String strUrl) {
        Pattern p = Pattern.compile("((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strUrl);
        return m.matches();
    }

    /**
     *
     *
     * 判断是否是合法的身份证号码
     * @param cardNum
     * @return true 合法 / false 非法
     */
    public static boolean isIdCard(String cardNum) {
        //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        //通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(cardNum);
        return idNumMatcher.matches();
    }

    /**
     * 去除字符串中的空格和回车
     * @param text
     * @return
     */
    public static String replaceText(String text){
        if(GsonUtil.isStringNull(text)){
           return "";
        }
        text = text.replaceAll("\\s", "");
        text = text.replaceAll("\\n", "");
        return text;
    }

    /**
     * 判断输入的密码是不是6-20位的英文字母和数字组成
     * @param strPwd 输入的密码
     * @return true/false
     */
    public static boolean isNumberAlphabetic(String strPwd) {
        String strPattern = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strPwd);
        return m.matches();
    }

    /**
     * 去掉“市”
     */
    public static String removeCityChar(String str) {
        return str.replaceAll("市", "");
    }
}

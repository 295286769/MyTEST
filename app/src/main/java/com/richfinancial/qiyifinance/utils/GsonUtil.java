package com.richfinancial.qiyifinance.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * GsonUtil
 * 项目名称:  QYManager
 * 类描述:    用于解析Json
 * 创建人:    luqb
 * 创建时间:  2016-03-09
 * 修改人:    luqb
 * 修改时间:  2016-03-09
 * 修改备注:  解析Json
 * 版本:      v1.0
 */
public class GsonUtil {

    public static final String strDefaultDateFormat = "yyyy/MM/dd HH:mm:ss";
    public static boolean isPrintException = true;

    /**
     * 提供指定日期格式的Gson对象
     *
     * @param dateFormat
     * @return
     */
    public static Gson getGson(String dateFormat) {
        return new GsonBuilder().setDateFormat(dateFormat).create();
    }

    /**
     * 提供默认日期解析格式的Gson对象
     *
     * @return
     */
    public static Gson getGson() {
        return new GsonBuilder().setDateFormat(strDefaultDateFormat).create();
    }

    /**
     * json转指定对象
     *
     * @param json
     * @param cls
     * @return T
     */
    public static <T> T getModle(String json, Class<T> cls) {
        return getGson().fromJson(json, cls);
    }

    /**
     * json转指定对象(key为json中为列表的字段名)
     *
     * @param json
     * @param cls
     * @return T
     */
    public static <T> T getModle(String json, Class<T> cls, String key) {
        json = getString(json, key);
        return getGson().fromJson(json, cls);
    }

    /**
     * json转指定对象列表
     *
     * @param json
     * @return List<T>
     */
    public static <T> List<T> getListModle(String json) {
        List<T> list = new ArrayList<T>();
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        list = getGson().fromJson(json, type);
        return list;
    }

    /**
     * json转指定对象列表(key为json中为列表的字段名)
     *
     * @param json
     * @param key
     * @return List<T>
     */
    public static <T> List<T> getListModle(String json, String key) {
        json = getString(json, key);
        List<T> list = new ArrayList<T>();
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        list = getGson().fromJson(json, type);
        return list;
    }

    /**
     * get String from jsonObject
     *
     * @param jsonObject
     * @param key
     * @return <ul>
     * <li>if jsonObject is null, return null</li>
     * <li>if key is null or empty, return null</li>
     * <li>if {@link JSONObject#getString(String)} exception, return
     * null</li>
     * <li>return {@link JSONObject#getString(String)}</li>
     * </ul>
     */
    public static String getString(String jsonObject, String key) {
        return getString(jsonObject, key, null);
    }

    /**
     * get String from jsonObject
     *
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return <ul>
     * <li>if jsonObject is null, return defaultValue</li>
     * <li>if key is null or empty, return defaultValue</li>
     * <li>if {@link JSONObject#getString(String)} exception, return
     * defaultValue</li>
     * <li>return {@link JSONObject#getString(String)}</li>
     * </ul>
     */
    public static String getString(String jsonObject, String key,
                                   String defaultValue) {
        if (jsonObject == null || null == key || "".equals(key)) {
            return defaultValue;
        }

        try {
            return new JSONObject(jsonObject).getString(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * 对象转Json格式字符串
     *
     * @param obj
     * @return
     */
    public static String getStringForObject(Object obj) {
        String json = getGson().toJson(obj);
        if(null != json && !"".equals(json) && 0 != json.length()){
            return json;
        }
        return "";
    }

    /**
     * 检查字符串是否为null，如果为null，返回 defText
     * @param text 要检查的字符串
     * @param defText 默认文字
     * @return
     */
    public static String getContext(String text, String defText){
        if(null == text || "".equals(text) || 0 == text.length()){
            return defText;
        }
        return text;
    }
    /**
     * 检查字符串是否为null，如果为null，返回 “-”
     * @param text
     * @return true null , false not null
     */
    public static boolean isStringNull(String text){
        if(null == text || "".equals(text) || 0 == text.length()){
            return true;
        }
        return false;
    }
}

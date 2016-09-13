package com.richfinancial.qiyifinance.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * ActivityCollector
 * 项目名称:  QYManager
 * 包:        com.lc.qymanager.utils
 * 类名称:    ActivityCollector
 * 类描述:    管理activity
 * 创建人:    android
 * 创建时间:  2016-05-23
 * 修改人:    android
 * 修改时间:  2016-05-23
 * 修改备注:  管理activity的桟
 * 版本:      v1.0
 */
public class ActivityCollector {
    public static Stack<Activity> activityStackAry = new Stack<Activity>();

    /**
     * 添加Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityStackAry.add(activity);
    }

    /**
     * 删除指定的Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityStackAry.remove(activity);
    }

    /**
     * 删除全部Activity
     */
    public static void finishAll() {
        while (!activityStackAry.isEmpty()) {
            Activity activity = activityStackAry.pop();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * finishOther()循环关闭除主页以外的所有activity
     */
    public static void finishOther() {
        //判断activityStackAry中的数量
        while (activityStackAry.size() > 1) {
            // 移除activityStackAry最顶端的对象
            Activity activity = activityStackAry.pop();
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * peek()方法 查看栈顶对象而不移除它
     */
    public static Activity peek() {
        return activityStackAry.peek();
    }
}

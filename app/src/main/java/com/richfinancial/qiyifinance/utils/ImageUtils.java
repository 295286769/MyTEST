package com.richfinancial.qiyifinance.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ImageUtils {
    /**
     * ImageUtils 这个可以只做简单的初始化,此方法建议在
     * Application中进行初始化
     * 
     * @param context
     */
    public static void initConfiguration(Context context) {
        ImageLoaderConfiguration.Builder configuration = new ImageLoaderConfiguration.Builder(context);
        //设置https图片
        //configuration.imageDownloader(new HttpsImageDownloader(context));
        ImageLoader.getInstance().init(configuration.build());
    }

    public static void showPic(ImageView imv , String url ,DisplayImageOptions options){
        ImageLoader.getInstance().displayImage(url, imv, options);
    }

    /**
     * 初始化DisplayImageOptions
     * @return
     */
    public static DisplayImageOptions initOptions(int draRes) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // 设置下载的图片是否缓存在内存中
                //.cacheInMemory()
                //.showStubImage(R.drawable.camera_loading)
                .showStubImage(draRes)
                // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisc()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                // 设置图片的解码类型//
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }


    /**
     * 初始化DisplayImageOptions
     * @return
     */
    public static DisplayImageOptions roundOptions(int draRes) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // 设置下载的图片是否缓存在内存中
                //.cacheInMemory()
                //.showStubImage(R.drawable.camera_loading)
                .showStubImage(draRes)
                // 设置下载的图片是否缓存在SD卡中
                .cacheOnDisc()
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)

                .displayer(new CircleBitmapDisplayer())
                // 设置图片的解码类型//
                .bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        return options;
    }

}
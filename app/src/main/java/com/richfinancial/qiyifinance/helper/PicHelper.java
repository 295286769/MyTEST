package com.richfinancial.qiyifinance.helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.richfinancial.qiyiplus.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 封装了相机or相册选取图片
 * Created by xujiayin on 16/3/23.
 */

/**
 * 使用说明：
 1.调用choosePic函数
 2.实现调用者的onActivityResult，并处理_MSG_CODE_CHOOSE_PIC_FROM_LOCAL_；_MSG_CODE_CAMERA_PHOTO_两个消息
 3.在并处理_MSG_CODE_CHOOSE_PIC_FROM_LOCAL_的处理中，调用getLocalChoosePicName获得选取相册中的文件名
 4.调用progressCutPic处理裁切图片，需要在onActivityResult中处理_MSG_CODE_CUT_PIC_消息
 5.调用compressPic压缩图片质量
 */
public class PicHelper /*implements ActivityCompat.OnRequestPermissionsResultCallback*/ {

    //for android 6.0
    final public static int REQUEST_CODE_ASK_CAMERA = 123;

    final public static int REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE = 456;

    private static final int REQUEST_EXTERNAL_STORAGE = 789;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };

    public static final int _MSG_CODE_CHOOSE_PIC_FROM_LOCAL_= 0x3001;   //消息ID：从相册选取图片

    public static final int _MSG_CODE_CAMERA_PHOTO_ = 0x3002;           //消息ID：从相机中拍摄

    public static final int _MSG_CODE_CUT_PIC_ = 0x3003;                //消息ID：裁剪选取照片

    public static final String s_strTempPath = Environment.getExternalStorageDirectory() + "/richibank/demo/";//获得可读写临时路径


    /**
     * 弹出选择对话框 并从相机选取 或 从本地选取 for android 6.0
     * @param atyCaller 调用的Activity
     * @param strPicSaveName 拍照后生成的新图片路径
     */
    public static void verifyChoosePicPermissions(final Activity atyCaller, final String strPicSaveName){
        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(atyCaller , Manifest.permission.CAMERA)){
            ActivityCompat.requestPermissions( atyCaller ,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_ASK_CAMERA);
        }else{
            PicHelper.choosePic(atyCaller , strPicSaveName);
        }
    }

    /**
     * 裁剪图片 兼容android 6.0及以下版本
     * @param atyCaller 调用的Activity
     * @param data  调用的Activity.onActivityResult返回的Intent
     * @param m_strNewFilePath  裁剪后生成新图片名称
     * @param w  设置图片宽度
     * @param h  设置图片高度
     */
    public static void verifyCutPictureIntentPermissions(final Activity atyCaller ,Intent data , String m_strNewFilePath , int w , int h){
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(atyCaller , Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(atyCaller , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE);
        } else {
            String strOldPicturePath = PicHelper.getLocalChoosePicName(atyCaller , data); // 获取图片路径
            PicHelper.progressCutPic(atyCaller, strOldPicturePath, m_strNewFilePath, w , h); // 裁剪
        }
    }

    /**
     * 裁剪图片 兼容android 6.0及以下版本
     * @param atyCaller 调用的Activity
     * @param m_strOldFilePath  原图片路径
     * @param m_strNewFilePath  裁剪后生成新图片名称
     * @param w  设置图片宽度
     * @param h  设置图片高度
     */
    // TODO: 这里是照相回调后继续裁剪功能（因：照相裁剪回调intent为null所以改为原来的方法，获取权限后再进行裁剪）
    public static void verifyCutPictureUrlPermissions(final Activity atyCaller ,String m_strOldFilePath , String m_strNewFilePath , int w , int h){
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(atyCaller , Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(atyCaller , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_READ_EXTERNAL_STORAGE);
        } else {
            PicHelper.progressCutPic( atyCaller , m_strOldFilePath , m_strNewFilePath, w , h); // 裁剪
        }
    }


    /**
     * 压缩图片 兼容android 6.0及以下版本
     * @param atyCaller 调用的Activity
     * @param path  源文件名称
     * @param quality  图片压缩质量
     */
    public static void verifyCompressPermissions(Activity atyCaller , String path , int quality) {
        // Check if we have write permission
        if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(atyCaller, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            ActivityCompat.requestPermissions(atyCaller, PERMISSIONS_STORAGE,  REQUEST_EXTERNAL_STORAGE);
        }else{
            //接下来继续的裁剪方法
            PicHelper.compressPic(atyCaller, path, quality);
        }
    }


    /**
     * 弹出选择对话框 并从相机选取 或 从本地选取
     * @param atyCaller 调用的Activity
     * @param strPicSaveName
     */
    public static void choosePic(final Activity atyCaller, final String strPicSaveName) {

        final String[] arrayType = new String[] { "相册选取", "立即拍照" };
        //弹出选择框
        Dialog alertDialog = new AlertDialog.Builder(atyCaller)
                .setTitle(R.string.str_text_dialog_prompt)       //设置标题
                .setItems(arrayType, new DialogInterface.OnClickListener() {//选项按钮回调
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            // 打开手机中的相册
                            Intent innerIntent = new Intent(
                                    Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
                            innerIntent.setType("image/*");
                            Intent wrapperIntent = Intent.createChooser(
                                    innerIntent, "Select Pic");
                            //启动相册选取图片
                            atyCaller.startActivityForResult(wrapperIntent,_MSG_CODE_CHOOSE_PIC_FROM_LOCAL_);
                        }
                        if (which == 1) {
                            //从相机拍照
                            Intent starCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            String mPicPath = s_strTempPath + strPicSaveName;//照片存储路径
                            File cameraFile = new File(mPicPath);
                            starCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
                            atyCaller.startActivityForResult(starCameraIntent,_MSG_CODE_CAMERA_PHOTO_);
                        }
                    }
                }).setNegativeButton(R.string.str_text_dialog_cancel, new DialogInterface.OnClickListener() {//设定取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
    }

    /**
     * 弹出选择对话框 并从相机选取 或 从本地选取
     * @param atyCaller 调用的Fragment
     * @param strPicSaveName
     */
    public static void choosePicForFragment(final Fragment atyCaller, final String strPicSaveName) {
        //TODO 这里的文字应保存到xml中
        final String[] arrayType = new String[] { "相册选取", "立即拍照" };
        //弹出选择框
        Dialog alertDialog = new AlertDialog.Builder(atyCaller.getActivity())
                .setTitle(R.string.str_text_dialog_prompt)       //设置标题
                .setItems(arrayType, new DialogInterface.OnClickListener() {//选项按钮回调

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // 打开手机中的相册
                            Intent innerIntent = new Intent(
                                    Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
                            innerIntent.setType("image/*");
                            Intent wrapperIntent = Intent.createChooser(
                                    innerIntent, "Select Pic");
                            //启动相册选取图片
                            atyCaller.startActivityForResult(wrapperIntent,_MSG_CODE_CHOOSE_PIC_FROM_LOCAL_);
                        }
                        if (which == 1) {
                            //从相机拍照
                            Intent starCameraIntent = new Intent(
                                    MediaStore.ACTION_IMAGE_CAPTURE);

                            starCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(s_strTempPath,
                                            strPicSaveName)));
                            atyCaller.startActivityForResult(starCameraIntent,_MSG_CODE_CAMERA_PHOTO_);
                        }
                    }
                }).setNegativeButton(R.string.str_text_dialog_cancel, new DialogInterface.OnClickListener() {//设定取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
    }

    /**
     * 仅供在onActivityResult中调用，获得相机选取的文件名
     * @param atyCaller 调用者
     * @param data onActivityResult的第三个参数
     * @return 选取的文件名
     */
    public static String getLocalChoosePicName(final Activity atyCaller,Intent data){
        try {
            String strResult = "";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) // 高版本Androd
            {
                strResult = getPath(atyCaller.getApplicationContext(), data.getData()); // 这是文件路径（通过函数计算得到）
                if(strResult==null)
                {
                    // 获取选中图片的路径
                    Cursor cursor = atyCaller.getContentResolver().query(data.getData(), null, null, null, null);
                    if (cursor != null && cursor.moveToFirst())
                    {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                        strResult = cursor.getString(column_index); // 这是文件路径
                    }
                    cursor.close();
                }
                return strResult;
            }
            else // 低版本Android
            {
                // 获取选中图片的路径
                Cursor cursor = atyCaller.getContentResolver().query(data.getData(), null, null, null, null);
                if (cursor != null && cursor.moveToFirst())
                {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                    strResult = cursor.getString(column_index); // 这是文件路径
                }

                cursor.close();
                return strResult;
            }
        } catch (Exception e) {
            Toast.makeText(atyCaller, "GetLocalPic Error!", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    /**
     * 执行裁剪图片
     * @param atyCaller 调用的Activity
     * @param strOldPicName 原文件名
     * @param strNewPicName 裁剪后保存文件名
     * @param aspectX 裁剪横向像素
     * @param aspectY 裁剪竖向像素
     * @return 裁剪后的临时路径
     */
    public static String progressCutPic(Activity atyCaller,String strOldPicName,String strNewPicName, int aspectX, int aspectY)
    {
        //设置源文件路径
        File oldPicture = new File(strOldPicName);
        Uri oldUriPic = Uri.fromFile(oldPicture);
        // 设置存储路径
        File newPicture = new File(strNewPicName);
        Uri newUriPic = Uri.fromFile(newPicture);
        //调用系统裁剪工具
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(oldUriPic, "image/*");//打开旧图片路径
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);

        // outputX outputY 是裁剪图片宽高，这里先不裁剪
        intent.putExtra("outputX", aspectX);
        intent.putExtra("outputY", aspectY);

        // 这个设置true，才会返回数据, 但是如果数据过大会发生保存不上的问题，这里设置成false,利用url找到
        intent.putExtra("return-data", false);
        // 防止黑边处理
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, newUriPic);//输出新文件路径url
        atyCaller.startActivityForResult(intent,_MSG_CODE_CUT_PIC_);
        return strNewPicName;
    }

    /**
     * 执行压缩图片质量
     * @param atyCaller 调用的Activity
     * @param path 原文件名
     * @param compress 压缩质量
     */
    public static void compressPic (Activity atyCaller, String path, int compress) {
        Bitmap fileBitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 按照compress把压缩后的数据存放到baos中
        fileBitmap.compress(Bitmap.CompressFormat.JPEG, compress, baos);
        File tempPicFile = new File(path + "-");
        try {
            FileOutputStream fos = new FileOutputStream(tempPicFile);
            baos.writeTo(fos);
            //将原file删除
            File oldFile = new File(path);
            oldFile.delete();
            //将新file改名成原文件名
            tempPicFile.renameTo(oldFile);
            baos.close();
        } catch (FileNotFoundException fnfException) {
            fnfException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }



    // 以下是为了Android4.4能够使用本地相册做的修改
    //参考http://blog.163.com/shexinyang@126/blog/static/13673931220149135409328/
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri . This is useful for
     * MediaStore Uris , and other file - based ContentProviders.
     *
     * @param context
     *            The context.
     * @param uri
     *            The Uri to query.
     * @param selection
     *            (Optional) Filter used in the query.
     * @param selectionArgs
     *            (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);

            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally{
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    // 为了使用Android4.4的相册
    @SuppressLint("NewApi")
    private static String getPath(final Context context, final Uri uri) {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = MediaStore.MediaColumns._ID + "=?";
                final String[] selectionArgs = new String[] { split[1] };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

}


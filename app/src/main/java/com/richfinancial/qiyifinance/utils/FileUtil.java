package com.richfinancial.qiyifinance.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 *
 * 项目名称:
 * 包:        com.richibank.survey.utils
 * 类名称:   FileUtil
 * 类描述:    操作文件通用类
 * 创建人:    wangfangfang
 * 创建时间:  2016/8/16
 * 修改人:
 * 修改时间:
 * 修改备注:
 * 版本:      v1.0
 */

public class FileUtil {

    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值
    public static final  String FILE_PATH="";
    /**
     * 获取文件指定文件的指定单位的大小
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath,int sizeType){
        File file=new File(filePath);
        long blockSize=0;
        try {
            if(file.isDirectory()){
                blockSize = getFileSizes(file);
            }else{
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小","获取失败!");
        }
        return FormetFileSize(blockSize, sizeType);
    }
    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath){
        File file=new File(filePath);
        long blockSize=0;
        try {
            if(file.isDirectory()){
                blockSize = getFileSizes(file);
            }else{
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小","获取失败!");
        }
        return FormetFileSize(blockSize);
    }
    /**
     * 获取指定文件大小
     * @param file
     * @return
     * @throws Exception
     */
    private static long getFileSize(File file) throws Exception
    {
        long size = 0;
        if (file.exists()){
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        else{
            file.createNewFile();
            Log.e("获取文件大小","文件不存在!");
        }
        return size;
    }

    /**
     * 获取指定文件夹
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++){
            if (flist[i].isDirectory()){
                size = size + getFileSizes(flist[i]);
            }
            else{
                size =size + getFileSize(flist[i]);
            }
        }
        return size;
    }
    /**
     * 转换文件大小
     * @param fileS
     * @return
     */
    private static String FormetFileSize(long fileS)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize="0B";
        if(fileS==0){
            return wrongSize;
        }
        if (fileS < 1024){
            fileSizeString = df.format((double) fileS) + "B";
        }
        else if (fileS < 1048576){
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        }
        else if (fileS < 1073741824){
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        }
        else{
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }
    /**
     * 转换文件大小,指定转换的类型
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS,int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    public static boolean deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return true;
    }
    /***
     * 判断SDCard是否存在？是否可以进行读写
     * @return true 存在 false 不存在
     */
    public static boolean SDCardState(){

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//表示SDCard存在并且可以读写
            return true;
        }else{
            return false;
        }
    }

    /***
     * 获取SDCard文件路径
     * @return 返回SDCard路径
     */

    public static String SDCardPath(){

        if(SDCardState()){//如果SDCard存在并且可以读写
            return Environment.getExternalStorageDirectory().getPath();
        }else{
            return null;
        }
    }

    /**
     * 获取SDCard 总容量大小(MB)
     * @return 返回SDCard总容量
     */

    public static long SDCardTotal(){

        if(null != SDCardPath()&&SDCardPath().equals("")){
            StatFs statfs = new StatFs(SDCardPath());
            long totalBlocks = statfs.getBlockCount(); //获取SDCard的Block总数
            long blockSize = statfs.getBlockSize();        //获取每个block的大小
            long SDtotalSize = totalBlocks*blockSize/1024/1024; //计算SDCard 总容量大小MB
            return SDtotalSize;
        }else{
            return 0;
        }

    }

    /**
     * 获取SDCard 可用容量大小(MB)
     * @return SDCard 可用容量大小
     */
    public static long SDCardFree(){

        if(null != SDCardPath()&&SDCardPath().equals("")){
            StatFs statfs = new StatFs(SDCardPath());
            long availaBlocks = statfs.getAvailableBlocks();     //获取SDCard的Block可用数
            long blockSize = statfs.getBlockSize();//获取每个block的大小
            long SDFreeSize = availaBlocks*blockSize/1024/1024; //计算SDCard 可用容量大小MB
            return SDFreeSize;
        }else{
            return 0;
        }

    }

    /**
     * 创建目录
     * @param path 文件路径
     * @param dirName 要创建的目录名
     * @return 创建得到的目录
     */
    public static File createDir(String path,String dirName) {

        File dir = new File(path + dirName);
        dir.mkdir();
        return dir;

    }

    /**
     * 删除目录
     * @param path 文件路径
     * @param dirName 要删除的目录名称
     *  @return true 删除成功 false 删除失败
     */

    public static boolean delDir(String path,String dirName) {
        File dir = new File(path + dirName);
        return delDir(dir);

    }

    /**
     * 创建文件
     * @param path 文件路径
     * @param fileName 文件名
     * @return 创建的文件
     * @throws IOException
     */

    public static File createFile(String path,String fileName) throws IOException {

        File file = new File(path + fileName);
        file.createNewFile();
        return file;

    }

    /**
     * 判断文件是否已经存在
     * @param path 文件路径
     * @param fileName 要检查的文件名
     * @return boolean, true表示存在，false表示不存在
     */

    public static boolean isFileExist(String path,String fileName) {
        File file = new File(path + fileName);
        return file.exists();

    }

    /**
     * 删除文件
     * @param path 文件路径
     * @param fileName
     * @return boolean, true表示删除成功，false删除失败
     */

    public static boolean delFile(String path,String fileName) {
        File file = new File(path + fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        file.delete();
        return true;

    }

    /**
     * 修改文件或目录名
     * @param path 文件路径
     * @param oldfileName 旧的文件名
     * @param newFileName 新的文件名
     * @return boolean, true表示修改成功，false修改失败
     */

    public static boolean renameFile(String path,String oldfileName, String newFileName) {
        File oleFile = new File(path + oldfileName);
        File newFile = new File(path + newFileName);
        return oleFile.renameTo(newFile);

    }

    /**
     * 拷贝单个文件
     * @param path 文件路径
     * @param srcFileName 源文件名
     * @param destFileName 目标文件名
     * @throws IOException
     * @return boolean, true表示拷贝成功，false拷贝失败
     */

    public static boolean copyFileTo(String path,String srcFileName, String destFileName) throws IOException {

        File srcFile = new File(path + srcFileName);
        File destFile = new File(path + destFileName);
        return copyFileTo(srcFile, destFile);
    }

    /**
     * 拷贝指定目录的所有文件
     * @param path 文件路径
     * @param srcDirName 源文件夹名称
     * @param destDirName 目标文件夹名称
     * @return boolean, true表示拷贝成功，false拷贝失败
     * @throws IOException
     */

    public static boolean copyFilesTo(String path,String srcDirName, String destDirName) throws IOException {

        File srcDir = new File(path + srcDirName);
        File destDir = new File(path + destDirName);
        return copyFilesTo(srcDir, destDir);

    }

    /**
     * 移动单个文件
     * @param path 文件路径
     * @param srcFileName 源文件名称
     * @param destFileName 目录文件名称
     * @return boolean true：移动成功 false：移动失败
     * @throws IOException
     */

    public static boolean moveFileTo(String path,String srcFileName, String destFileName) throws IOException {
        File srcFile = new File(path + srcFileName);
        File destFile = new File(path + destFileName);
        return moveFileTo(srcFile, destFile);

    }

    /**
     * 移动指定目录的所有文件
     * @param path 文件路径
     * @param srcDirName 源文件夹名称
     * @param destDirName 目录文件夹名称
     * @return boolean true：移动成功 false：移动失败
     * @throws IOException
     */

    public static boolean moveFilesTo(String path,String srcDirName, String destDirName) throws IOException {
        File srcDir = new File(path + srcDirName);
        File destDir = new File(path + destDirName);
        return moveFilesTo(srcDir, destDir);
    }

    /**
     * 将文件写入sd卡。如:writeSDFile("test.txt");
     * @param fileName 文件名称
     * @return 输出流
     * @throws IOException
     */
    public static BufferedOutputStream writeSDFile(String fileName) throws IOException {
        String path=Environment.getExternalStorageDirectory().getPath() + "//";
        File file = new File(path + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        return new BufferedOutputStream(fos);
    }

    /**
     *  在原有文件上继续写文件。如:appendSDFile("test.txt");
     * @param fileName 文件名
     * @return  输出流
     * @throws IOException
     */
    public static BufferedOutputStream appendSDFile(String fileName) throws IOException {
        String path=Environment.getExternalStorageDirectory().getPath() + "//";
        File file = new File(path + fileName);
        FileOutputStream fos = new FileOutputStream(file, true);
        return new BufferedOutputStream(fos);
    }

    /**
     * 从SD卡读取文件。如:readSDFile("test.txt");
     * @param fileName 文件名
     * @return 输入流
     * @throws IOException
     */
    public static BufferedInputStream readSDFile(String fileName) throws IOException {
        String path=Environment.getExternalStorageDirectory().getPath() + "//";
        File file = new File(path + fileName);
        FileInputStream fis = new FileInputStream(file);
        return new BufferedInputStream(fis);
    }

    /**
     * 将文件写入应用私有的files目录。如:writeFile("test.txt");
     * @param fileName 文件名称
     * @return 输出流
     * @throws IOException
     */
    public static BufferedOutputStream wirteFile(Context context,String fileName) throws IOException {
        OutputStream os = context.openFileOutput(fileName,
                Context.MODE_WORLD_WRITEABLE);
        return new BufferedOutputStream(os);
    }

    /**
     * 在原有文件上继续写文件。如:appendFile("test.txt");
     * @param fileName 文件名称
     * @return 输出流
     * @throws IOException
     */
    public static BufferedOutputStream appendFile(Context context,String fileName) throws IOException {
        OutputStream os = context.openFileOutput(fileName, Context.MODE_APPEND);
        return new BufferedOutputStream(os);
    }

    /**
     * 从应用的私有目录files读取文件。如:readFile("test.txt");
     * @param fileName 文件名称
     * @return 输入流
     * @throws IOException
     */
    public static BufferedInputStream readFile(Context context,String fileName) throws IOException {
        InputStream is = context.openFileInput(fileName);
        return new BufferedInputStream(is);
    }

    /**
     * 将一个输入流中的内容写入到SD卡或者私有目录上生成文件
     * @param fileName 文件名
     * @param inputStream 字节输入流
     * @return 得到的文件
     */

    public static File streamToFile(String path,String fileName, InputStream inputStream) {

        File file = null;
        OutputStream output = null;
        try{
            createDir(path,fileName);
            file = createFile(path,fileName);
            output = new FileOutputStream(file);
            byte buffer [] = new byte[4 * 1024];
            while((inputStream.read(buffer)) != -1){
                output.write(buffer);
            }
            output.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                output.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return file;

    }

    /**
     * 删除一个文件
     * @param file 文件
     * @return   boolean  true：成功 false：失败
     */

    public static boolean delFile(File file) {

        if (file.isDirectory())
            return false;
        return file.delete();
    }

    /**
     * 删除一个目录（可以是非空目录）
     * @param dir 目录
     * @return   boolean  true：成功 false：失败
     */

    public static boolean delDir(File dir) {

        if (dir == null || !dir.exists() || dir.isFile()) {
            return false;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                delDir(file);// 递归
            }
        }
        dir.delete();
        return true;
    }

    /**

     * 拷贝一个文件
     * @param srcFile srcFile源文件
     * @param destFile destFile目标文件
     * @throws IOException
     * @return   boolean  true：成功 false：失败
     */

    public static boolean copyFileTo(File srcFile, File destFile) throws IOException {

        if (srcFile.isDirectory() || destFile.isDirectory())
            return false;// 判断是否是文件
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        int readLen = 0;
        byte[] buf = new byte[1024];
        while ((readLen = fis.read(buf)) != -1) {
            fos.write(buf, 0, readLen);
        }
        fos.flush();
        fos.close();
        fis.close();
        return true;
    }

    /**
     * 拷贝目录下的所有文件到指定目录
     * @param srcDir srcFile源目录
     * @param destDir destFile目标目录
     * @return  boolean  true：成功 false：失败
     * @throws IOException
     */

    public static boolean copyFilesTo(File srcDir, File destDir) throws IOException {

        if (!srcDir.isDirectory() || !destDir.isDirectory())
            return false;// 判断是否是目录
        if (!destDir.exists())
            return false;// 判断目标目录是否存在
        File[] srcFiles = srcDir.listFiles();
        for (int i = 0; i < srcFiles.length; i++) {
            if (srcFiles[i].isFile()) {
                // 获得目标文件
                File destFile = new File(destDir.getPath() + "//"
                        + srcFiles[i].getName());
                copyFileTo(srcFiles[i], destFile);
            } else if (srcFiles[i].isDirectory()) {
                File theDestDir = new File(destDir.getPath() + "//"
                        + srcFiles[i].getName());
                copyFilesTo(srcFiles[i], theDestDir);
            }
        }
        return true;
    }

    /**
     * 移动一个文件
     * @param srcFile srcFile源文件
     * @param destFile destFile目标文件
     * @return boolean  true：成功 false：失败
     * @throws IOException
     */

    public static boolean moveFileTo(File srcFile, File destFile) throws IOException {

        boolean iscopy = copyFileTo(srcFile, destFile);
        if (!iscopy)
            return false;
        delFile(srcFile);
        return true;
    }

    /**
     * 移动目录下的所有文件到指定目录
     * @param srcDir srcFile源目录
     * @param destDir destFile目标目录
     * @return  boolean  true：成功 false：失败
     * @throws IOException
     */

    public static boolean moveFilesTo(File srcDir, File destDir) throws IOException {

        if (!srcDir.isDirectory() || !destDir.isDirectory()) {
            return false;
        }

        File[] srcDirFiles = srcDir.listFiles();
        for (int i = 0; i < srcDirFiles.length; i++) {
            if (srcDirFiles[i].isFile()) {
                File oneDestFile = new File(destDir.getPath() + "//"
                        + srcDirFiles[i].getName());
                moveFileTo(srcDirFiles[i], oneDestFile);
                delFile(srcDirFiles[i]);
            } else if (srcDirFiles[i].isDirectory()) {
                File oneDestFile = new File(destDir.getPath() + "//"
                        + srcDirFiles[i].getName());
                moveFilesTo(srcDirFiles[i], oneDestFile);
                delDir(srcDirFiles[i]);
            }
        }

        return true;

    }

}

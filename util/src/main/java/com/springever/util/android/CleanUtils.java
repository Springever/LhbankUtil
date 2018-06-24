package com.springever.util.android;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * <pre>
 *

 *     time  : 2016/09/27
 *     desc  : 清除相关工具类
 * </pre>
 */
public final class CleanUtils {

	private CleanUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	/**
	 * 清除内部缓存
	 * <p>
	 * /data/data/com.xxx.xxx/cache
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalCache() {
		return FileUtils.deleteFilesInDir(Utils.getContext().getCacheDir());
	}

	/**
	 * 清除内部文件
	 * <p>
	 * /data/data/com.xxx.xxx/files
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalFiles() {
		return FileUtils.deleteFilesInDir(Utils.getContext().getFilesDir());
	}

	/**
	 * 清除内部数据库
	 * <p>
	 * /data/data/com.xxx.xxx/databases
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalDbs() {
		return FileUtils.deleteFilesInDir(Utils.getContext().getFilesDir()
				.getParent()
				+ File.separator + "databases");
	}

	/**
	 * 根据名称清除数据库
	 * <p>
	 * /data/data/com.xxx.xxx/databases/dbName
	 * </p>
	 * 
	 * @param dbName
	 *            数据库名称
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalDbByName(String dbName) {
		return Utils.getContext().deleteDatabase(dbName);
	}

	/**
	 * 清除内部SP
	 * <p>
	 * /data/data/com.xxx.xxx/shared_prefs
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanInternalSP() {
		return FileUtils.deleteFilesInDir(Utils.getContext().getFilesDir()
				.getParent()
				+ File.separator + "shared_prefs");
	}

	/**
	 * 清除外部缓存
	 * <p>
	 * /storage/emulated/0/android/data/com.xxx.xxx/cache
	 * </p>
	 * 
	 * @return {@code true}: 清除成功<br>
	 *         {@code false}: 清除失败
	 */
	public static boolean cleanExternalCache() {
		return SDCardUtils.isSDCardEnable()
				&& FileUtils.deleteFilesInDir(Utils.getContext()
						.getExternalCacheDir());
	}

	/**
	 * 清除/data/data/com.xxx.xxx/files下的内容
	 * 
	 * @Description
	 * @param context
	 * @Author Lewis(lgs@yitong.com.cn) 2014-7-18
	 */
	public static void cleanFiles(Context context) {
		deleteFilesByDirectory(context.getFilesDir());
	}

	/**
	 * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
	 * 
	 * @Description
	 * @param context
	 * @Author Lewis(lgs@yitong.com.cn) 2014-7-18
	 */
	public static void cleanExternalCache(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir());
		}
	}

	/**
	 * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
	 * 
	 * @Description
	 * @param filePath
	 * @Author Lewis(lgs@yitong.com.cn) 2014-7-18
	 */
	public static void cleanCustomCache(String filePath) {
		deleteFilesByDirectory(new File(filePath));
	}

	/**
	 * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
	 * 
	 * @Description
	 * @param directory
	 */
	public static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}
}

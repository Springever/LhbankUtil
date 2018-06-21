package com.springever.util.android.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * <pre>
 *     author: maowenping
 *     blog  : http://blog.csdn.net/mwp123555/
 *     time  : 2017/06/16
 *     desc  : 弹出框相关工具类
 * </pre>
 */
public class DialogUtils {
	
	/**
	 * 带确定按钮的提示文字
	 * 
	 * @Description
	 * @param msg
	 */
	public void AlertDialogInfo(Context context, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示：");
		builder.setMessage(msg);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.setCancelable(false);
		builder.show();
		return;
	}

	/**
	 * @Description 提示框，自己完成确定按钮事件
	 * @param msg
	 * @param listener
	 */
	public void AlertDialogInfo(Context context, String msg,
			DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("提示：");
		builder.setMessage(msg);
		builder.setPositiveButton("确定", listener);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.setCancelable(false);
		builder.show();
		return;
	}

	/**
	 * @Description 提示框，自己完成确定按钮事件
	 * @param msg
	 * @param listener
	 */
	public void AlertPositiveDialogInfo(Context context, String title,
			String btext, String msg, DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(btext, listener);
		builder.create();
		builder.setCancelable(false);
		builder.show();
		return;
	}
}

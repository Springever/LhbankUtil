package com.springever.util.android.util.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.springever.util.android.util.log.LogCollector;

/**
 * 
 * @author maowenping 2015-12-12
 * @description 收到广播
 *
 */
public class HandWareInfoReceiver extends BroadcastReceiver{

	private static final String TAG="HandWareInfoReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		LogCollector.writeLog("LoginFragment", "", "", "进入HandWareInfoReceiver了");
		Intent service = new Intent(context, HandWareInfoService.class);
		context.startService(service);
	}
}

package com.springever.util.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handler的使用
 */
public class HandlerTimeUtils {

    private Timer timer = new Timer();

    private Handler handler_timer = new MyHandler();

    private  static HandlerTimeUtils handlerTimeUtils;

    private  HandlerTimeUtils(){

    }

    public static HandlerTimeUtils getInstance(){
        if(handlerTimeUtils==null){
            new HandlerTimeUtils();
        }
        return handlerTimeUtils;
    }

    public void timer() {
        timer.schedule(new MyTimer(), 1, 5000);
    }

    // 设置定时器任务
    class MyTimer extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            handler_timer.sendMessage(message);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            if (message.what == 1) {
            }
        }
    }

    public void getHandler(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                Message message = new Message();
                message.what = 2;
                handler_timer.sendMessage(message);
                // handler_timer.sendEmptyMessage(int what);
                // handler_timer.sendEmptyMessageAtTime(int what, long uptimeMillis);//什么时间执行发送消息
                // handler_timer.sendEmptyMessageDelayed(int what, long delayMillis);//延迟多少执行发送消息
                // handler_timer.sendMessageAtTime(Message msg, long uptimeMillis);//什么时间执行发送消息
                // handler_timer.sendMessageDelayed(Message msg, long uptimeMillis);//延迟多少执行发送消息
            }
        }).start();
        handler_timer.postAtTime(new Runnable() {
            @Override
            public void run() {
                System.out.println("handler_timer."
                        + Thread.currentThread().getName() + "-"
                        + Thread.currentThread().getId());
            }
        }, "xxx", SystemClock.uptimeMillis() + 4000);
    }

    public void getPostHandler(){
        new Thread() {
            public void run() {
                Looper.prepare();
                new Handler().post(runnable);//在子线程中直接去new 一个handler
                Looper.loop();//这种情况下，Runnable对象是运行在子线程中的，可以进行联网操作，但是不能更新UI
            }
        }.start();
    }
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("getPostHandler."
                    + Thread.currentThread().getName() + "-"
                    + Thread.currentThread().getId());
        }
    };
}

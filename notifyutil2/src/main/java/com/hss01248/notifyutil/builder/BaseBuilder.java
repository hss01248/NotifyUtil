package com.hss01248.notifyutil.builder;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.hss01248.notifyutil.NotifyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class BaseBuilder {
    //最基本的ui
    public  int smallIcon;
    public CharSequence contentTitle;
    public CharSequence contentText;

    public boolean headup;
    CharSequence summaryText;

    //最基本的控制管理
    public int id;

    public int bigIcon;
    public CharSequence ticker = "您有新的消息";

    public CharSequence subText;
    public int flag = NotificationCompat.FLAG_AUTO_CANCEL;
    public int priority = NotificationCompat.PRIORITY_DEFAULT;

    public Uri soundUri;
    public long[] vibratePatten;
    public int rgb;
    public int onMs;
    public int offMs;

    public int defaults = NotificationCompat.DEFAULT_LIGHTS;//默认只有走马灯提醒
    public  boolean sound = true;
   public boolean vibrate = true;
   public boolean lights = true;

    public BaseBuilder setLockScreenVisiablity(int lockScreenVisiablity) {
        this.lockScreenVisiablity = lockScreenVisiablity;
        return this;
    }

    public int lockScreenVisiablity = NotificationCompat.VISIBILITY_SECRET;



    public long when;
    //事件
    public PendingIntent contentIntent;
    public PendingIntent deleteIntent;
    public PendingIntent fullscreenIntent;

    //种类
    public NotificationCompat.Style style;

    public BaseBuilder setOnGoing() {
        this.onGoing = true;
        return this;
    }

    public boolean onGoing = false;

    public BaseBuilder setForgroundService() {
        this.forgroundService = true;
        this.onGoing = true;
        return this;
    }

    public boolean forgroundService = false;

    //带按钮的

    public List<BtnActionBean> btnActionBeens;
    public BaseBuilder addBtn(int icon,CharSequence text,PendingIntent pendingIntent){
        if(btnActionBeens == null){
            btnActionBeens = new ArrayList<>();
        }
        if(btnActionBeens.size()>5){
            throw  new RuntimeException("5 buttons at most!");
        }
        btnActionBeens.add(new BtnActionBean(icon,text,pendingIntent));

        return this;
    }

    public static class BtnActionBean{
        public int icon;
        public CharSequence text;
        public PendingIntent pendingIntent;

        public BtnActionBean(int icon, CharSequence text, PendingIntent pendingIntent) {
            this.icon = icon;
            this.text = text;
            this.pendingIntent = pendingIntent;
        }
    }


    public NotificationCompat.Builder getcBuilder() {
        return cBuilder;
    }

    protected NotificationCompat.Builder cBuilder;





    public BaseBuilder setBase(int icon,CharSequence contentTitle,CharSequence contentText){
        this.smallIcon = icon;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        return this;
    }

    public BaseBuilder setId(int id){
        this.id = id;
        return this;
    }

    public BaseBuilder setSummaryText(CharSequence summaryText){
        this.summaryText = summaryText;
        return this;
    }
    public BaseBuilder setContentText(CharSequence contentText){
        this.contentText = contentText;
        return this;
    }
    public BaseBuilder setPriority(int priority){
        this.priority = priority;
        return this;
    }
    public BaseBuilder setContentIntent(PendingIntent contentIntent){
        this.contentIntent  = contentIntent;
        return this;
    }
    public BaseBuilder setDeleteIntent(PendingIntent deleteIntent){
        this.deleteIntent  = deleteIntent;
        return this;
    }
    //todo
    public BaseBuilder setFullScreenIntent(PendingIntent fullscreenIntent){
        this.fullscreenIntent  = fullscreenIntent;
        return this;
    }
    public BaseBuilder setSmallIcon(int smallIcon){
        this.smallIcon = smallIcon;
        return this;
    }
    public BaseBuilder setBigIcon(int bigIcon){
        this.bigIcon = bigIcon;
        return this;
    }
    public BaseBuilder setHeadup(){
        this.headup = true;
        return this;
    }
    public BaseBuilder setTicker(CharSequence ticker){
        this.ticker = ticker;
        return this;
    }
    public BaseBuilder setSubtext(CharSequence subText){
        this.subText = subText;
        return this;
    }
    public BaseBuilder setAction(boolean sound, boolean vibrate, boolean lights){
        this.sound = sound;
        this.vibrate = vibrate;
        this.lights = lights;
        return this;
    }



    public void build(){
        cBuilder = new NotificationCompat.Builder(NotifyUtil.context);
        cBuilder.setContentIntent(contentIntent);// 该通知要启动的Intent

        if(smallIcon >0){
            cBuilder.setSmallIcon(smallIcon);// 设置顶部状态栏的小图标
        }
        if(bigIcon >0){
            cBuilder.setLargeIcon(BitmapFactory.decodeResource(NotifyUtil.context.getResources(), bigIcon));
        }

        cBuilder.setTicker(ticker);// 在顶部状态栏中的提示信息

        cBuilder.setContentTitle(contentTitle);// 设置通知中心的标题
        if(!TextUtils.isEmpty(contentText)){
            cBuilder.setContentText(contentText);// 设置通知中心中的内容
        }

        cBuilder.setWhen(System.currentTimeMillis());
        //cBuilder.setStyle()

		/*
         * 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失,
		 * 不设置的话点击消息后也不清除，但可以滑动删除
		 */
        cBuilder.setAutoCancel(true);

        // 将Ongoing设为true 那么notification将不能滑动删除
        // notifyBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
		 * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
		 */
        cBuilder.setPriority(priority);

        //int defaults = 0;

        if (sound) {
            defaults |= Notification.DEFAULT_SOUND;
        }
        if (vibrate) {
            defaults |= Notification.DEFAULT_VIBRATE;
        }
        if (lights) {
            defaults |= Notification.DEFAULT_LIGHTS;
        }
        cBuilder.setDefaults(defaults);


        //按钮
        if(btnActionBeens!=null && btnActionBeens.size()>0){
            for(BtnActionBean bean: btnActionBeens){
                cBuilder.addAction(bean.icon,bean.text,bean.pendingIntent);
            }
        }

        //headup
        if(headup){
            cBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
            cBuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        }else {
            cBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            cBuilder.setDefaults(NotificationCompat.DEFAULT_LIGHTS);
        }
        if(TextUtils.isEmpty(ticker)){
            cBuilder.setTicker("你有新的消息");
        }
        cBuilder.setOngoing(onGoing);
        cBuilder.setFullScreenIntent(fullscreenIntent,true);
        cBuilder.setVisibility(lockScreenVisiablity);

    }

    public void show(){
        build();
        Notification notification = cBuilder.build();
        if(forgroundService){
            notification.flags = Notification.FLAG_FOREGROUND_SERVICE;
        }

      NotifyUtil.notify(id,notification);
    }
















}

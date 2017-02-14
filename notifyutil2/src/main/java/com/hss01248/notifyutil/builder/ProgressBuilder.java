package com.hss01248.notifyutil.builder;

import android.support.v4.app.NotificationCompat;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class ProgressBuilder extends BaseBuilder{
    public int max;
    public int progress;
    public boolean interminate = false;

    public ProgressBuilder setProgress(int max,int progress,boolean interminate){
        this.max = max;
        this.progress = progress;
        this.interminate = interminate;
        return this;
    }

    @Override
    public void build() {
        super.build();
        cBuilder.setProgress(max,progress, interminate);
        cBuilder.setDefaults(0);
        cBuilder.setPriority(NotificationCompat.PRIORITY_LOW);
    }
}

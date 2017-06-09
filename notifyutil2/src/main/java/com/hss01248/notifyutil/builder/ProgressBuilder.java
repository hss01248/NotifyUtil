package com.hss01248.notifyutil.builder;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class ProgressBuilder extends BaseBuilder{
    public int max;
    public int progress;
    public boolean interminate = false;

    @Deprecated
    public ProgressBuilder setProgress(int max,int progress,boolean interminate){
        setProgressAndFormat(progress,max,interminate,"%d/%d");
        return this;
    }

    public ProgressBuilder setProgressAndFormat(int progress,int max,boolean interminate,String format){
        this.max = max;
        this.progress = progress;
        this.interminate = interminate;

        //contenttext的显示
        if(TextUtils.isEmpty(format) ){
            format = "进度:%d/%d";
            setContentText(String.format(format,progress,max));
        }else {
            if(format.contains("%%")){//百分比类型
               int progressf = progress*100/max;
                setContentText(String.format(format,progressf));
            }else {

                setContentText(String.format(format,progress,max));
            }

        }

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

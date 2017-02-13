package com.hss01248.notifyutil.builder;

import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class MailboxBuilder extends BaseBuilder {
    ArrayList<String> messageList;
    public MailboxBuilder addMsg(String msg){
        if(messageList == null){
            messageList = new ArrayList<>();
        }
        messageList.add(msg);
        return this;
    }

    @Override
    public void build() {
        super.build();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (String msg : messageList) {
            inboxStyle.addLine(msg);
        }
        String text = "[" + messageList.size() + "]条信息";
        inboxStyle.setSummaryText(text);
        cBuilder.setStyle(inboxStyle);
        cBuilder.setContentText("你有"+text);
        if(TextUtils.isEmpty(ticker)){
            cBuilder.setTicker(text);
        }
    }
}

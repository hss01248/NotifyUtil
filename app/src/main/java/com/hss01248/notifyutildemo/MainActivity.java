package com.hss01248.notifyutildemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hss01248.notifyutil.NotifyUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.simple)
    Button simple;
    @Bind(R.id.pic)
    Button pic;
    @Bind(R.id.progress)
    Button progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        NotifyUtil.init(getApplicationContext());
    }

    @OnClick({R.id.simple, R.id.pic, R.id.progress,R.id.bigtext,R.id.mailbox,R.id.media})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simple:
                NotifyUtil.buildSimple(100,R.drawable.timg,"标题标题标题图表题滴滴滴","哈哈哈哈哈哈哈呼呼呼呼呼呼",null)
                        .setHeadup()
                        .addBtn(R.mipmap.ic_launcher,"left", NotifyUtil.buildIntent(MainActivity.class))
                        .addBtn(R.mipmap.ic_launcher,"rightdd", NotifyUtil.buildIntent(MainActivity.class))
                        .show();

                break;
            case R.id.pic:
                NotifyUtil.buildBigPic(101,R.drawable.timg,"title","content","summmaujds")
                        .setPicRes(R.drawable.timg2)
                        .show();
                break;
            case R.id.progress:
                showProgress();
               // NotifyUtil.buildProgress(102,R.mipmap.ic_launcher,"正在下载",5,100,false).show();
                break;
            case R.id.bigtext:
                NotifyUtil.buildBigText(103,R.drawable.timg,"jtitle","我学习最快的方法就是先看效果，" +
                        "再想原理最后，将它实现。效果是最直观的，而且能够有效的判断所学的东西是不是想要的。" +
                        "现在网上的资料实在太杂，很多花了很多时间去研究，最后发现坑爹了，不是想要的。" +
                        "这篇文章首先会介绍能实现的主要功能。然后是解决问题的基本思想，接着是具体的一些实现。" +
                        "本篇文章和以前的风格有所不同，以前都是文章中代码比较少，附上demo,但发现这样可能不方便读者，" +
                        "所以采用了实现效果以及主要代码的形式。这种方式现在还在测试阶段，如果觉得以前的模式比较" +
                        "好或者其他更好的方式的话可以給我留言，以后的文章会做出相应的调整 。").show();

                break;
            case R.id.mailbox:
                NotifyUtil.buildMailBox(104,R.drawable.timg,"title")
                        .addMsg("11111111111")
                        .addMsg("33333333333333")
                        .addMsg("6666666666666666666")
                        .show();
                break;
            case R.id.media://todo failed
                NotifyUtil.buildMedia(105,R.drawable.timg,"title","content")
                        .addBtn(R.mipmap.ic_launcher,"left", NotifyUtil.buildIntent(MainActivity.class))
                        .addBtn(R.mipmap.ic_launcher,"center", NotifyUtil.buildIntent(MainActivity.class))
                        .addBtn(R.mipmap.ic_launcher,"right", NotifyUtil.buildIntent(MainActivity.class))
                        .show();
        }
    }
    int progresses =0;

    private void showProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progresses >=100){
                    return;
                }
                progresses = progresses +10;
                NotifyUtil.buildProgress(102,R.mipmap.ic_launcher,"正在下载",progresses,100).show();
                showProgress();

            }
        },500);

    }
}

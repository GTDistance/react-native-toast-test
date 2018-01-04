package com.thomas.toastlibrary;

import android.content.Context;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by Thomas.Wang on 2018/1/3.
 */
public class MyNativeModule extends ReactContextBaseJavaModule {
    private Context mContext;
    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        //返回的这个名字是必须的，在rn代码中需要这个名字来调用该类的方法。
        return "MyNativeModule";
    }

    //函数不能有返回值，因为被调用的原生代码是异步的，原生代码执行结束之后只能通过回调函数或者发送信息给rn那边。
    @ReactMethod
    public void rnCallNative(String msg, final Callback callback){

        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.invoke("谁是sb？");
            }
        }).start();

    }
}

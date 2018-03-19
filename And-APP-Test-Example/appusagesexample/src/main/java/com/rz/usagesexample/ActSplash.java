package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rz.librarycore.apppackage.APPStaticPackageInfo;
import com.rz.librarycore.log.SecureKeyManager;
import com.rz.librarycore.storage.SharePrefPrivateHandler;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        SharePrefPrivateHandler sharePrefHandler = new SharePrefPrivateHandler(context, APPStaticPackageInfo.getPackageName(context));
        sharePrefHandler.clearAll();
        SecureKeyManager secureKeyManager = new SecureKeyManager(activity, context);
        SecureKeyManager.onSetAppIsRunFirstTime(context);
        startActivity(new Intent(context, ActYoutubeTest.class));
        startService(new Intent(getBaseContext(), OnServiceTest.class));
        finish();
    }
}
//https://github.com/youtube/yt-android-player/blob/master/src/com/examples/youtubeapidemo/PlayerControlsDemoActivity.java
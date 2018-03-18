package com.rz.usagesexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        startActivity(new Intent(context, ActYoutubeTest.class));
        finish();
    }
}
//https://github.com/youtube/yt-android-player/blob/master/src/com/examples/youtubeapidemo/PlayerControlsDemoActivity.java
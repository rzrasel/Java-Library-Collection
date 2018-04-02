package com.sm.answerform;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    private Ringtone ringtone;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"WakeUpDudeTodayOffice",Toast.LENGTH_SHORT).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }
}
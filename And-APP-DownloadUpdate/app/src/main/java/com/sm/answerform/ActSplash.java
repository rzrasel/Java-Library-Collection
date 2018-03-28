package com.sm.answerform;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ActSplash extends AppCompatActivity {
    private Activity activity;
    private Context context;
    public static final String INTENT_NOTIFY = "co.happybirthday.notification.INTENT_NOTIFY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        activity = this;
        context = this;
        /*Date date = new Date();
        date.getMinutes();
        int interval = 2;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        //Log.i("Time ", String.valueOf(df.format(time.getTime())));
        calendar.add(Calendar.MINUTE, interval);
        setAlarm(this, calendar);*/
        new OnDownloadManager();
        //startActivity(new Intent(context, ActDownloadAPK.class));
        //finish();
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public class OnDownloadManager {
        public OnDownloadManager() {
            if (Build.VERSION.SDK_INT >= 23) {
                int REQUEST = 112;
                //String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
                String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
                if (!hasPermissions(context, PERMISSIONS)) {
                    ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, REQUEST);
                    return;
                }
            }
            //get destination to update file and set Uri
            //TODO: First I wanted to store my update .apk file on internal storage for my app but apparently android does not allow you to open and install
            //aplication with existing package from there. So for me, alternative solution is Download directory in external storage. If there is better
            //solution, please inform us in comment
            String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
            String fileName = "AppName.apk";
            destination += fileName;
            final Uri uri = Uri.parse("file://" + destination);

            //Delete update file if exists
            File file = new File(destination);
            if (file.exists())
                //file.delete() - test this, I think sometimes it doesnt work
                file.delete();

            //get url of app on server
            String url = context.getString(R.string.update_app_url);
            url = "http://androidpala.com/tutorial/app-debug.apk";

            //set downloadmanager
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setDescription(context.getString(R.string.notification_description));
            request.setTitle(context.getString(R.string.title));

            //set destination
            request.setDestinationUri(uri);

            // get download service and enqueue file
            final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            final long downloadId = manager.enqueue(request);

            //set BroadcastReceiver to install app when .apk is downloaded
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                public void onReceive(Context ctxt, Intent intent) {
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    install.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    install.setDataAndType(uri, manager.getMimeTypeForDownloadedFile(downloadId));
                    //intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    startActivity(install);

                    unregisterReceiver(this);
                    finish();
                }
            };
            //register receiver for when .apk download is compete
            registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
    }

    public void setAlarm(Context argContext, Calendar argCalendar) {
        int notificationId = new Random().nextInt();
        /*String myDate = "2014/10/29 18:10:45";
        //creates a formatter that parses the date in the given format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(myDate);*/
        int requestCode = 234324243;
        int flags = 0;
        //long timeInMillis = argDate.getTime();
        long timeInMillis = argCalendar.getTimeInMillis();
        /*int value = Integer.parseInt((fromTimeBtn.getText().toString()));
        Intent intent = new Intent(this, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);*/
        Intent intent = new Intent(argContext, ActSplash.class);
        intent.putExtra(INTENT_NOTIFY, true);
        intent.putExtra("DATA", "Data");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(argContext, requestCode, intent, flags);
        //PendingIntent pendingIntent = PendingIntent.getService(argContext, requestCode, intent, flags);

        AlarmManager alarmManager = (AlarmManager) argContext.getSystemService(ALARM_SERVICE);
        alarmManager.set(alarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
        //Toast.makeText(this, "Alarmsetted", Toast.LENGTH_SHORT).show();

    }

    private void testOne() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "app.apk")), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
//http://androidpala.com/install-android-app-programmatically/
//http://www.javacodex.com/Date-and-Time/Add-Time-To-A-Timestamp
//https://github.com/ivijaykumaar/Remainder/blob/master/app/src/main/java/com/atom/remainder/NewEventAct.java
//https://github.com/dharmakshetri/HappyBirthDay
//https://www.javacodegeeks.com/2012/09/android-alarmmanager-tutorial.html
//https://github.com/AbhinayB/Medical-Remainder/tree/master/MedicalAlert/app/src/main/java/com/example/medicalalert
//https://www.youtube.com/watch?v=cODKB9fApXk
package com.sm.answerform;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActDownloadAPK extends AppCompatActivity {
    private Activity activity;
    private Context context;
    ProgressDialog bar;
    private static String TAG = "MainActivity";
    private int AppVersion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_download_apk);
        activity = this;
        context = this;
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST = 112;
            //String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE};
            if (!hasPermissions(context, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, REQUEST);
                return;
            }
        }
        TextView heading = (TextView) findViewById(R.id.heading);
        Button updateBtn = (Button) findViewById(R.id.btn);
        heading.setText("App Version: " + AppVersion);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadNewVersion().execute();
            }
        });
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

    public class DownloadNewVersion extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar = new ProgressDialog(context);
            bar.setCancelable(false);
            bar.setMessage("Downloading...");
            bar.setIndeterminate(true);
            bar.setCanceledOnTouchOutside(false);
            bar.show();

        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            bar.setIndeterminate(false);
            bar.setMax(100);
            bar.setProgress(progress[0]);
            String msg = "";
            if (progress[0] > 99) {
                msg = "Finishing... ";
            } else {
                msg = "Downloading... " + progress[0] + "%";
            }
            bar.setMessage(msg);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            bar.dismiss();
            if (result) {
                Toast.makeText(getApplicationContext(), "Update Done", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getApplicationContext(), "Error: Try Again", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            Boolean flag = false;
            try {
                URL url = new URL("http://androidpala.com/tutorial/app-debug.apk");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.connect();
                String PATH = Environment.getExternalStorageDirectory() + "/Download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, "app-debug.apk");

                if (outputFile.exists()) {
                    outputFile.delete();
                }

                FileOutputStream fos = new FileOutputStream(outputFile);
                InputStream is = conn.getInputStream();

                int total_size = 1431692;//size of apk
                total_size = conn.getContentLength();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                int per = 0;
                int downloaded = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    downloaded += len1;
                    per = (int) (downloaded * 100 / total_size);
                    publishProgress(per);
                }
                fos.close();
                is.close();
                OpenNewVersion(PATH);
                flag = true;
            } catch (Exception e) {
                Log.e(TAG, "Update Error: " + e.getMessage());
                flag = false;
            }
            return flag;
        }
    }

    void OpenNewVersion(String location) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(location + "app-debug.apk")), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
//http://www.mysamplecode.com/2013/05/android-update-application.html
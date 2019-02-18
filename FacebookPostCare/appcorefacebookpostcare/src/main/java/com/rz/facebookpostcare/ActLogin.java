package com.rz.facebookpostcare;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;

import com.facebook.CallbackManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ActLogin extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private CallbackManager callbackManager;
    private Button sysBtnFbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        activity = this;
        context = this;
        //////////////


        //////////////
        callbackManager = CallbackManager.Factory.create();
        sysBtnFbLogin = (Button) findViewById(R.id.sysBtnFbLogin);
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile", "AccessToken");
        //////////////


        //////////////
        printHashKey(context);
        //System.out.println("DATE: " + PrettyDate.getDate("2018-06-23 14:16:00"));
        //System.out.println("DATE: " + PrettyDate.getDate((new Date()).getTime() + ""));
    }

    public static void printHashKey(Context argContext) {
        try {
            String packageName = argContext.getApplicationContext().getPackageName();
            PackageInfo info = argContext.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                System.out.println("printHashKey() Hash Key: [" + hashKey + "]");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("printHashKey()" + e);
        } catch (Exception e) {
            System.out.println("printHashKey()" + e);
        }
    }
}

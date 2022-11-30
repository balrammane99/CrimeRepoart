package com.intellicloudpps.crimerepoart.Activitys;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.intellicloudpps.crimerepoart.Healper.Constant;
import com.intellicloudpps.crimerepoart.Healper.SharedSession;
import com.intellicloudpps.crimerepoart.MainActivity;
import com.intellicloudpps.crimerepoart.R;

public class SplashActivity extends AppCompatActivity {
    int splash=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        String email= SharedSession.getStr(getApplicationContext(), Constant.EMAIL);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i=new Intent(SplashActivity.this,MainActivity.class);
//                startActivity(i);
//                finish();



                if (!isEmpty(email)){

                    String usertype= SharedSession.getStr(getApplicationContext(), Constant.USERTYPE);
                    if(usertype.equals("user")) {
                        Intent l = new Intent(SplashActivity.this, CustomerPageActivity.class);
                        startActivity(l);
                        finish();
                    }
                    else{
                        Intent l=new Intent(SplashActivity.this, AdminPageActivity.class);
                        startActivity(l);
                        finish();
                    }
                }

                else   {
                    Intent j=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(j);
                    finish();
                }

            }
        },splash);
    }
}
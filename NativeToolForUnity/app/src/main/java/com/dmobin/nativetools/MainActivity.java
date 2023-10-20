package com.dmobin.nativetools;

import static android.app.PendingIntent.FLAG_IMMUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    NotificationHelper notificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button notiButton = findViewById(R.id.noti);
        notiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNotification(v);
            }
        });

        Button noticustomButton = findViewById(R.id.button2);
        noticustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCustomNotification(v);
            }
        });
    }

    public void ShowNotification(View view) {
        Log.d("Testing", "ShowNotification: ");
        notificationHelper = new NotificationHelper(this,"TEST_NOTIFICATION_CHANNEL","Test Notification",NotificationManager.IMPORTANCE_DEFAULT,"This is a test notification channel");
        notificationHelper.pushNotification("Test Notification","This is a test notification",R.drawable.baseline_notifications_24,0x88FF0000 ,null , BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background),0);
    }
    public void ShowCustomNotification(View view)
    {
        notificationHelper = new NotificationHelper(this,"TEST_NOTIFICATION_CHANNEL","Test Notification",NotificationManager.IMPORTANCE_DEFAULT,"This is a test notification channel");
        notificationHelper.pushCustomNotification("Test Notification","This is a test notification",R.drawable.baseline_notifications_24,0x88FF0000 ,null , BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background),0);
    }

}
package com.dmobin.customnotification;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationManager extends AppCompatActivity {
    NotificationHelper notificationHelper;
    public void Init(Context context, String channelId, String channelName, int NotifyImportance, String channelDesc) {
        notificationHelper = new NotificationHelper(context,channelId,channelName,NotifyImportance,channelDesc);
        Log.d("NotificationManager", "Init");
    }
    public void ShowNotification() {
        Log.d("NotificationManager", "ShowNotification");
        notificationHelper = new NotificationHelper(this,"TEST_NOTIFICATION_CHANNEL","Test Notification", android.app.NotificationManager.IMPORTANCE_DEFAULT,"This is a test notification channel");
        notificationHelper.pushNotification("Test Notification","This is a test notification",R.drawable.baseline_notifications_24,0x88FF0000 ,null , null,R.drawable.ic_launcher_background);
    }
    public void ShowCustomNotification()
    {
        Log.d("NotificationManager", "ShowCustomNotification");
        notificationHelper = new NotificationHelper(this,"TEST_NOTIFICATION_CHANNEL","Test Notification", android.app.NotificationManager.IMPORTANCE_DEFAULT,"This is a test notification channel");
        notificationHelper.pushCustomNotification("Test Notification","This is a test notification",R.drawable.baseline_notifications_24,0x88FF0000 ,null , null,R.drawable.ic_launcher_background);
    }
}

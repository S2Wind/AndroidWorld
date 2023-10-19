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

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        customView.setTextViewText(R.id.notification_text, "Your custom text here");

// Set up the play button PendingIntent
        Intent playIntent = new Intent(this, PlayButtonReceiver.class); // The component that should handle the play button's action
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 0, playIntent, FLAG_IMMUTABLE);
        customView.setOnClickPendingIntent(R.id.play_button, playPendingIntent);
    }

    public void ShowNotification(View view) {
        NotificationHelper.createNotificationChannel(this);
        sendTestNotification();
    }
    public void ShowCustomNotification(View view)
    {
        NotificationHelper.createNotificationChannel(this);
        sendCustomNotification();
    }

    private void sendTestNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)  // Replace with your icon
                .setContentTitle("Test Notification")
                .setContentText("This is a test notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Notification", "Request admin!");
            return;
        }
        Log.d("Notification", "Normal Notification");
        notificationManager.notify(100, builder.build());  // '100' is a notification ID, it can be any unique integer
    }

    private void sendCustomNotification() {
        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.custom_notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(customView)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Notification", "Request admin!");
            return;
        }
        Log.d("Notification", "Custom Notification");
        notificationManager.notify(200, builder.build());
    }

}
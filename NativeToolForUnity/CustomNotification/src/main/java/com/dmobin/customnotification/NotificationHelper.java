package com.dmobin.customnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.dmobin.customnotification.R;
import com.unity3d.player.UnityPlayer;

public class NotificationHelper {
    public static final String CHANNEL_ID = "TEST_NOTIFICATION_CHANNEL";

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Test Notification";
            String description = "This is a test notification channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private static Context getContext() {
        return UnityPlayer.currentActivity;
    }

    public static void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "YOUR_CHANNEL_ID")
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        notificationManager.notify(100, builder.build());
    }

}

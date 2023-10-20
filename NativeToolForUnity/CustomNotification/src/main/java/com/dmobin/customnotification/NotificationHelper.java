package com.dmobin.customnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.ColorInt;
import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private Context context;
    private String channelId;
    private String channelName;
    private String channelDesc;
    private int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;
    NotificationChannel channel;

    public NotificationHelper(Context context, String channelId, String channelName, int NotifyImportance, String channelDesc) {
        this.context = context;
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelDesc = channelDesc;
        this.notificationImportance = NotifyImportance;
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelId, channelName, notificationImportance);
            channel.setDescription(channelDesc);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        } else {
            Log.d("NotificationHelper", "API 26- NotificationChannel is not available");
        }
    }

    //Push a notification
    public void pushNotification(String title,
                                 String content,
                                 int smallIcon,
                                 @ColorInt int color,
                                 PendingIntent pendingIntent,
                                 Bitmap bitmap,
                                 int id)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder builder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                builder = new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(smallIcon)
                        .setContentTitle(title)
                        .setColor(color)
                        .setContentText(content)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
            }
            if (bitmap != null) {
                builder.setStyle(
                        new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap)
                                .bigLargeIcon(null)
                ).setLargeIcon(bitmap);
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(id, builder.build());
        }
        else {
            Log.d("NotificationHelper", "API 26- NotificationChannel is not available");
        }
    }
    public void pushCustomNotification(String title,
                                       String content,
                                       int smallIcon,
                                       @ColorInt int color,
                                       PendingIntent pendingIntent,
                                       Bitmap bitmap,
                                       int id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create a RemoteViews object from your custom layout
            RemoteViews customView = new RemoteViews(context.getPackageName(), R.layout.custom_notification_layout);// Chọn màu bạn muốn
            customView.setInt(R.id.notification_background, "setBackgroundColor", color);
            customView.setImageViewResource(R.id.notification_icon, R.drawable.ic_launcher_foreground);
            customView.setTextViewText(R.id.notification_title, title);
            customView.setTextViewText(R.id.notification_content, content);

            // If you want to handle button clicks, you'd set a PendingIntent to the button here.
            // Example: customView.setOnClickPendingIntent(R.id.notification_action_button, somePendingIntent);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(smallIcon)
                    .setColor(color)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setCustomContentView(customView)  // Set the custom view
                    .setAutoCancel(true);

            if (bitmap != null) {
                builder.setStyle(
                        new NotificationCompat.BigPictureStyle()
                                .bigPicture(bitmap)
                                .bigLargeIcon(null)
                ).setLargeIcon(bitmap);
            }

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(id, builder.build());
        }
        else {
            Log.d("NotificationHelper", "API 26- NotificationChannel is not available");
        }
    }
}
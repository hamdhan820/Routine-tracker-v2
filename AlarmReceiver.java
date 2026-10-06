package com.mydaily.tracker;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context c, Intent in) {
    String t = in.getStringExtra("t"), b = in.getStringExtra("b");
    int id = in.getIntExtra("id", 1);
    NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
    if (Build.VERSION.SDK_INT >= 26) {
      nm.createNotificationChannel(new NotificationChannel("alarm", "Alarms", NotificationManager.IMPORTANCE_HIGH));
    }
    Intent ai = new Intent(c, AlarmActivity.class);
    ai.putExtra("t", t); ai.putExtra("b", b);
    ai.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent p = PendingIntent.getActivity(c, id, ai, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
    Notification.Builder nb = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(c, "alarm") : new Notification.Builder(c);
    nb.setSmallIcon(android.R.drawable.ic_lock_idle_alarm).setContentTitle(t).setContentText(b)
      .setCategory(Notification.CATEGORY_ALARM).setPriority(Notification.PRIORITY_MAX)
      .setFullScreenIntent(p, true).setContentIntent(p).setAutoCancel(true);
    nm.notify(id, nb.build());
  }
}

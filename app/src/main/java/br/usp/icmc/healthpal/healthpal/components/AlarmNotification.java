package br.usp.icmc.healthpal.healthpal.components;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import br.usp.icmc.healthpal.healthpal.AlarmRaiseActivity;
import br.usp.icmc.healthpal.healthpal.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmNotification {

    private NotificationManager notificationManager;

    public void push(Context context, int id) {
        Intent intent = new Intent(context, AlarmRaiseActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("BROOOO")
                .setContentText("FAZ SOL")
                .setSmallIcon(R.drawable.fa_capsules)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.fa_capsules, "RAFFA MOREIRA MANO", pIntent)
                .build();

        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }

    public void pop(int id) {
        notificationManager.cancel(id);
    }
}

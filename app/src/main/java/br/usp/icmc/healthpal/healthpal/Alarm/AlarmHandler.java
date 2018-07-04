package br.usp.icmc.healthpal.healthpal.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlarmHandler {
    Context context;
    AlarmManager manager;

    public AlarmHandler(Context context) {
        this.context = context;
        this.manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setAlarm(long triggerIn, int intervalHours, int code, Class handler) {
        Bundle data = new Bundle();
        this.setAlarm(triggerIn, intervalHours, code, handler, data);
    }

    public void setAlarm(long triggerIn, int intervalHours, int code, Class handler, Bundle data) {
        Intent intent = new Intent(context, handler);
        intent.putExtras(data);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, code, intent, 0);
        this.manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                triggerIn,
                AlarmManager.INTERVAL_HOUR * intervalHours,
                pendingIntent
        );
    }
}

package br.usp.icmc.healthpal.healthpal.Alarm;

import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;

public class AlarmHandler {
    Context context;
    JobManager manager;

    public AlarmHandler(Context context) {
        this.manager = JobManager.create(context);
        this.scheduleJob(tag -> null);
    }

    public void scheduleJob(JobCreator job) {
        this.manager.addJobCreator(job);
    }
}

package br.usp.icmc.healthpal.healthpal.Alarm;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

public class MedicationJob extends Job {

    public static final String TAG = "MEDICATION_JOB";

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        return null;
    }

    public static JobCreator getCreator() {
        return tag -> {
            switch (tag) {
                case TAG:
                    return new MedicationJob();
                default:
                    return null;
            }
        };
    }
}

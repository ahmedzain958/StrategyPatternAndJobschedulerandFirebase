package com.mobiledoctors24.rxaffectsui.jobSCheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.mobiledoctors24.rxaffectsui.database.FirebaseDbToRoomDataUpdateTask;

public class DbUpdateJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        FirebaseDbToRoomDataUpdateTask dbUpdateTask = new FirebaseDbToRoomDataUpdateTask();
        dbUpdateTask.getCouponsFromFirebaseUpdateLocalDb(this);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

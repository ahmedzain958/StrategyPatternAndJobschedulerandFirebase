package com.mobiledoctors24.rxaffectsui.jobSCheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobiledoctors24.rxaffectsui.R;

public class JobSchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);
        scheduleJob();
    }

    private void scheduleJob() {
        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(this);

        if (!preferences.getBoolean("firstRunComplete", false)) {
            //schedule the job only once.
            scheduleJobFirebaseToRoomDataUpdate();

            //update shared preference
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstRunComplete", true);
            editor.commit();
        }
    }

    private void scheduleJobFirebaseToRoomDataUpdate() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(this, DbUpdateJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(86400000).setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING)
                .setPersisted(true).build();
        jobScheduler.schedule(jobInfo);
    }
}

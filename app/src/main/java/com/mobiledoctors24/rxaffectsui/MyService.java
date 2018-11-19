package com.mobiledoctors24.rxaffectsui;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MyService extends Service {
    static PublishSubject<String> data = PublishSubject.create();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        data.onNext("Hello");

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.onNext("" + i);
        }
        data.onComplete();
        return START_STICKY;
    }

    public static Observable<String> getObservable() {
        return data;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
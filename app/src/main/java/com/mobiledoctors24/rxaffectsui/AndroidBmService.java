package com.mobiledoctors24.rxaffectsui;


import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class AndroidBmService extends Service implements BmService {
    private static final int PRESSURE_RATE = 1000;   // microseconds between pressure updates
    private SensorManager sensorManager;
    private SensorEventListener pressureListener;
    private ObservableEmitter<Float> pressureObserver;
    private Observable<Float> pressureObservable;
    private LocalBroadcastManager broadcaster;
    static int counter=0;


    public class LocalBinder extends Binder {
        public AndroidBmService getService() {
            return AndroidBmService.this;
        }
    }

    private IBinder binder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service status", "Service bound");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        sendResult("zaincoooo");

        return START_NOT_STICKY;
    }
    static final public String COPA_RESULT = "com.controlj.copame.backend.COPAService.REQUEST_PROCESSED";

    static final public String COPA_MESSAGE = "com.controlj.copame.backend.COPAService.COPA_MSG";

    public void sendResult(String message) {
        Intent intent = new Intent(COPA_RESULT);
        if (message != null)
            intent.putExtra(COPA_MESSAGE, message);
        broadcaster.sendBroadcast(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        broadcaster = LocalBroadcastManager.getInstance(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (pressureSensor != null)
            sensorManager.registerListener(pressureListener = new SensorEventListener() {


                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (pressureObserver != null) {
                        float lastPressure = event.values[0];
                        float lastPressureAltitude = (float) ((1 - Math.pow(lastPressure / 1013.25, 0.190284)) * 145366.45);
                        pressureObserver.onNext(lastPressureAltitude);
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            }, pressureSensor, PRESSURE_RATE);
        counter++;
        sendResult(String.valueOf(counter));
    }


    @Override
    public Observable<Float> observePressure() {
        if (pressureObservable == null) {
            pressureObservable = Observable.create(emitter -> pressureObserver = emitter);
            pressureObservable = pressureObservable.share();
        }
        return pressureObservable;
    }

    @Override
    public void onDestroy() {
        if (pressureListener != null)
            sensorManager.unregisterListener(pressureListener);
    }
}
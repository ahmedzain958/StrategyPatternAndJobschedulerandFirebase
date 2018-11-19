package com.mobiledoctors24.rxaffectsui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CounterActivity extends AppCompatActivity {

    private static final String TAG = "counter";
    private TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        text_view = findViewById(R.id.text_view);
        Intent intentService = new Intent(CounterActivity.this,MyService.class);
        startService(intentService);
        io.reactivex.Observable<String> observable = MyService.getObservable();
        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getFirstObserver());

        /*counterObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        text_view.setText("\n" + s + "\n");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    private Observer<String>  getFirstObserver() {
        return new Observer<String> () {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onCompleted: ");

            }


            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onNext(String text) {
                Log.d(TAG, "DATA reveived here: " + text);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text_view.setText(text);
                    }
                });
            }
        };
    }

    /*io.reactivex.Observable<String> counterObservable() {
        return io.reactivex.Observable.create(subsriber -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subsriber.onNext("" + i);
            }
            subsriber.onComplete();
        });
    }*/
}

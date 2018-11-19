package com.mobiledoctors24.rxaffectsui.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

public class FirebaseDbToRoomDataUpdateTask {

    FirebaseFirestore firebaseFirestore;
    private CouponsDb couponsDb;
    private TaskExecutor taskExecutor;

    public FirebaseDbToRoomDataUpdateTask() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        taskExecutor = new TaskExecutor();
    }

    public void getCouponsFromFirebaseUpdateLocalDb(final Context ctx) {
        firebaseFirestore.collection("coupons")
                .whereEqualTo("createDt", getTodaysDate())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Coupon> couponList = task.getResult().toObjects(Coupon.class);
                            taskExecutor.execute(new RoomUpdateTask(couponList, ctx));
                        } else {
                            Toast.makeText(ctx, "FIREBASE Error getting documents: task.getException()", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public class TaskExecutor implements Executor {
        @Override
        public void execute(@NonNull Runnable runnable) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }

    public class RoomUpdateTask implements Runnable {
        private List<Coupon> cpnList;
        private Context context;

        public RoomUpdateTask(List<Coupon> cpnListIn, Context ctx) {
            cpnList = cpnListIn;
            context = ctx;
        }

        @Override
        public void run() {
            insertLatestCouponsIntoLocalDb(cpnList, context);
        }


    }

    private void insertLatestCouponsIntoLocalDb(List<Coupon> cpnList, Context context) {
        couponsDb = Room.databaseBuilder(context, CouponsDb.class, "coupons db").build();
        couponsDb.couponsDb().insertCoupons(cpnList);
        couponsDb.couponsDb().deleteCoupons(getTodaysDate());
        Log.d("ROOM", "local database update complete");

        Log.d("ROOM", "number of local records " +
                couponsDb.couponsDb().getCoupons().size());
    }

    private String getTodaysDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = Calendar.getInstance().getTime();
        String today = simpleDateFormat.format(date);
        return today;
    }
}

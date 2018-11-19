package com.mobiledoctors24.rxaffectsui.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Coupon.class}, version = 1)
public abstract class CouponsDb extends RoomDatabase {
    public abstract CouponsDAO  couponsDb();
}

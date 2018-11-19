package com.mobiledoctors24.rxaffectsui.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CouponsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCoupons(List<Coupon> coupons);

    @Query("DELETE From coupon where expiryDt= :expiryDtIn")
    public void deleteCoupons(String expiryDtIn);

    @Query("Select * from  Coupon")
    public List<Coupon> getCoupons();
}

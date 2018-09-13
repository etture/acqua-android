package com.jinoolee.acquaandroid.model.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface TokenDataDao {
    @Query("SELECT token FROM tokenData LIMIT 1")
    fun getToken(): Flowable<String>

    @Query("DELETE FROM tokenData")
    fun deleteToken()
}
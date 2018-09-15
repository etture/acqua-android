package com.jinoolee.acquaandroid.model.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.jinoolee.acquaandroid.model.room.entity.TokenData
import io.reactivex.Flowable

@Dao
interface TokenDataDao {
    @Query("SELECT token FROM tokenData LIMIT 1")
    fun getToken(): Flowable<String>

    @Insert(onConflict = REPLACE)
    fun insertToken(token: TokenData)

    @Query("DELETE FROM tokenData")
    fun deleteToken()
}
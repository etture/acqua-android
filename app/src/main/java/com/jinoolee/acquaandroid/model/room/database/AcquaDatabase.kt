package com.jinoolee.acquaandroid.model.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jinoolee.acquaandroid.model.room.dao.TokenDataDao
import com.jinoolee.acquaandroid.model.room.entity.TokenData

@Database(entities = arrayOf(TokenData::class), version = 1, exportSchema = false)
abstract class AcquaDatabase: RoomDatabase() {
    abstract fun tokenDataDao(): TokenDataDao

    companion object {
        private var INSTANCE: AcquaDatabase? = null

        fun getInstance(context: Context): AcquaDatabase? {
            if(INSTANCE == null) {
                synchronized(AcquaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AcquaDatabase::class.java, "acqua.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
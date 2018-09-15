package com.jinoolee.acquaandroid.model.room.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jinoolee.acquaandroid.model.room.dao.TokenDataDao
import com.jinoolee.acquaandroid.model.room.entity.TokenData

@Database(entities = arrayOf(TokenData::class), version = 1, exportSchema = false)
abstract class TokenDatabase: RoomDatabase() {
    abstract fun tokenDataDao(): TokenDataDao

    companion object {
        private var INSTANCE: TokenDatabase? = null

        fun getInstance(context: Context): TokenDatabase? {
            if(INSTANCE == null) {
                synchronized(TokenDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            TokenDatabase::class.java, "token.db")
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
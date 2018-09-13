package com.jinoolee.acquaandroid.model.room.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tokenData")
data class TokenData (
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "token") var token: String
)
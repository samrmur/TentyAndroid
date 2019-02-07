package com.tenty.tentyandroid.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "calls",
    primaryKeys = ["date", "contact_type"]
)
data class Spam(
    val date: Long,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    @ColumnInfo(name = "contact_type") val contactType: String
)
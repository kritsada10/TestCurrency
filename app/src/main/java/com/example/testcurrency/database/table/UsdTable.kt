package com.example.testcurrency.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_usd")
data class UsdTable(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "code_name")
    var code_Usd: String,

    @ColumnInfo(name = "rate_prize")
    var rate_Usd: String,

    @ColumnInfo(name = "date_time")
    var date_Usd: String

)
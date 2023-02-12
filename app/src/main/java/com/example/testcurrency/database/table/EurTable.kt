package com.example.testcurrency.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_eur")
data class EurTable (

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "code_name")
    var code_Eur: String,

    @ColumnInfo(name = "rate_prize")
    var rate_Eur: String,

    @ColumnInfo(name = "date_time")
    var date_Eur: String

)
package com.example.testcurrency.database.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "currency_pond")
data class PondTable(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "code_name")
    var code_pond: String,

    @ColumnInfo(name = "rate_prize")
    var rate_pond: String,

    @ColumnInfo(name = "date_time")
    var date_pond: String

)
package com.example.testcurrency.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testcurrency.database.dao.EurDao
import com.example.testcurrency.database.dao.PondDao
import com.example.testcurrency.database.dao.UsdDao
import com.example.testcurrency.database.table.EurTable
import com.example.testcurrency.database.table.PondTable
import com.example.testcurrency.database.table.UsdTable

@Database(entities = [EurTable::class, PondTable::class, UsdTable::class], version = 1, exportSchema = false)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun eurDataBase(): EurDao

    abstract fun pondDataBase(): PondDao

    abstract fun usdDataBase(): UsdDao

}
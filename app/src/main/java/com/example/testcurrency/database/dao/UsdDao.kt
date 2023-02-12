package com.example.testcurrency.database.dao

import androidx.room.*
import com.example.testcurrency.database.table.UsdTable

@Dao
interface UsdDao {

    @Insert
    suspend fun insertUsd(usdTable: UsdTable)

    @Query("SELECT * FROM currency_usd")
    fun getAllUsd(): List<UsdTable>

    @Update
    suspend fun updateUsd(usdTable: UsdTable)

    @Delete
    suspend fun deleteUsd(usdTable: UsdTable)

}
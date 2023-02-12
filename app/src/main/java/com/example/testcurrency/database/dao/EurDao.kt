package com.example.testcurrency.database.dao

import androidx.room.*
import com.example.testcurrency.database.table.EurTable

@Dao
interface EurDao {

    @Insert
    suspend fun insertEur(eurTable: EurTable)

    @Query("SELECT * FROM currency_eur")
    fun getAllEur(): List<EurTable>

    @Update
    suspend fun updateEur(eurTable: EurTable)

    @Delete
    suspend fun deleteEur(eurTable: EurTable)

}
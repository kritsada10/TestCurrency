package com.example.testcurrency.database.dao

import androidx.room.*
import com.example.testcurrency.database.table.PondTable

@Dao
interface PondDao {

    @Insert
    suspend fun insertGbp(pondTable: PondTable)

    @Query("SELECT * FROM currency_pond")
    fun getAllGbp(): List<PondTable>

    @Update
    suspend fun updateGbp(pondTable: PondTable)

    @Delete
    suspend fun deleteGbp(pondTable: PondTable)

}
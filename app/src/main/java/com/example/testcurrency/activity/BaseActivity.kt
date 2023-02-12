package com.example.testcurrency.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.testcurrency.callback.MainInterface
import com.example.testcurrency.database.HistoryDatabase
import com.example.testcurrency.database.dao.EurDao
import com.example.testcurrency.database.dao.PondDao
import com.example.testcurrency.database.dao.UsdDao

open class BaseActivity: AppCompatActivity(), MainInterface {

    companion object {
        lateinit var eurInterface: EurDao

        lateinit var pondInterface: PondDao

        lateinit var usdInterface: UsdDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initDb()
    }

    private fun initDb(){
        val db = Room.databaseBuilder(applicationContext, HistoryDatabase::class.java,"currency_database").allowMainThreadQueries().build()
        eurInterface = db.eurDataBase()
        pondInterface = db.pondDataBase()
        usdInterface = db.usdDataBase()
    }

}
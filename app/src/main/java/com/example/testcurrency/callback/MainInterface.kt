package com.example.testcurrency.callback

import com.example.testcurrency.database.table.EurTable
import com.example.testcurrency.database.table.PondTable
import com.example.testcurrency.database.table.UsdTable
import com.example.testcurrency.model.Sample

interface MainInterface {

    fun updateData(){}

    fun pauseLooper(){}

    fun onGetEurHistory(callBack: (listDB : List<EurTable>) -> Unit){}

    fun onGetGbpHistory(callBack: (listDB : List<PondTable>) -> Unit){}

    fun onGetUsdHistory(callBack: (listDB : List<UsdTable>) -> Unit){}

}
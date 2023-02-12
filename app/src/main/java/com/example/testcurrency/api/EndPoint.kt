package com.example.testcurrency.api

import com.example.testcurrency.model.Sample
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {

    @GET("v1/bpi/currentprice.json")
    fun getDataCurrency() : Call<Sample>

}
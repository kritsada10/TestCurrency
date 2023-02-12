package com.example.testcurrency.api

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ApiClient {

    lateinit var context: Context

    val API_DEFAULT: EndPoint
        get() {
            return RetrofitBuilder().build()
        }

}
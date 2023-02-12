package com.example.testcurrency.api

import android.util.Log
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class BaseResponse {

    inline fun <reified T> callApi(call: Call<T>, callbackResponse: CallbackResponse<T>) {
        call.enqueue(object : retrofit2.Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                callbackResponse.onErrorLoading("Error Connection Failed")
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == 200) {
                    Log.i("TAG", response.body().toString())
                    callbackResponse.onSuccess(data = response.body()!!)
                } else {
                    try {
                        callbackResponse.onErrorLoading("Error" + response.code().toString())
                    } catch (_: Exception) {

                    }
                }
            }

        })
    }

    interface CallbackResponse<T> {
        fun onSuccess(data: T)
        fun onFail(data: T?)
        fun onStartLoading() {}
        fun onFinishLoading() {}
        fun onErrorLoading(errorMsg: String?) {}
    }

}
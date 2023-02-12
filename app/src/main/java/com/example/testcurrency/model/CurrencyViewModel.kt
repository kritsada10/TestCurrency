package com.example.testcurrency.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcurrency.api.ApiClient
import com.example.testcurrency.api.BaseResponse
import com.example.testcurrency.callback.MainInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class CurrencyViewModel : ViewModel(), MainInterface {

    private var onNotRealtime = true

    private var diposable: Disposable? = null

    private var callBack: MainInterface? = null

    var resultValue = MutableLiveData<Sample>()

    fun callApi() {
        if (onNotRealtime) {
            restCallApi()
        } else {
            createNewData()
        }

    }

    @SuppressLint("CheckResult")
    private fun createNewData() {

        diposable = Observable.interval(60L, TimeUnit.SECONDS)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onNotRealtime = true
                callApi()
                diposable?.dispose()
            }

    }

    private fun restCallApi() {
        val call = ApiClient.API_DEFAULT.getDataCurrency()
        BaseResponse().callApi(call, object : BaseResponse.CallbackResponse<Sample> {
            override fun onSuccess(data: Sample) {
                onNotRealtime = false
                resultValue.value = data
                callBack?.updateData()
                callApi()
            }

            override fun onFail(data: Sample?) {

            }

        })
    }

    fun clear() {
        resultValue = MutableLiveData<Sample>()
    }

    fun setListener(callBack: MainInterface) {
        this.callBack = callBack
    }

    fun pauseLooperDis() {
        diposable?.dispose()
    }

    fun restartLooper() {
        callApi()
    }

}
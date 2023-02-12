package com.example.testcurrency.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testcurrency.R
import com.example.testcurrency.callback.MainInterface
import com.example.testcurrency.database.table.EurTable
import com.example.testcurrency.database.table.PondTable
import com.example.testcurrency.database.table.UsdTable
import com.example.testcurrency.displayCurrencyFormat
import com.example.testcurrency.model.CurrencyViewModel
import com.example.testcurrency.model.Sample
import com.example.testcurrency.setupColor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : BaseActivity(), MainInterface {

    private lateinit var currencyModel : CurrencyViewModel

    private var keepDataEur = 0.0f

    private var keepDataGbp = 0.0f

    private var keepDataUsd = 0.0f

    private var listener: MainInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDataMain()
        listener = this
        coordinatorLayout.float_center.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        button_fiban.setOnClickListener {
            logicFibonacci()
        }
    }

    private fun initDataMain(){
        currencyModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        currencyModel.setListener(this)
        currencyModel.callApi()
        val adapterDropdown = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(
            R.array.currency
        ))
        spinner_currency.adapter = adapterDropdown
    }

    private fun spinClick(){
        spinner_currency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                conditionCurrency(insertReq.text.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun textAutoCal(){
        insertReq.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                conditionCurrency(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    override fun updateData() {
        currencyModel.resultValue.observe(this) {
            insertDB(it)
            setupColorText(it.bpi.EUR.rate_float,it.bpi.GBP.rate_float,it.bpi.USD.rate_float)
            keepDataEur = it.bpi.EUR.rate_float
            keepDataGbp = it.bpi.GBP.rate_float
            keepDataUsd = it.bpi.USD.rate_float
            value_btcEur.text = it.bpi.EUR.rate_float.displayCurrencyFormat()
            value_btcPond.text = it.bpi.GBP.rate_float.displayCurrencyFormat()
            value_btcUsd.text = it.bpi.USD.rate_float.displayCurrencyFormat()
            if(insertReq.text.toString().isNotEmpty() && insertReq.text.toString() != "0"){
                conditionCurrency(insertReq.text.toString())
            }
        }
        textAutoCal()
        spinClick()
        currencyModel.clear()
    }

    private fun setupColorText(eurRate: Float, gbpRate: Float, usdRate: Float){
        value_btcEur.setupColor(keepDataEur, eurRate)
        value_btcPond.setupColor(keepDataGbp, gbpRate)
        value_btcUsd.setupColor(keepDataUsd, usdRate)
    }

    private fun conditionCurrency(insertData : String?){
        when (spinner_currency.selectedItem.toString()) {
            "EUR" -> {
                calculateResult(insertData.toString(), keepDataEur)
            }
            "GBP" -> {
                calculateResult(insertData.toString(), keepDataGbp)
            }
            "USD" -> {
                calculateResult(insertData.toString(), keepDataUsd)
            }

        }
    }

    private fun calculateResult(insertData : String?, typeSymbol : Float){
        if(insertData!!.isNotEmpty() && insertData != "0"){
            val calculate = insertData.toFloat() * typeSymbol
            val exchange = calculate.displayCurrencyFormat()
            insertReq_currency.text = exchange
        }
        else{
            insertReq_currency.text = ""
        }
    }

    private fun insertDB(data: Sample) {
        lifecycleScope.launch(Dispatchers.IO) {
            eurInterface.insertEur(EurTable(0,data.bpi.EUR.code,data.bpi.EUR.rate_float.displayCurrencyFormat(), data.time.updated))
            pondInterface.insertGbp(PondTable(0,data.bpi.GBP.code,data.bpi.GBP.rate_float.displayCurrencyFormat(), data.time.updated))
            usdInterface.insertUsd(UsdTable(0,data.bpi.USD.code,data.bpi.USD.rate_float.displayCurrencyFormat(), data.time.updated))
        }
    }

    private fun logicFibonacci() {
        val n = 9
        var t1 = 0
        var t2 = 1

        Log.d("FibonacciPrint", "First $n terms: ")
        for (i in 1..n) {
            Log.d("FibonacciPrint", "$t1 + ")
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }
    }

    override fun onPause() {
        super.onPause()
        currencyModel.pauseLooperDis()
    }

    override fun onRestart() {
        super.onRestart()
        currencyModel.restartLooper()
    }


}
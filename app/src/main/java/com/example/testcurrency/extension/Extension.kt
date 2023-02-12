package com.example.testcurrency

import android.graphics.Color
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

fun TextView.setupColor(currentValue: Float, updateValue: Float){

    if(currentValue != 0.0f){
        if(currentValue < updateValue){
            setTextColor(Color.GREEN)
        }
        else{
            setTextColor(Color.RED)
        }
    }

}

fun Float.displayCurrencyFormat(): String {
    val df = DecimalFormat("#,##0.00")
    return df.format(this)
}
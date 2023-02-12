package com.example.testcurrency.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrency.R
import com.example.testcurrency.database.table.UsdTable
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryUsdAdapter : RecyclerView.Adapter<HistoryUsdAdapter.HistoryUsdHolder>() {

    private var historyData: List<UsdTable> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryUsdValue(dataList: List<UsdTable>) {
        this.historyData = dataList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryUsdHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryUsdHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryUsdHolder, position: Int) {
        holder.bindLayoutUsdHistory(historyData[position])
    }

    override fun getItemCount(): Int {
        return historyData.size
    }


    class HistoryUsdHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindLayoutUsdHistory(resultHistory: UsdTable) {
            view.noId_history.text = resultHistory.id.toString()
            view.code_history.text = resultHistory.code_Usd
            view.currency_history.text = resultHistory.rate_Usd
            view.date_history.text = resultHistory.date_Usd
        }

    }

}
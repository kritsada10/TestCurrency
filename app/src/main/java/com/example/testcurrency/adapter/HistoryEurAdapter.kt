package com.example.testcurrency.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrency.R
import com.example.testcurrency.database.table.EurTable
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryEurAdapter : RecyclerView.Adapter<HistoryEurAdapter.HistoryEurHolder>() {

    private var historyData: List<EurTable> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryEurValue(dataList: List<EurTable>) {
        this.historyData = dataList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryEurHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryEurHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryEurHolder, position: Int) {
        holder.bindLayoutEurHistory(historyData[position])
    }

    override fun getItemCount(): Int {
        return historyData.size
    }

    class HistoryEurHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindLayoutEurHistory(resultHistory: EurTable) {
            view.noId_history.text = resultHistory.id.toString()
            view.code_history.text = resultHistory.code_Eur
            view.currency_history.text = resultHistory.rate_Eur
            view.date_history.text = resultHistory.date_Eur
        }

    }

}

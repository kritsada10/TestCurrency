package com.example.testcurrency.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcurrency.R
import com.example.testcurrency.database.table.PondTable
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryGbpAdapter : RecyclerView.Adapter<HistoryGbpAdapter.HistoryGbpHolder>() {

    private var historyData: List<PondTable> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setHistoryGbpValue(dataList: List<PondTable>) {
        this.historyData = dataList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryGbpHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryGbpHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryGbpHolder, position: Int) {
        holder.bindLayoutGbpHistory(historyData[position])
    }

    override fun getItemCount(): Int {
        return historyData.size
    }


    class HistoryGbpHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindLayoutGbpHistory(resultHistory: PondTable) {
            view.noId_history.text = resultHistory.id.toString()
            view.code_history.text = resultHistory.code_pond
            view.currency_history.text = resultHistory.rate_pond
            view.date_history.text = resultHistory.date_pond
        }

    }

}
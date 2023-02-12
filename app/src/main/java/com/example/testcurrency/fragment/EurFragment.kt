package com.example.testcurrency.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcurrency.adapter.HistoryEurAdapter
import com.example.testcurrency.R
import com.example.testcurrency.callback.MainInterface
import kotlinx.android.synthetic.main.fragment_eurhis.*

class EurFragment : Fragment() {

    private var listener: MainInterface? = null

    private var historyEurAdapter = HistoryEurAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_eurhis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener?.onGetEurHistory {
            historyEurAdapter.setHistoryEurValue(it)
        }
        recyclerviewHistory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = historyEurAdapter
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainInterface) listener = context
        else throw RuntimeException("$context must implement OnMenuListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


}
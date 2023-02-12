package com.example.testcurrency.activity

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.testcurrency.R
import com.example.testcurrency.adapter.TabAdapter
import com.example.testcurrency.callback.MainInterface
import com.example.testcurrency.database.table.EurTable
import com.example.testcurrency.database.table.PondTable
import com.example.testcurrency.database.table.UsdTable
import com.example.testcurrency.fragment.EurFragment
import com.example.testcurrency.fragment.GbpFragment
import com.example.testcurrency.fragment.UsdFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.widget_tabdialog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryActivity : BaseActivity(), MainInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_tabdialog)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(supportFragmentManager)
        adapter.addFrag(EurFragment(), "EUR")
        adapter.addFrag(GbpFragment(), "GBP")
        adapter.addFrag(UsdFragment(), "USD")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onGetEurHistory(callBack: (listDB: List<EurTable>) -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            val getHistory = eurInterface.getAllEur()
            callBack.invoke(getHistory)
        }
    }

    override fun onGetGbpHistory(callBack: (listDB: List<PondTable>) -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            val getHistory = pondInterface.getAllGbp()
            callBack.invoke(getHistory)
        }
    }

    override fun onGetUsdHistory(callBack: (listDB: List<UsdTable>) -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            val getHistory = usdInterface.getAllUsd()
            callBack.invoke(getHistory)
        }
    }
}
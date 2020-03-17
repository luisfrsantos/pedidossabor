package com.opluss.pedidossabor.order.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.opluss.pedidossabor.R

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class OrderActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by inject()
    private val orderAdapter: OrderAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyrcler_view_order_activity.adapter = orderAdapter
        BottomSheetBehavior.from(bottomSheet).apply {
            isHideable = false
        }

        viewModel.findByMonth()
        viewModel.getOrder().observe(this, Observer { orderView ->
            orderAdapter.orderList.removeAll(orderAdapter.orderList)
            orderAdapter.orderList = orderView.orderList
            tv_total_value_order_activity.text = orderView.total
            tv_total_pay_value_order_activity.text = orderView.totalPaid
            orderAdapter.notifyDataSetChanged()
        })

        add_order.setOnClickListener {
            startActivity(Intent(applicationContext, SaveOrUpdateOrderActivity::class.java))
        }

        iv_icon_search_order_activity.setOnClickListener {
            startActivityForResult(Intent(applicationContext, SearchActivity::class.java), REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (resultCode) {
            Activity.RESULT_OK -> {
                if (requestCode == REQUEST_CODE) {
                    viewModel.findByName(data!!.extras!!.get("name").toString())
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val REQUEST_CODE = 1001
    }
}

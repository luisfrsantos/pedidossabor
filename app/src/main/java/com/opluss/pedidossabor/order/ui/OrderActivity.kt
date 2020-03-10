package com.opluss.pedidossabor.order.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.opluss.pedidossabor.R

import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class OrderActivity : AppCompatActivity() {

    private val viewModel: OrderViewModel by inject()
    private val orderAdapter: OrderAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyrcler_view_order_activity.adapter = orderAdapter

        viewModel.findByMond()
        viewModel.getOrderList().observe(this, Observer { orderList ->
            orderAdapter.orderList = orderList
            orderAdapter.notifyDataSetChanged()
        })

        add_order.setOnClickListener {
            startActivity(Intent(applicationContext, AddOrderActivity::class.java))
        }
    }
}

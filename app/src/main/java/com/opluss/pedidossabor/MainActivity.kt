package com.opluss.pedidossabor

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.opluss.pedidossabor.order.AddOrderActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        add_order.setOnClickListener {
            startActivity(Intent(applicationContext, AddOrderActivity::class.java))
        }
    }

}

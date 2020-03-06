package com.opluss.pedidossabor.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.opluss.pedidossabor.R
import kotlinx.android.synthetic.main.activity_main.*

class AddOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        setSupportActionBar(toolbar)
    }
}

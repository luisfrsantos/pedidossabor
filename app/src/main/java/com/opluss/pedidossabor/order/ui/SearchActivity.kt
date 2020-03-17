package com.opluss.pedidossabor.order.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.opluss.pedidossabor.R
import kotlinx.android.synthetic.main.activity_search.button_apply_activity_search
import kotlinx.android.synthetic.main.activity_search.text_input_name_activity_search

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        button_apply_activity_search.setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", text_input_name_activity_search.text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}

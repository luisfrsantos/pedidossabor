package com.opluss.pedidossabor.order.ui

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.opluss.pedidossabor.R
import com.opluss.pedidossabor.commons.helper.BR_PATTERN_DISPLAY
import com.opluss.pedidossabor.commons.helper.DateHelper
import com.opluss.pedidossabor.commons.helper.DateHelper.now
import com.opluss.pedidossabor.commons.repository.BaseRepository.State.ERROR
import com.opluss.pedidossabor.commons.repository.BaseRepository.State.LOADING
import com.opluss.pedidossabor.commons.repository.BaseRepository.State.SUCCESS
import com.opluss.pedidossabor.order.model.Customer
import com.opluss.pedidossabor.order.model.Order
import com.opluss.pedidossabor.order.model.Product
import kotlinx.android.synthetic.main.activity_add_order.button_save_order
import kotlinx.android.synthetic.main.activity_add_order.switch_pay_day
import kotlinx.android.synthetic.main.activity_add_order.text_input_date
import kotlinx.android.synthetic.main.activity_add_order.text_input_layout_date
import kotlinx.android.synthetic.main.activity_add_order.text_input_layout_name
import kotlinx.android.synthetic.main.activity_add_order.text_input_layout_phone
import kotlinx.android.synthetic.main.activity_add_order.text_input_layout_product
import kotlinx.android.synthetic.main.activity_add_order.text_input_layout_value
import kotlinx.android.synthetic.main.activity_add_order.text_input_name
import kotlinx.android.synthetic.main.activity_add_order.text_input_phone
import kotlinx.android.synthetic.main.activity_add_order.text_input_product
import kotlinx.android.synthetic.main.activity_add_order.text_input_value
import kotlinx.android.synthetic.main.activity_add_order.toolbar
import org.koin.android.ext.android.inject

class AddOrderActivity : AppCompatActivity() {

    private val viewModel: AddOrderViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        text_input_date.setText(now(BR_PATTERN_DISPLAY))
        text_input_phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        button_save_order.setOnClickListener {
            save()
        }

        viewModel.getState().observe(this, Observer { state ->
            when (state!!) {
                LOADING -> {
                }
                SUCCESS -> {
                    AlertDialog.Builder(this)
                        .setTitle("Legal")
                        .setMessage("Pedido salvo com sucesso !")
                        .setPositiveButton(android.R.string.ok) { _, _ ->
                            finish()
                        }.show()
                }
                ERROR -> {
                }
            }
        })
    }

    fun save() {
        if (text_input_name.text.isNullOrEmpty()) {
            text_input_layout_name.error = getString(R.string.field_is_required)
            return
        }
        if (text_input_phone.text.isNullOrBlank()) {
            text_input_layout_phone.error = getString(R.string.field_is_required)
            return
        }

        if (text_input_product.text.isNullOrBlank()) {
            text_input_layout_product.error = getString(R.string.field_is_required)
            return
        }

        if (text_input_value.text.isNullOrBlank()) {
            text_input_layout_value.error = getString(R.string.field_is_required)
            return
        }

        if (text_input_date.text.isNullOrBlank()) {
            text_input_layout_date.error = getString(R.string.field_is_required)
            return
        }

        val customerName = text_input_name.text.toString()
        val customerPhone = text_input_phone.text.toString()
        val productName = text_input_product.text.toString()
        val productValue = text_input_value.text.toString().trim().replace(",", ".").toDouble()
        val date = text_input_date.text.toString()
        val paidDay = switch_pay_day.isChecked
        val product = Product()
        product.name = productName
        product.value = productValue
        val customer = Customer()
        customer.name = customerName
        customer.phone = customerPhone
        val order = Order()
        order.customer = customer
        order.product = product
        order.date = DateHelper.toTimesTamp(date, BR_PATTERN_DISPLAY)
        order.payState = paidDay
        viewModel.save(order)
    }
}

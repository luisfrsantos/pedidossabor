package com.opluss.pedidossabor.order.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.opluss.pedidossabor.R
import com.opluss.pedidossabor.commons.helper.BR_PATTERN_DISPLAY
import com.opluss.pedidossabor.commons.helper.DateHelper.timesTampToString
import com.opluss.pedidossabor.commons.helper.MoneyFormat
import com.opluss.pedidossabor.order.model.Order
import kotlinx.android.synthetic.main.adapter_order.view.customer_name_adpter_order
import kotlinx.android.synthetic.main.adapter_order.view.date_adapter_order
import kotlinx.android.synthetic.main.adapter_order.view.product_name_adpter_order
import kotlinx.android.synthetic.main.adapter_order.view.state_pay_adapter_order
import kotlinx.android.synthetic.main.adapter_order.view.value_adapter_order

class OrderAdapter(private val context: Context) :
    RecyclerView.Adapter<OrderAdapter.ListViewHolder>() {

    var orderList: ArrayList<Order>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_order, parent, false))

    override fun getItemCount(): Int {
        orderList?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        orderList?.apply {
            val order = get(position)
            holder.tvCustomerName.text = order.customer!!.name
            holder.tvProduct.text = order.product!!.name
            holder.tvDate.text = timesTampToString(order.date!!, BR_PATTERN_DISPLAY)
            holder.tvStatePay.text = if (order.payState!!) "Pago" else "Não pago"
            holder.tvValue.text = MoneyFormat.toMoney(order.product!!.value!!)
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCustomerName: TextView = view.customer_name_adpter_order
        var tvProduct: TextView = view.product_name_adpter_order
        var tvDate: TextView = view.date_adapter_order
        var tvStatePay: TextView = view.state_pay_adapter_order
        var tvValue: TextView = view.value_adapter_order
    }
}
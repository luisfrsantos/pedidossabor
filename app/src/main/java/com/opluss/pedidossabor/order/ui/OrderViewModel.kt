package com.opluss.pedidossabor.order.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opluss.pedidossabor.commons.helper.MoneyFormat.toMoney
import com.opluss.pedidossabor.order.model.Order
import com.opluss.pedidossabor.order.model.OrderView
import com.opluss.pedidossabor.order.reposotory.CUSTOMER_NAME_SEARCH
import com.opluss.pedidossabor.order.reposotory.OrderRepository

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    val orderView: MutableLiveData<OrderView> by lazy {
        MutableLiveData<OrderView>()
    }

    fun getOrder(): MutableLiveData<OrderView> {
        orderRepository.orderList.observeForever {
            sumValues(it)
        }

        return orderView
    }

    private fun sumValues(it: ArrayList<Order>) {
        val internalOrderView = OrderView()
        var total = 0.0
        var totalPaid = 0.0
        it.forEach { order ->
            total += order.product!!.value!!
            if (order.payState) {
                totalPaid += order.product!!.value!!
            }
        }
        internalOrderView.orderList = it
        internalOrderView.total = toMoney(total)
        internalOrderView.totalPaid = toMoney(totalPaid)
        orderView.postValue(internalOrderView)
    }

    fun findByMonth() {
        orderRepository.findByLastMonth()
    }

    fun findByName(name: String) {
        orderRepository.findByParam(CUSTOMER_NAME_SEARCH, name)
    }
}
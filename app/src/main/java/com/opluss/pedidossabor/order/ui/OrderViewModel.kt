package com.opluss.pedidossabor.order.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.opluss.pedidossabor.commons.helper.MoneyFormat.toMoney
import com.opluss.pedidossabor.order.model.OrderView
import com.opluss.pedidossabor.order.reposotory.OrderRepository

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    val orderView: MutableLiveData<OrderView> by lazy {
        MutableLiveData<OrderView>()
    }

    fun getOrder(): MutableLiveData<OrderView> {
        orderRepository.orderList.observeForever {
            var internalOrderView = OrderView()
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

        return orderView
    }

    fun findByMonth() {
        orderRepository.findByMond()
    }
}
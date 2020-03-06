package com.opluss.pedidossabor.order.ui

import androidx.lifecycle.ViewModel
import com.opluss.pedidossabor.order.model.Order
import com.opluss.pedidossabor.order.reposotory.OrderRepository

class AddOrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    fun getState() = orderRepository.state

    fun save(data: Order) {
        orderRepository.save(data)
    }
}
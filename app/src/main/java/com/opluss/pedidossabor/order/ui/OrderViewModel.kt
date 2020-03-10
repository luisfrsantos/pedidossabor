package com.opluss.pedidossabor.order.ui

import androidx.lifecycle.ViewModel
import com.opluss.pedidossabor.order.reposotory.OrderRepository

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {

    fun getState() = orderRepository.state

    fun getOrderList() = orderRepository.orderList

    fun findByMond() {
        orderRepository.findByMond()
    }
}
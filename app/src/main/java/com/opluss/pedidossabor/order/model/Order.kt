package com.opluss.pedidossabor.order.model

import java.sql.Timestamp

data class Order(val customer: Customer, val product: Product, val date: Timestamp, val payday: Boolean)
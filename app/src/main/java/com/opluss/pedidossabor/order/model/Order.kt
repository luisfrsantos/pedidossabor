package com.opluss.pedidossabor.order.model

import com.google.firebase.Timestamp

class Order {
    var customer: Customer? = null
    var product: Product? = null
    var date: Timestamp? = null
    var payState: Boolean? = null
    var payday: Boolean? = null
}
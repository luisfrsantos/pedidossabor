package com.opluss.pedidossabor.order.reposotory

import androidx.lifecycle.MutableLiveData
import com.opluss.pedidossabor.commons.repository.BaseRepository
import com.opluss.pedidossabor.order.model.Customer
import com.opluss.pedidossabor.order.model.Order
import java.sql.Timestamp

const val COLLECTION_NAME = "Order"

class OrderRepository : BaseRepository<Order>() {

    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    override fun save(data: Order) {
        db.collection(COLLECTION_NAME)
            .document(createID(data.customer, data.date))
            .set(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    state.postValue(State.SUCCESS)
                } else {
                    state.postValue(State.ERROR)
                }
            }
    }

    override fun findByMond(id: String) {
        db.collection(COLLECTION_NAME)
    }

    private fun createID(customer: Customer, date: Timestamp): String {
        return "${customer.name.capitalize()}$date"
            .replace("\\s".toRegex(), "")
            .replace("/", "")
            .replace(":", "")
    }
}
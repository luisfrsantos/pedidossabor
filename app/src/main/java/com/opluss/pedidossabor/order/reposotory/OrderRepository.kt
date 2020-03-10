package com.opluss.pedidossabor.order.reposotory

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.opluss.pedidossabor.commons.helper.BR_PATTERN_DISPLAY
import com.opluss.pedidossabor.commons.helper.DateHelper
import com.opluss.pedidossabor.commons.helper.DateHelper.toTimesTamp
import com.opluss.pedidossabor.commons.repository.BaseRepository
import com.opluss.pedidossabor.order.model.Customer
import com.opluss.pedidossabor.order.model.Order

const val COLLECTION_NAME = "Order"

class OrderRepository : BaseRepository<Order>() {

    val state: MutableLiveData<State> by lazy {
        MutableLiveData<State>()
    }

    val orderList: MutableLiveData<ArrayList<Order>> by lazy {
        MutableLiveData<ArrayList<Order>>()
    }

    override fun save(data: Order) {
        db.collection(COLLECTION_NAME)
            .document(createID(data.customer!!, data.date!!))
            .set(data)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    state.postValue(State.SUCCESS)
                } else {
                    state.postValue(State.ERROR)
                }
            }
    }

    override fun findByMond() {
        db.collection(COLLECTION_NAME)
            .whereGreaterThan(
                "date",
                toTimesTamp(DateHelper.minusDays(29, BR_PATTERN_DISPLAY), BR_PATTERN_DISPLAY)
            )
            .addSnapshotListener { snapshot, _ ->
                snapshot?.apply {
                    orderList.postValue(toObjects(Order::class.java) as ArrayList<Order>?)
                }
            }
    }

    @SuppressLint("DefaultLocale")
    private fun createID(customer: Customer, date: Timestamp): String {
        return "${customer.name!!.capitalize()}$date"
            .replace("\\s".toRegex(), "")
            .replace("/", "")
            .replace(":", "")
    }
}
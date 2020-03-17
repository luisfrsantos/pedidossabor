package com.opluss.pedidossabor.commons.repository

import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseRepository<T> {

    protected val db = FirebaseFirestore.getInstance()

    abstract fun saveOrUpdate(data: T, old: T? = null)

    abstract fun findByLastMonth()

    abstract fun findByParam(param: String, values: String)

    abstract fun deleteByID(id: String)

    enum class State {
        SUCCESS,
        LOADING,
        ERROR
    }
}
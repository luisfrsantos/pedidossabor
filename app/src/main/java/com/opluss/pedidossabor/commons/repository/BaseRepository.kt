package com.opluss.pedidossabor.commons.repository

import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseRepository<T> {

    protected val db = FirebaseFirestore.getInstance()

    abstract fun save(data: T)

    abstract fun findByMond()

    enum class State {
        SUCCESS,
        LOADING,
        ERROR
    }
}
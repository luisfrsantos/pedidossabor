package com.opluss.pedidossabor.commons.helper

import java.text.NumberFormat
import java.util.Currency

object MoneyFormat {
    const val CURRENCY_CODE = "BRL"

    fun toMoney(value: Double): String {
        val format: NumberFormat = NumberFormat.getCurrencyInstance()
        format.currency = Currency.getInstance(CURRENCY_CODE)

        return format.format(value)
    }
}
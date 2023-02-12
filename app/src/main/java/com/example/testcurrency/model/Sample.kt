package com.example.testcurrency.model

data class Sample(
    val bpi: Bpi,
    val chartName: String,
    val disclaimer: String,
    val time: Time
)

data class Bpi(
    val EUR: EUR,
    val GBP: GBP,
    val USD: USD
)

data class Time(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)

data class EUR(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Float,
    val symbol: String
)

data class GBP(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Float,
    val symbol: String
)

data class USD(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Float,
    val symbol: String
)
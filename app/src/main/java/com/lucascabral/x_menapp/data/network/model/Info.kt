package com.lucascabral.x_menapp.data.network.model

data class Info(
    val count: Int,
    val limit: Int,
    val next: String,
    val page: Int,
    val pageCount: Int,
    val prev: Any
)
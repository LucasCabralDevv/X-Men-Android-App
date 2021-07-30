package com.lucascabral.x_menapp.data.api

import com.lucascabral.x_menapp.data.network.model.XMenResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface XMenService {

    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): XMenResponse
}
package com.example.data.api

import com.example.data.Url
import com.example.data.request.AuthRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Auth {

    @POST(Url.Auth.postCode)
    suspend fun postCode(
        @Body authRequest: AuthRequest,
    ): Void

    @GET(Url.Auth.checkCode)
    suspend fun checkCode(
        @Query("code") code: String,
        @Query("phone-number") phoneNumber: String,
    ): Void
}
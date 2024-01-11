package com.example.data.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("to") val to: String,
)

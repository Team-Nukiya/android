package com.example.data

object Url {

    private const val auth = "v1/auth"

    object Auth {
        const val postCode = "$auth/codes"
        const val checkCode = "$auth/certified"
    }
}
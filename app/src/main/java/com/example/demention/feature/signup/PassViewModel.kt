package com.example.demention.feature.signup

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.ApiProvider
import com.example.data.request.AuthRequest

class PassViewModel : ViewModel() {
    private var _phoneNumber: MutableState<String> = mutableStateOf("")
    private var _code: MutableState<String> = mutableStateOf("")
    val phoneNumber: State<String> get() = _phoneNumber
    val code: State<String> get() = _code

    fun handleUpdatePhoneNumber(str: String) {
        _phoneNumber.value = str
    }

    fun handleUpdateCode(str: String) {
        _code.value = str
    }

    suspend fun postCode(phoneNumber: String) {
        kotlin.runCatching {
            ApiProvider.authApi().postCode(authRequest = AuthRequest(phoneNumber))
        }.onSuccess {
            Log.d("success", "a")
        }.onFailure {
            Log.d("fail", it.toString())
        }
    }

    suspend fun checkCode(code: String, phoneNumber: String) {
        kotlin.runCatching {
            ApiProvider.authApi().checkCode(code = code, phoneNumber = phoneNumber)
        }.onSuccess {
            Log.d("success", "a")
        }.onFailure {
            Log.d("fail", it.toString())
        }
    }
}
package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.data.api.Auth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiProvider {
    /**
     * this is a variable supported when not in json format
     */
    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private lateinit var sharedPreferences: SharedPreferences

    /**
     * this function save token with shared Preference
     * @param context
     */
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    }


    /**
     * this function show http logs
     */
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    /**
     * Returns a configured Retrofit instance for network requests with logging, token authentication,
     * and Gson serialization/deserialization
     * @return Configured Retrofit instance
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * this function puts a token in the header
     * @return interceptor that include header token
     */
    private fun getTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val token = sharedPreferences.getString("token", "") ?: ""
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }

    fun authApi(): Auth = getRetrofit().create(Auth::class.java)
}
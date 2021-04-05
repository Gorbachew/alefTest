package ru.test.alef.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.test.alef.util.Constants.BASE_URL

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}
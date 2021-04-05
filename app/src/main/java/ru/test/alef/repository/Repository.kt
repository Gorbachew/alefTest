package ru.test.alef.repository

import retrofit2.Call
import retrofit2.Response
import ru.test.alef.api.RetrofitInstance

class Repository {

    suspend fun getImages(): Response<List<String>> {
        return RetrofitInstance.api.getImages()
    }


}
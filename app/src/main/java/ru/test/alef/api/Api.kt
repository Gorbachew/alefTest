package ru.test.alef.api

import retrofit2.Response
import retrofit2.http.GET
import ru.test.alef.util.Constants.TASK_GET

interface Api {

    @GET(TASK_GET)
    suspend fun getImages(): Response<List<String>>

}
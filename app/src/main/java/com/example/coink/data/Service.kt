package com.example.coink.data

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @Headers(
        "apiKey: 441252"
    )
    @POST("qa/")
    fun signup(@Body payLoad: String):
            retrofit2.Call<ResponseBody>
}
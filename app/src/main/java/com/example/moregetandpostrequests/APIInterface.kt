package com.example.moregetandpostrequests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {

    @GET("/test/")
    fun getNames(): Call<ArrayList<Name>>

    @POST("/test/")
    fun addName(@Body name: Name): Call<Name>
}
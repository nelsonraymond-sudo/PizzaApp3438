package com.example.pizzaapp3438.client

// FIXED: Remove android.telecom.Call
// import android.telecom.Call
import com.example.pizzaapp3438.response.account.Data
import retrofit2.Call

import com.example.pizzaapp3438.response.account.LoginResponse
import com.example.pizzaapp3438.response.food.FoodResponse
// You likely need to import FoodResponse and AccountResponse here too, e.g.:
// import com.example.pizzaapp3438.response.food.FoodResponse
// import com.example.pizzaapp3438.response.account.AccountResponse

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface Api {
    @GET("food")
    fun getFood(): Call<ArrayList<FoodResponse>>

    @FormUrlEncoded
    @POST("login")
    fun postLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @PUT("account")
    fun putAccount(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("level") level: String,
        @Field("password") password: String
    ): Call<Data>
}

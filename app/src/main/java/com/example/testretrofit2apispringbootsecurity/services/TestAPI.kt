package com.example.testretrofit2apispringbootsecurity.services

import com.example.testretrofit2apispringbootsecurity.models.Customer
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*


interface TestAPI {

    @GET("customers")
    fun showAllCustomer(@Header("Authorization") authHeader: String ):Observable<List<Customer>>

    @POST("customers")
    fun addCustomer(@Header("Authorization") authHeader: String, @Body() customer: Customer):Observable<Customer>

    @DELETE("customers/{id}")
    fun deleteCustomer(@Header("Authorization") authHeader: String, @Path("id") id: Int):Observable<ResponseBody>
}
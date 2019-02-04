package com.example.testretrofit2apispringbootsecurity.models

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("id") val id: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("email") val email: String
)
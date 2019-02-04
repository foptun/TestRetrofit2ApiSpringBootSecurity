package com.example.testretrofit2apispringbootsecurity.models

import com.google.gson.annotations.SerializedName

data class CustomerResponse(

    @SerializedName("") val listCustomer: List<Customer>
)
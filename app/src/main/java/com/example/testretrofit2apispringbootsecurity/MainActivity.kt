package com.example.testretrofit2apispringbootsecurity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.testretrofit2apispringbootsecurity.models.Customer
import com.example.testretrofit2apispringbootsecurity.services.TestAPI
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.4:8081/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtHello.text = "Test Fucking";

        var username : String = "foptun"
        var password : String = "1234"

        var base: String = "${username}:${password}"
        var authHeader: String = "Basic ${Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)}"

        retrofit.create<TestAPI>(TestAPI::class.java)
            .addCustomer(authHeader, customer = Customer(0, "AAA", "bbb", 202, "adminxx@mail.com"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<Customer>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(customer: Customer) {
                    println("FOPTUN ADD CUSTOMER == ${customer}")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                }
            })

        retrofit.create<TestAPI>(TestAPI::class.java)
            .deleteCustomer(authHeader, 13)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ResponseBody>{
                override fun onComplete() {
                    println("FOPTUN DELETE CUSTOMER onComplete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(message: ResponseBody) {
                    println("FOPTUN DELETE CUSTOMER onNext ${message}")
                }

                override fun onError(e: Throwable) {
                    println("@DELETE ERROR ${e}")
                }
            })


        retrofit.create<TestAPI>(TestAPI::class.java)
            .showAllCustomer(authHeader)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<List<Customer>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    // txtHello.text = "Hello D: ${d.toString()}"
                }

                override fun onNext(listCustomer: List<Customer>) {
                    txtHello.text = "Hello: ${listCustomer.get(0).firstName}"

                    println(listCustomer)
//                    for (customer in listCustomer){
//                        println("SHOW ${customer}");
//                    }

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })



    }

}

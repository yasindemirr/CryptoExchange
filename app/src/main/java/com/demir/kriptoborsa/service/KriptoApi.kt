package com.demir.kriptoborsa.service

import com.demir.kriptoborsa.model.KriptoModel
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.http.GET

interface KriptoApi {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Observable<List<KriptoModel>>
    //fun getData():Call<List<KriptoModel>>

}
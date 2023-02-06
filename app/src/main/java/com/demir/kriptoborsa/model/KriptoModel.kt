package com.demir.kriptoborsa.model

import com.google.gson.annotations.SerializedName
import java.util.Currency

data class KriptoModel(
   // @SerializedName("currency")
    val currency:String,
    //@SerializedName("price")
    val price:String
)

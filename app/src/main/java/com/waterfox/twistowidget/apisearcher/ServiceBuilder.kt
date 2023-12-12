package com.waterfox.twistowidget.apisearcher

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder
{
    private const val URL ="https://twisto.opendatasoft.com/api/explore/v2.1/catalog/datasets/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()
    fun <T> buildService (serviceType :Class<T>):T{
        return retrofit.create(serviceType)
    }
}
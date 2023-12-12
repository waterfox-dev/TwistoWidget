package com.waterfox.twistowidget.apisearcher

import com.waterfox.twistowidget.apisearcher.result.StopResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StopService
{
    @GET("base-arret/records")
    fun getStops(
        @Query("where") where: String,
        @Query("limit") limit: Int
    ): Call<StopResult>
}
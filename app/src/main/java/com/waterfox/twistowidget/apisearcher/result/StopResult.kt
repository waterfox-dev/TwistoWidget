package com.waterfox.twistowidget.apisearcher.result

data class StopResult(
    val results: List<StopSpecific>,
    val total_count: Int
)
package com.waterfox.twistowidget.apisearcher.result

data class StopSpecific(
    val location_type: String,
    val parent_station: Any,
    val stop_code: Any,
    val stop_coordinates: StopCoordinates,
    val stop_desc: Any,
    val stop_id: String,
    val stop_name: String,
    val stop_timezone: Any,
    val stop_url: Any,
    val wheelchair_boarding: String,
    val zone_id: Any
)
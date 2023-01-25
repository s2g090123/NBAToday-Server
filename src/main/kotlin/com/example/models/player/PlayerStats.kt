package com.example.models.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerStats(
    @SerialName("resource") val resource: String?,
    @SerialName("parameters") val parameters: Parameter?,
    @SerialName("resultSets") val resultSets: List<Result>?
) {
    @Serializable
    data class Parameter(
        @SerialName("PlayerID") val playerId: Int?,
    )

    @Serializable
    data class Result(
        @SerialName("name") val name: String?,
        @SerialName("headers") val headers: List<String>?,
        @SerialName("rowSet") val rowData: List<List<String>>?
    )
}
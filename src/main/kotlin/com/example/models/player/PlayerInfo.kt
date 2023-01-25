package com.example.models.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerInfo(
    @SerialName("resource") val resource: String?,
    @SerialName("resultSets") val resultSets: List<Result>?
) {
    @Serializable
    data class Result(
        @SerialName("name") val name: String?,
        @SerialName("headers") val headers: List<String>?,
        @SerialName("rowSet") val rowData: List<List<String>>?
    )
}
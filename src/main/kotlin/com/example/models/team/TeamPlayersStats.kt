package com.example.models.team

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamPlayersStats(
    @SerialName("resource") val resource: String?,
    @SerialName("parameters") val parameters: Parameters?,
    @SerialName("resultSets") val data: List<Data>?
) {
    @Serializable
    data class Parameters(
        @SerialName("TeamID") val teamId: Int?
    )

    @Serializable
    data class Data(
        @SerialName("name") val name: String?,
        @SerialName("headers") val headers: List<String>?,
        @SerialName("rowSet") val rowData: List<List<String>>?
    )
}
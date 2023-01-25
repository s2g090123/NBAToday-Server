package com.example.models.team

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamStats(
    @SerialName("resource") val resource: String?,
    @SerialName("resultSets") val data: List<Data>?
) {
    @Serializable
    data class Data(
        @SerialName("name") val name: String?,
        @SerialName("headers") val headers: List<String>?,
        @SerialName("rowSet") val rowData: List<List<String>>?
    )
}
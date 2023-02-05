package com.example.models.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDetail(
    @SerialName("info") val info: PlayerInfo,
    @SerialName("stats") val stats: PlayerStats
)
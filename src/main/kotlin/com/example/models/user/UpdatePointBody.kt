package com.example.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePointBody(
    val account: String?,
    val token: String?,
    val points: Long?
)
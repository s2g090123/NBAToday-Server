package com.example.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePasswordBody(
    val account: String?,
    val token: String?,
    val newPassword: String?
)
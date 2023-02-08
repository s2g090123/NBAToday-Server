package com.example.models.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    val account: String?,
    val password: String?
)
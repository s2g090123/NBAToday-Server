package com.example.models.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val account: String,
    val name: String,
    val points: Long,
    val password: String,
    val token: String,
    val lastLogin: Long
)
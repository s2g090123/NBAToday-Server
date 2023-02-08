package com.example.database.dao

import com.example.models.user.User

interface UserDao {
    suspend fun allUsers(): List<User>
    suspend fun user(account: String): User?
    suspend fun user(account: String, password: String): User?
    suspend fun addUser(user: User): User?
    suspend fun editName(account: String, name: String, token: String): Boolean
    suspend fun editPoints(account: String, points: Long, token: String): Boolean
    suspend fun editToken(account: String, token: String): Boolean
    suspend fun editPassword(account: String, password: String): Boolean
    suspend fun deleteUser(account: String): Boolean
}
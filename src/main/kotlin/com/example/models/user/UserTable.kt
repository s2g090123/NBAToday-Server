package com.example.models.user

import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val account = varchar("account", 128)
    val name = varchar("name", 128)
    val points = long("points")
    val password = varchar("password", 128)
    val token = varchar("token", 128)
    val lastLogin = long("last_login")

    override val primaryKey: PrimaryKey = PrimaryKey(account)
}
package com.example.database.dao

import com.example.database.DatabaseFactory.dbQuery
import com.example.models.user.User
import com.example.models.user.UserTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserDaoImp : UserDao {
    private fun ResultRow.toUser(): User {
        return User(
            account = this[UserTable.account],
            name = this[UserTable.name],
            points = this[UserTable.points],
            password = this[UserTable.password],
            token = this[UserTable.token],
            lastLogin = this[UserTable.lastLogin]
        )
    }

    override suspend fun allUsers(): List<User> = dbQuery {
        UserTable.selectAll().map { it.toUser() }
    }

    override suspend fun user(account: String): User? = dbQuery {
        UserTable
            .select { (UserTable.account eq account) }
            .map { it.toUser() }
            .singleOrNull()
    }

    override suspend fun user(account: String, password: String): User? = dbQuery {
        UserTable
            .select { (UserTable.account eq account) and (UserTable.password eq password) }
            .map { it.toUser() }
            .singleOrNull()
    }

    override suspend fun addUser(user: User) = dbQuery {
        UserTable.insert {
            it[account] = user.account
            it[name] = user.name
            it[points] = user.points
            it[password] = user.password
            it[token] = user.token
            it[lastLogin] = user.lastLogin
        }.resultedValues?.singleOrNull()?.toUser()
    }

    override suspend fun editName(account: String, name: String, token: String): Boolean = dbQuery {
        UserTable
            .update({ (UserTable.account eq account) and (UserTable.token eq token) }) {
                it[UserTable.name] = name
            } > 0
    }

    override suspend fun editPoints(account: String, points: Long, token: String): Boolean = dbQuery {
        UserTable
            .update({ (UserTable.account eq account) and (UserTable.token eq token) }) {
                it[UserTable.points] = points
            } > 0
    }

    override suspend fun editToken(account: String, token: String): Boolean = dbQuery {
        UserTable
            .update({ UserTable.account eq account }) {
                it[UserTable.token] = token
            } > 0
    }

    override suspend fun editPassword(account: String, password: String): Boolean = dbQuery {
        UserTable
            .update({ UserTable.account eq account }) {
                it[UserTable.password] = password
            } > 0
    }

    override suspend fun editLastLogin(account: String, token: String, lastLogin: Long): Boolean = dbQuery {
        UserTable
            .update({ (UserTable.account eq account) and (UserTable.token eq token) }) {
                it[UserTable.lastLogin] = lastLogin
            } > 0
    }

    override suspend fun deleteUser(account: String): Boolean = dbQuery {
        UserTable.deleteWhere { UserTable.account eq account } > 0
    }
}
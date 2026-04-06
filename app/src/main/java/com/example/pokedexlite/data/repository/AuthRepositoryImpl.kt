package com.example.pokedexlite.data.repository

import com.example.pokedexlite.data.local.SessionManager
import com.example.pokedexlite.data.local.dao.UserDao
import com.example.pokedexlite.domain.model.User
import com.example.pokedexlite.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> =
        withContext(Dispatchers.IO) {
            val user = userDao.getUserByUsername(username)
                ?: return@withContext Result.failure(Exception("User not found. Please register first."))

            if (user.password != password) {
                return@withContext Result.failure(Exception("Incorrect password. Please try again."))
            }

            sessionManager.saveSession(username)
            Result.success(user)
        }

    override suspend fun register(username: String, email: String, password: String): Result<User> =
        withContext(Dispatchers.IO) {
            if (userDao.userExists(username)) {
                return@withContext Result.failure(Exception("Username \"$username\" is already taken."))
            }

            val user = User(
                id = System.currentTimeMillis().toString(),
                username = username,
                email = email,
                password = password
            )
            userDao.insertUser(user)
            sessionManager.saveSession(username)
            Result.success(user)
        }

    override suspend fun getLoggedInUser(): User? = withContext(Dispatchers.IO) {
        val username = sessionManager.getLoggedInUsername() ?: return@withContext null
        userDao.getUserByUsername(username)
    }

    override suspend fun logout() {
        sessionManager.clearSession()
    }
}

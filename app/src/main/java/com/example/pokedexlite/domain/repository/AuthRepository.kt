package com.example.pokedexlite.domain.repository

import com.example.pokedexlite.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    suspend fun register(username: String, email: String, password: String): Result<User>
    suspend fun getLoggedInUser(): User?
    suspend fun logout()
}

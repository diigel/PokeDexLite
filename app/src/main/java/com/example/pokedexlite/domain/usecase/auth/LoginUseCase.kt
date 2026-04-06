package com.example.pokedexlite.domain.usecase.auth

import com.example.pokedexlite.domain.model.User
import com.example.pokedexlite.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        if (username.isBlank()) {
            return Result.failure(IllegalArgumentException("Username cannot be empty"))
        }
        if (password.isBlank()) {
            return Result.failure(IllegalArgumentException("Password cannot be empty"))
        }
        return authRepository.login(username.trim(), password)
    }
}

package com.example.pokedexlite.domain.usecase.auth

import com.example.pokedexlite.domain.model.User
import com.example.pokedexlite.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String
    ): Result<User> {
        if (username.isBlank()) {
            return Result.failure(IllegalArgumentException("Username cannot be empty"))
        }
        if (email.isBlank() || !email.contains("@")) {
            return Result.failure(IllegalArgumentException("Please enter a valid email"))
        }
        if (password.length < 6) {
            return Result.failure(IllegalArgumentException("Password must be at least 6 characters"))
        }
        return authRepository.register(username.trim(), email.trim(), password)
    }
}

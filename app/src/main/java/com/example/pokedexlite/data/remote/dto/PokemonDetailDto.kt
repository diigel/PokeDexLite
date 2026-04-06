package com.example.pokedexlite.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("abilities")
    val abilities: List<AbilitySlot>,
    @SerializedName("sprites")
    val sprites: SpriteDto?
)

data class AbilitySlot(
    @SerializedName("ability")
    val ability: AbilityInfo,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)

data class AbilityInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class SpriteDto(
    @SerializedName("front_default")
    val frontDefault: String?
)

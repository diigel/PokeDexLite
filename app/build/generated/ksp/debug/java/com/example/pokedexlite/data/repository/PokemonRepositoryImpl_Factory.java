package com.example.pokedexlite.data.repository;

import com.example.pokedexlite.data.local.dao.PokemonDao;
import com.example.pokedexlite.data.remote.PokeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class PokemonRepositoryImpl_Factory implements Factory<PokemonRepositoryImpl> {
  private final Provider<PokeApiService> apiServiceProvider;

  private final Provider<PokemonDao> pokemonDaoProvider;

  public PokemonRepositoryImpl_Factory(Provider<PokeApiService> apiServiceProvider,
      Provider<PokemonDao> pokemonDaoProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.pokemonDaoProvider = pokemonDaoProvider;
  }

  @Override
  public PokemonRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), pokemonDaoProvider.get());
  }

  public static PokemonRepositoryImpl_Factory create(Provider<PokeApiService> apiServiceProvider,
      Provider<PokemonDao> pokemonDaoProvider) {
    return new PokemonRepositoryImpl_Factory(apiServiceProvider, pokemonDaoProvider);
  }

  public static PokemonRepositoryImpl newInstance(PokeApiService apiService,
      PokemonDao pokemonDao) {
    return new PokemonRepositoryImpl(apiService, pokemonDao);
  }
}

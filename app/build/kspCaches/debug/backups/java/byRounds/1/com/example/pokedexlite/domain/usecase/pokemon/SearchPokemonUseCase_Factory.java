package com.example.pokedexlite.domain.usecase.pokemon;

import com.example.pokedexlite.domain.repository.PokemonRepository;
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
public final class SearchPokemonUseCase_Factory implements Factory<SearchPokemonUseCase> {
  private final Provider<PokemonRepository> pokemonRepositoryProvider;

  public SearchPokemonUseCase_Factory(Provider<PokemonRepository> pokemonRepositoryProvider) {
    this.pokemonRepositoryProvider = pokemonRepositoryProvider;
  }

  @Override
  public SearchPokemonUseCase get() {
    return newInstance(pokemonRepositoryProvider.get());
  }

  public static SearchPokemonUseCase_Factory create(
      Provider<PokemonRepository> pokemonRepositoryProvider) {
    return new SearchPokemonUseCase_Factory(pokemonRepositoryProvider);
  }

  public static SearchPokemonUseCase newInstance(PokemonRepository pokemonRepository) {
    return new SearchPokemonUseCase(pokemonRepository);
  }
}

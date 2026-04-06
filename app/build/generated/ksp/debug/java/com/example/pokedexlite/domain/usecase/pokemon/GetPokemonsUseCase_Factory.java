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
public final class GetPokemonsUseCase_Factory implements Factory<GetPokemonsUseCase> {
  private final Provider<PokemonRepository> pokemonRepositoryProvider;

  public GetPokemonsUseCase_Factory(Provider<PokemonRepository> pokemonRepositoryProvider) {
    this.pokemonRepositoryProvider = pokemonRepositoryProvider;
  }

  @Override
  public GetPokemonsUseCase get() {
    return newInstance(pokemonRepositoryProvider.get());
  }

  public static GetPokemonsUseCase_Factory create(
      Provider<PokemonRepository> pokemonRepositoryProvider) {
    return new GetPokemonsUseCase_Factory(pokemonRepositoryProvider);
  }

  public static GetPokemonsUseCase newInstance(PokemonRepository pokemonRepository) {
    return new GetPokemonsUseCase(pokemonRepository);
  }
}

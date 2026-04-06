package com.example.pokedexlite.presentation.home;

import com.example.pokedexlite.domain.usecase.pokemon.GetPokemonsUseCase;
import com.example.pokedexlite.domain.usecase.pokemon.SearchPokemonUseCase;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<GetPokemonsUseCase> getPokemonsUseCaseProvider;

  private final Provider<SearchPokemonUseCase> searchPokemonUseCaseProvider;

  public HomeViewModel_Factory(Provider<GetPokemonsUseCase> getPokemonsUseCaseProvider,
      Provider<SearchPokemonUseCase> searchPokemonUseCaseProvider) {
    this.getPokemonsUseCaseProvider = getPokemonsUseCaseProvider;
    this.searchPokemonUseCaseProvider = searchPokemonUseCaseProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(getPokemonsUseCaseProvider.get(), searchPokemonUseCaseProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<GetPokemonsUseCase> getPokemonsUseCaseProvider,
      Provider<SearchPokemonUseCase> searchPokemonUseCaseProvider) {
    return new HomeViewModel_Factory(getPokemonsUseCaseProvider, searchPokemonUseCaseProvider);
  }

  public static HomeViewModel newInstance(GetPokemonsUseCase getPokemonsUseCase,
      SearchPokemonUseCase searchPokemonUseCase) {
    return new HomeViewModel(getPokemonsUseCase, searchPokemonUseCase);
  }
}

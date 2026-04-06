package com.example.pokedexlite.presentation.detail;

import androidx.lifecycle.SavedStateHandle;
import com.example.pokedexlite.domain.usecase.pokemon.GetPokemonDetailUseCase;
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
public final class DetailViewModel_Factory implements Factory<DetailViewModel> {
  private final Provider<GetPokemonDetailUseCase> getPokemonDetailUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public DetailViewModel_Factory(Provider<GetPokemonDetailUseCase> getPokemonDetailUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getPokemonDetailUseCaseProvider = getPokemonDetailUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public DetailViewModel get() {
    return newInstance(getPokemonDetailUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static DetailViewModel_Factory create(
      Provider<GetPokemonDetailUseCase> getPokemonDetailUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new DetailViewModel_Factory(getPokemonDetailUseCaseProvider, savedStateHandleProvider);
  }

  public static DetailViewModel newInstance(GetPokemonDetailUseCase getPokemonDetailUseCase,
      SavedStateHandle savedStateHandle) {
    return new DetailViewModel(getPokemonDetailUseCase, savedStateHandle);
  }
}

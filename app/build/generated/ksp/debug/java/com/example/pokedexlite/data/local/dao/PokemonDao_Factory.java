package com.example.pokedexlite.data.local.dao;

import com.example.pokedexlite.data.local.CouchbaseManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PokemonDao_Factory implements Factory<PokemonDao> {
  private final Provider<CouchbaseManager> couchbaseManagerProvider;

  public PokemonDao_Factory(Provider<CouchbaseManager> couchbaseManagerProvider) {
    this.couchbaseManagerProvider = couchbaseManagerProvider;
  }

  @Override
  public PokemonDao get() {
    return newInstance(couchbaseManagerProvider.get());
  }

  public static PokemonDao_Factory create(Provider<CouchbaseManager> couchbaseManagerProvider) {
    return new PokemonDao_Factory(couchbaseManagerProvider);
  }

  public static PokemonDao newInstance(CouchbaseManager couchbaseManager) {
    return new PokemonDao(couchbaseManager);
  }
}

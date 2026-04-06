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
public final class UserDao_Factory implements Factory<UserDao> {
  private final Provider<CouchbaseManager> couchbaseManagerProvider;

  public UserDao_Factory(Provider<CouchbaseManager> couchbaseManagerProvider) {
    this.couchbaseManagerProvider = couchbaseManagerProvider;
  }

  @Override
  public UserDao get() {
    return newInstance(couchbaseManagerProvider.get());
  }

  public static UserDao_Factory create(Provider<CouchbaseManager> couchbaseManagerProvider) {
    return new UserDao_Factory(couchbaseManagerProvider);
  }

  public static UserDao newInstance(CouchbaseManager couchbaseManager) {
    return new UserDao(couchbaseManager);
  }
}

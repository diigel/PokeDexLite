package com.example.pokedexlite.data.local;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class CouchbaseManager_Factory implements Factory<CouchbaseManager> {
  private final Provider<Context> contextProvider;

  public CouchbaseManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CouchbaseManager get() {
    return newInstance(contextProvider.get());
  }

  public static CouchbaseManager_Factory create(Provider<Context> contextProvider) {
    return new CouchbaseManager_Factory(contextProvider);
  }

  public static CouchbaseManager newInstance(Context context) {
    return new CouchbaseManager(context);
  }
}

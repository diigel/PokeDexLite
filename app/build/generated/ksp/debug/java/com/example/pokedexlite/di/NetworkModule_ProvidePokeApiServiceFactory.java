package com.example.pokedexlite.di;

import com.example.pokedexlite.data.remote.PokeApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvidePokeApiServiceFactory implements Factory<PokeApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidePokeApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PokeApiService get() {
    return providePokeApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvidePokeApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePokeApiServiceFactory(retrofitProvider);
  }

  public static PokeApiService providePokeApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePokeApiService(retrofit));
  }
}

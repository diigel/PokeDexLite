package com.example.pokedexlite.data.repository;

import com.example.pokedexlite.data.local.SessionManager;
import com.example.pokedexlite.data.local.dao.UserDao;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  public AuthRepositoryImpl_Factory(Provider<UserDao> userDaoProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.userDaoProvider = userDaoProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(userDaoProvider.get(), sessionManagerProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<UserDao> userDaoProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new AuthRepositoryImpl_Factory(userDaoProvider, sessionManagerProvider);
  }

  public static AuthRepositoryImpl newInstance(UserDao userDao, SessionManager sessionManager) {
    return new AuthRepositoryImpl(userDao, sessionManager);
  }
}

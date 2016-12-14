package com.ways2u;

import com.ways2u.reuse.Person;
import com.ways2u.user.PrintPerson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvidePrintPersonFactory implements Factory<PrintPerson> {
  private final AppModule module;

  private final Provider<Person> personProvider;

  public AppModule_ProvidePrintPersonFactory(AppModule module, Provider<Person> personProvider) {
    assert module != null;
    this.module = module;
    assert personProvider != null;
    this.personProvider = personProvider;
  }

  @Override
  public PrintPerson get() {
    return Preconditions.checkNotNull(
        module.providePrintPerson(personProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<PrintPerson> create(AppModule module, Provider<Person> personProvider) {
    return new AppModule_ProvidePrintPersonFactory(module, personProvider);
  }
}

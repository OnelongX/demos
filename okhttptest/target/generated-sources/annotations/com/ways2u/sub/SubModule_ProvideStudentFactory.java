package com.ways2u.sub;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SubModule_ProvideStudentFactory implements Factory<Student> {
  private final SubModule module;

  public SubModule_ProvideStudentFactory(SubModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Student get() {
    return Preconditions.checkNotNull(
        module.provideStudent(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Student> create(SubModule module) {
    return new SubModule_ProvideStudentFactory(module);
  }
}

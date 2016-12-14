package com.ways2u.config;

import com.ways2u.reuse.Person;
import com.ways2u.reuse.ReuseModule;
import com.ways2u.reuse.ReuseModule_ProvidePersonFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerConfigComponent implements ConfigComponent {
  private Provider<String> provideBaseUrlProvider;

  private Provider<Boolean> provideDebugProvider;

  private Provider<Person> providePersonProvider;

  private DaggerConfigComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ConfigComponent create() {
    return builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideBaseUrlProvider =
        DoubleCheck.provider(ConfigModule_ProvideBaseUrlFactory.create(builder.configModule));

    this.provideDebugProvider =
        DoubleCheck.provider(ConfigModule_ProvideDebugFactory.create(builder.configModule));

    this.providePersonProvider =
        SingleCheck.provider(ReuseModule_ProvidePersonFactory.create(builder.reuseModule));
  }

  @Override
  public String getBaseUrl() {
    return provideBaseUrlProvider.get();
  }

  @Override
  public boolean isDebug() {
    return provideDebugProvider.get();
  }

  @Override
  public Person getPerson() {
    return providePersonProvider.get();
  }

  public static final class Builder {
    private ConfigModule configModule;

    private ReuseModule reuseModule;

    private Builder() {}

    public ConfigComponent build() {
      if (configModule == null) {
        this.configModule = new ConfigModule();
      }
      if (reuseModule == null) {
        this.reuseModule = new ReuseModule();
      }
      return new DaggerConfigComponent(this);
    }

    public Builder configModule(ConfigModule configModule) {
      this.configModule = Preconditions.checkNotNull(configModule);
      return this;
    }

    public Builder reuseModule(ReuseModule reuseModule) {
      this.reuseModule = Preconditions.checkNotNull(reuseModule);
      return this;
    }
  }
}

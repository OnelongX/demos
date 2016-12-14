package com.ways2u;

import com.ways2u.net.GankApi;
import com.ways2u.net.NetComponent;
import com.ways2u.reuse.Person;
import com.ways2u.reuse.ReuseModule;
import com.ways2u.reuse.ReuseModule_ProvidePersonFactory;
import com.ways2u.sub.SubComponent;
import com.ways2u.sub.SubModule;
import com.ways2u.sub.SubModule_TtFactory;
import com.ways2u.user.Binder;
import com.ways2u.user.Binder_Factory;
import com.ways2u.user.MyUser;
import com.ways2u.user.MyUser_Factory;
import com.ways2u.user.PrintPerson;
import com.ways2u.user.User;
import com.ways2u.user.UserModule;
import com.ways2u.user.UserModule_ProvideBarKeyFactory;
import com.ways2u.user.UserModule_ProvideFooKeyFactory;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.MapFactory;
import dagger.internal.MapProviderFactory;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<AppContext> provideAppContextProvider;

  private Provider<GankApi> getGankApiProvider;

  private Provider<MyUser> myUserProvider;

  private Provider<User> provideUserProvider;

  private Provider<Binder> binderProvider;

  private Provider<Person> providePersonProvider;

  private Provider<String> provideFooKeyProvider;

  private Provider<String> provideBarKeyProvider;

  private Provider<Map<String, Provider<String>>> mapOfStringAndProviderOfStringProvider;

  private Provider<Map<String, String>> mapOfStringAndStringProvider;

  private Provider<PrintPerson> providePrintPersonProvider;

  private MembersInjector<AppMain> appMainMembersInjector;

  private DaggerAppComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideAppContextProvider =
        DoubleCheck.provider(AppModule_ProvideAppContextFactory.create(builder.appModule));

    this.getGankApiProvider =
        new Factory<GankApi>() {
          private final NetComponent netComponent = builder.netComponent;

          @Override
          public GankApi get() {
            return Preconditions.checkNotNull(
                netComponent.getGankApi(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.myUserProvider = DoubleCheck.provider(MyUser_Factory.create());

    this.provideUserProvider = (Provider) myUserProvider;

    this.binderProvider = DoubleCheck.provider(Binder_Factory.create(provideUserProvider));

    this.providePersonProvider =
        SingleCheck.provider(ReuseModule_ProvidePersonFactory.create(builder.reuseModule));

    this.provideFooKeyProvider = UserModule_ProvideFooKeyFactory.create(builder.userModule);

    this.provideBarKeyProvider = UserModule_ProvideBarKeyFactory.create(builder.userModule);

    this.mapOfStringAndProviderOfStringProvider =
        MapProviderFactory.<String, String>builder(2)
            .put("foo", provideFooKeyProvider)
            .put("bar", provideBarKeyProvider)
            .build();

    this.mapOfStringAndStringProvider = MapFactory.create(mapOfStringAndProviderOfStringProvider);

    this.providePrintPersonProvider =
        DoubleCheck.provider(
            AppModule_ProvidePrintPersonFactory.create(builder.appModule, providePersonProvider));

    this.appMainMembersInjector =
        AppMain_MembersInjector.create(
            getGankApiProvider,
            provideUserProvider,
            binderProvider,
            providePersonProvider,
            mapOfStringAndStringProvider,
            providePrintPersonProvider);
  }

  @Override
  public AppContext getContext() {
    return provideAppContextProvider.get();
  }

  @Override
  public void inject(AppMain appMain) {
    appMainMembersInjector.injectMembers(appMain);
  }

  @Override
  public SubComponent addSub(SubModule subModule) {
    return new SubComponentImpl(subModule);
  }

  public static final class Builder {
    private AppModule appModule;

    private ReuseModule reuseModule;

    private UserModule userModule;

    private NetComponent netComponent;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      if (reuseModule == null) {
        this.reuseModule = new ReuseModule();
      }
      if (userModule == null) {
        this.userModule = new UserModule();
      }
      if (netComponent == null) {
        throw new IllegalStateException(NetComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder userModule(UserModule userModule) {
      this.userModule = Preconditions.checkNotNull(userModule);
      return this;
    }

    public Builder reuseModule(ReuseModule reuseModule) {
      this.reuseModule = Preconditions.checkNotNull(reuseModule);
      return this;
    }

    public Builder netComponent(NetComponent netComponent) {
      this.netComponent = Preconditions.checkNotNull(netComponent);
      return this;
    }
  }

  private final class SubComponentImpl implements SubComponent {
    private final SubModule subModule;

    private Provider<String> ttProvider;

    private MembersInjector<AppSub> appSubMembersInjector;

    private SubComponentImpl(SubModule subModule) {
      this.subModule = Preconditions.checkNotNull(subModule);
      initialize();
    }

    @SuppressWarnings("unchecked")
    private void initialize() {

      this.ttProvider = DoubleCheck.provider(SubModule_TtFactory.create(subModule));

      this.appSubMembersInjector =
          AppSub_MembersInjector.create(
              DaggerAppComponent.this.getGankApiProvider,
              DaggerAppComponent.this.provideUserProvider,
              DaggerAppComponent.this.binderProvider,
              DaggerAppComponent.this.providePersonProvider,
              DaggerAppComponent.this.mapOfStringAndStringProvider,
              DaggerAppComponent.this.providePrintPersonProvider,
              ttProvider);
    }

    @Override
    public void inject(AppSub appSub) {
      appSubMembersInjector.injectMembers(appSub);
    }
  }
}

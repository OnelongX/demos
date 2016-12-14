package com.ways2u;

import com.ways2u.net.GankApi;
import com.ways2u.reuse.Person;
import com.ways2u.user.Binder;
import com.ways2u.user.PrintPerson;
import com.ways2u.user.User;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import java.util.Map;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppSub_MembersInjector implements MembersInjector<AppSub> {
  private final Provider<GankApi> gankApiProvider;

  private final Provider<User> userAndUser3AndUser1Provider;

  private final Provider<Binder> binderProvider;

  private final Provider<Person> personAndPerson1Provider;

  private final Provider<Map<String, String>> mapProvider;

  private final Provider<PrintPerson> printPersonProvider;

  private final Provider<String> qqProvider;

  public AppSub_MembersInjector(
      Provider<GankApi> gankApiProvider,
      Provider<User> userAndUser3AndUser1Provider,
      Provider<Binder> binderProvider,
      Provider<Person> personAndPerson1Provider,
      Provider<Map<String, String>> mapProvider,
      Provider<PrintPerson> printPersonProvider,
      Provider<String> qqProvider) {
    assert gankApiProvider != null;
    this.gankApiProvider = gankApiProvider;
    assert userAndUser3AndUser1Provider != null;
    this.userAndUser3AndUser1Provider = userAndUser3AndUser1Provider;
    assert binderProvider != null;
    this.binderProvider = binderProvider;
    assert personAndPerson1Provider != null;
    this.personAndPerson1Provider = personAndPerson1Provider;
    assert mapProvider != null;
    this.mapProvider = mapProvider;
    assert printPersonProvider != null;
    this.printPersonProvider = printPersonProvider;
    assert qqProvider != null;
    this.qqProvider = qqProvider;
  }

  public static MembersInjector<AppSub> create(
      Provider<GankApi> gankApiProvider,
      Provider<User> userAndUser3AndUser1Provider,
      Provider<Binder> binderProvider,
      Provider<Person> personAndPerson1Provider,
      Provider<Map<String, String>> mapProvider,
      Provider<PrintPerson> printPersonProvider,
      Provider<String> qqProvider) {
    return new AppSub_MembersInjector(
        gankApiProvider,
        userAndUser3AndUser1Provider,
        binderProvider,
        personAndPerson1Provider,
        mapProvider,
        printPersonProvider,
        qqProvider);
  }

  @Override
  public void injectMembers(AppSub instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.gankApi = gankApiProvider.get();
    instance.user = userAndUser3AndUser1Provider.get();
    instance.user1 = DoubleCheck.lazy(userAndUser3AndUser1Provider);
    instance.user3 = userAndUser3AndUser1Provider.get();
    instance.binder = binderProvider.get();
    instance.person = personAndPerson1Provider.get();
    instance.person1 = personAndPerson1Provider.get();
    instance.map = mapProvider.get();
    instance.printPerson = printPersonProvider.get();
    instance.qq = qqProvider.get();
  }

  public static void injectBinder(AppSub instance, Provider<Binder> binderProvider) {
    instance.binder = binderProvider.get();
  }

  public static void injectPerson(AppSub instance, Provider<Person> personProvider) {
    instance.person = personProvider.get();
  }

  public static void injectPerson1(AppSub instance, Provider<Person> person1Provider) {
    instance.person1 = person1Provider.get();
  }

  public static void injectMap(AppSub instance, Provider<Map<String, String>> mapProvider) {
    instance.map = mapProvider.get();
  }

  public static void injectPrintPerson(AppSub instance, Provider<PrintPerson> printPersonProvider) {
    instance.printPerson = printPersonProvider.get();
  }

  public static void injectQq(AppSub instance, Provider<String> qqProvider) {
    instance.qq = qqProvider.get();
  }
}

package com.ways2u.config;


import javax.inject.Singleton;

import com.ways2u.reuse.Person;
import com.ways2u.reuse.ReuseModule;
import dagger.Component;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/9.
 */
@ConfigScope
@Component(modules = {ConfigModule.class, ReuseModule.class})
public interface ConfigComponent {
    String getBaseUrl();
    boolean isDebug();
    Person getPerson();
}

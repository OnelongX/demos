package com.ways2u.reuse;

import com.ways2u.config.ConfigScope;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by huanglong on 2016/12/13.
 */
@Module
@Reusable //作用域
public class ReuseModule {
    public ReuseModule(){}

    @Provides
    @Reusable //这样多个Component引用也是单例
    public Person providePerson(){
        return new Person();
    }
}

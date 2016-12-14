package com.ways2u.config;



import com.ways2u.reuse.ReuseModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/11.
 */
@Module
public class ConfigModule {

    public ConfigModule(){
    }

    @ConfigScope
    @Provides
    public String provideBaseUrl(){
        return "http://gank.io/api/";
    }

    @ConfigScope
    @Provides
    public boolean provideDebug(){
        return false;
    }

}

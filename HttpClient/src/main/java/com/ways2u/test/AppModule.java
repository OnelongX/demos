package com.ways2u.test;

import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module
public class AppModule {
    public AppModule(){

    }

    @ApplicationScope
    @Provides
    public AppContext provideAppContext(){
        return new AppContext();
    }

}

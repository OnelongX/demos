package com.ways2u.sub;


import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/11.
 */
@Module
public class SubModule {
    public SubModule(){}

    @Provides
    @SubScope
    public String tt(){
        return "Onelong";
    }

}

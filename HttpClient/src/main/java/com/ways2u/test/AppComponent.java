package com.ways2u.test;

import com.ways2u.net.NetComponent;
import dagger.Component;

/**
 * Created by huanglong on 2016/12/12.
 */

@ApplicationScope
@Component(
        dependencies = {
                NetComponent.class
        },
        modules = {
                AppModule.class,

        })

public interface AppComponent{
    AppContext getContext();
    void inject(AppMain appMain);

}

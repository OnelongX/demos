package com.ways2u;

import com.ways2u.config.ConfigComponent;
import com.ways2u.net.NetComponent;
import com.ways2u.reuse.ReuseModule;
import com.ways2u.sub.SubComponent;
import com.ways2u.sub.SubModule;
import com.ways2u.user.User;
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
                ReuseModule.class //暴露被外部使用
        })

public interface AppComponent{
    AppContext getContext();

    void inject(AppMain appMain);

    SubComponent addSub(SubModule subModule);//SubComponent目前只知道这样使用，继承父组建的所有provides
}

package com.ways2u.user;

import com.ways2u.ApplicationScope;
import com.ways2u.map.TestKey;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.producers.Produces;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module
public class UserModule {

 /*
    @ApplicationScope
    @Provides
    public MyUser provideUser() {
        return new MyUser();
    }
*/

    @Provides(type = Provides.Type.MAP)
    @TestKey("foo")
    public String provideFooKey() {
        return "foo value";
    }

    @Provides(type = Provides.Type.MAP)
    @TestKey("bar")
    public String provideBarKey() {
        return "bar value";
    }
}

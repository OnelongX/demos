package com.ways2u;

import com.ways2u.reuse.Person;
import com.ways2u.reuse.ReuseModule;
import com.ways2u.sub.SubComponent;
import com.ways2u.sub.SubModule;
import com.ways2u.user.AbstractMpdule;
import com.ways2u.user.PrintPerson;
import com.ways2u.user.UserModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module(includes = {UserModule.class, AbstractMpdule.class})
public class AppModule {
    public AppModule(){

    }

    @ApplicationScope
    @Provides
    public AppContext provideAppContext(){
        return new AppContext();
    }
/*
// baseUrl 跨组件了不能注入
    @ApplicationScope
    @Provides
    public PrintPerson providePrintPerson(Person person,String baseUrl){
        return new PrintPerson(person,baseUrl);
    }
*/

    @ApplicationScope
    @Provides
    public PrintPerson providePrintPerson(Person person){
        return new PrintPerson(person);
    }

}

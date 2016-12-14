package com.ways2u.user;

import dagger.Binds;
import dagger.Module;

/**
 * Created by huanglong on 2016/12/12.
 */
@Module
public abstract class AbstractMpdule {
    //其实就是做类型转换的 obj to interface
    //不能包含其他非抽象方法
    @Binds
    public abstract User provideUser(MyUser user);
}

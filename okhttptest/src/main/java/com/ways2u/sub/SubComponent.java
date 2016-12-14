package com.ways2u.sub;

import com.ways2u.AppSub;
import dagger.Subcomponent;

/**
 * Created by huanglong on 2016/12/11.
 */

@SubScope
@Subcomponent(modules = SubModule.class)
public interface SubComponent {

    void inject(AppSub appSub);
}

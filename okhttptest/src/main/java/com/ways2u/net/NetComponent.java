package com.ways2u.net;

import com.ways2u.config.ConfigComponent;
import dagger.Component;

/**
 * Created by huanglong on 2016/12/12.
 */

@NetScope
@Component(dependencies = {ConfigComponent.class},modules = {NetModule.class})
public interface NetComponent {
    GankApi getGankApi();
}

package com.ways2u.map;

import dagger.MapKey;

@MapKey(unwrapValue = true)
public @interface TestKey {
    String value();
}
package com.ways2u.net;

import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NetScope {}
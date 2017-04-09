package com.stok.ramazan.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
@SuppressWarnings("rawtypes")
public @interface Control {
	Class[] nullControl() default Class.class;

	int lenght() default 0;

	int minlenght() default 0;
}

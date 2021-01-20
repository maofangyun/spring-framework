package com.mfy.test.advisor;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Lock {

	String lockName() default "redis";

	int expire() default 60;

	String key() default "lock";

}

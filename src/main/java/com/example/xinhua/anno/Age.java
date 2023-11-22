package com.example.xinhua.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.xinhua.validation.AgeValidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { AgeValidation.class }) // 指定提供校对的类
public @interface Age {
    String message() default "年龄不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

package com.example.xinhua.validation;

import com.example.xinhua.anno.Age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidation implements ConstraintValidator<Age, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value < 0) {
            return false;
        }
        return true;
    }

}

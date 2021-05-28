package com.shangzf.user.api.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfigPwdValidator implements ConstraintValidator<ConfigPwd, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

    @Override
    public void initialize(ConfigPwd constraintAnnotation) {

    }
}

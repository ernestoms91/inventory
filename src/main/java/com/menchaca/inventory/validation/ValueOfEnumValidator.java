package com.menchaca.inventory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.menchaca.inventory.validation.ValueOfEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueOfEnumValidator implements ConstraintValidator <ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;


    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
     }



    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}

package com.example.table.reservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class LocalDateTimeNotNullValidator implements ConstraintValidator<NotNullLocalDateTime, LocalDateTime> {
  @Override
  public void initialize(NotNullLocalDateTime constraintAnnotation) {
  }

  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    return value != null;
  }
}

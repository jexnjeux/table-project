package com.example.table.reservation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ReservationDateValidator implements ConstraintValidator<ValidReservationDate, LocalDateTime> {

  @Override
  public void initialize(ValidReservationDate constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
    LocalDate today = LocalDate.now();

    LocalDate oneMonthLater = today.plus(1, ChronoUnit.MONTHS);
    return !value.toLocalDate().isBefore(today) && !value.toLocalDate().isAfter(oneMonthLater);
  }
}

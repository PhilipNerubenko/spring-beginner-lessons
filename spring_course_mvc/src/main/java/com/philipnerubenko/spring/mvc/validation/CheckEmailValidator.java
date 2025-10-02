package com.philipnerubenko.spring.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * A custom constraint validator that checks if an email address ends with a specified suffix.
 * This validator is used in conjunction with the {@link CheckEmail} annotation to enforce
 * email format constraints based on a required ending string.
 */
class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {
  /** The suffix that the validated email must end with. */
  private String endOfEmail;

  /**
   * Initializes the validator with the configuration from the annotation.
   *
   * @param constraintAnnotation the {@link CheckEmail} annotation containing the required
   *                             email suffix
   */
  @Override
  public void initialize(CheckEmail constraintAnnotation) {
    endOfEmail = constraintAnnotation.value();
  }

  /**
   * Validates whether the provided email address ends with the configured suffix.
   * <p>
   * If the input is {@code null}, validation passes (treated as valid).
   *
   * @param enteredValue the email address to validate
   * @param cvc          context object for customizing constraint violation messages
   * @return {@code true} if the email is valid (null or ends with the suffix), otherwise
   *         {@code false}
   */
  @Override
  public boolean isValid(String enteredValue, ConstraintValidatorContext cvc) {
    if (enteredValue == null) {
      return true;
    }
    return enteredValue.endsWith(endOfEmail);
  }
}

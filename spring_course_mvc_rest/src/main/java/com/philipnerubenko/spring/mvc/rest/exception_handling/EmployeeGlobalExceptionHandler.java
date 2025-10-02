package com.philipnerubenko.spring.mvc.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for managing errors in employee-related controller operations.
 * This class uses {@literal @}ControllerAdvice to provide centralized exception handling
 * across all controller classes in the application.
 */
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
  /**
   * Handles {@link NoSuchEmployeeException} exceptions globally.
   * <p>
   * Returns a HTTP 404 NOT_FOUND response with error details in the specified format.
   *
   * @param exception the caught NoSuchEmployeeException containing error information
   * @return ResponseEntity with error details and HTTP 404 status
   */
  @ExceptionHandler
  public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {
    EmployeeIncorrectData data = new EmployeeIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }

  /**
   * Handles general Exception types globally.
   * <p>
   * Returns a HTTP 400 BAD_REQUEST response with error details in the specified format.
   *
   * @param exception the caught generic Exception containing error information
   * @return ResponseEntity with error details and HTTP 400 status
   */
  @ExceptionHandler
  public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {
    EmployeeIncorrectData data = new EmployeeIncorrectData();
    data.setInfo(exception.getMessage());

    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }
}

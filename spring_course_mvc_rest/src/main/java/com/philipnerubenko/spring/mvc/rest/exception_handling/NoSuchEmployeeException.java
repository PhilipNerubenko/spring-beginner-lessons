package com.philipnerubenko.spring.mvc.rest.exception_handling;

/**
 * Custom unchecked exception indicating that an employee could not be found in the database.
 * This exception is typically thrown when attempting to retrieve an employee by ID or other
 * criteria that does not match any existing records.
 */
public class NoSuchEmployeeException extends RuntimeException {
  /**
   * Constructs a new NoSuchEmployeeException with the specified detail message.
   * <p>
   * The message is saved for later retrieval by the {@link Throwable#getMessage()} method.
   *
   * @param message the detail message (which is saved for later retrieval by the
   *                {@link Throwable#getMessage()} method)
   */
  public NoSuchEmployeeException(String message) {
    super(message);
  }
}

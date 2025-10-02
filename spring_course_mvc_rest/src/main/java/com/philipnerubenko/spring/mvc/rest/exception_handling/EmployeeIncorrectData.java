package com.philipnerubenko.spring.mvc.rest.exception_handling;

/**
 * A data transfer object (DTO) used to encapsulate error information for employee-related
 * operations. This class is typically used to return structured error messages in HTTP responses
 * when invalid or incorrect employee data is encountered during API requests.
 */
public class EmployeeIncorrectData {
  /**
   * A string containing descriptive error information about the incorrect data.
   */
  private String info;

  /**
   * Default no-argument constructor required for framework compatibility (e.g., Spring).
   * Initializes an empty EmployeeIncorrectData object.
   */
  public EmployeeIncorrectData() {}

  /**
   * Retrieves the error information message.
   *
   * @return the descriptive error message
   */
  public String getInfo() {
    return info;
  }

  /**
   * Sets the error information message.
   *
   * @param info the error message to store
   */
  public void setInfo(String info) {
    this.info = info;
  }
}

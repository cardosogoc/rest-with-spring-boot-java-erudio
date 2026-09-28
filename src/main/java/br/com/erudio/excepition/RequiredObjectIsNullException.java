package br.com.erudio.excepition;

public class RequiredObjectIsNullException extends RuntimeException {

  public RequiredObjectIsNullException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public RequiredObjectIsNullException(String message) {
    super(message);
  }

  public RequiredObjectIsNullException() {
    super("It is not allowed to persist a null object!");
  }
}

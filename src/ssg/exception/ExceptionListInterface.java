package ssg.exception;

public interface ExceptionListInterface {
  void throwInvalidNumberRange(String number, int max);
  void throwInvalidNumber(String number);
  void throwInvalidInputLength(String input);
  void throwInvalidInputAddress(String address);
  void throwInvalidFloat(String number);
  void throwInvalidOkay(String ok);
}

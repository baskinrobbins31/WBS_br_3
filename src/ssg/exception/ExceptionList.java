package ssg.exception;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ssg.enums.errorcode.ErrorCode;

public class ExceptionList implements ExceptionListInterface{

  @Override
  public void throwInvalidNumberRange(String number, int max) {
    if (isValidNumber(number) || isCorrectNumberRange(number, max)) {
      throwExceptionInvalidInputNumber();
    }
  }

  @Override
  public void throwInvalidNumber(String number) {
    if (isValidNumber(number)) {
      throwExceptionInvalidInputNumber();
    }
  }

  @Override
  public void throwInvalidInputLength(String input) {
    if (isValidText(input) || isLength50(input)) {
      throwExceptionInvalidInputLength();
    }
  }

  @Override
  public void throwInvalidInputAddress(String address) {
    if (isValidAddress(address) || isLength50(address)) {
      throwExceptionInvalidInputAddress();
    }
  }

  @Override
  public void throwInvalidFloat(String number) {
    if (isValidFloat(number)) {
      throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
    }
  }

  @Override
  public void throwInvalidOkay(String ok) {
    if (isValidOk(ok)) {
      throw new Exception(ErrorCode.INVALID_INPUT_OKAY);
    }
  }

  private void throwExceptionInvalidInputNumber(){
    throw new Exception(ErrorCode.INVALID_INPUT_NUMBER);
  }

  private void throwExceptionInvalidInputLength() {
    throw new Exception(ErrorCode.INVALID_INPUT_LENGTH);
  }

  private void throwExceptionInvalidInputAddress(){
    throw new Exception(ErrorCode.INVALID_INPUT_ADDRESS);
  }

  private boolean isLength50(String s) {
    return (s.length() > 50);
  }

  private boolean isCorrectNumberRange(String number, int max) {
    try {
      int num = Integer.parseInt(number);
      return num < 1 || num > max;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  private boolean isValidNumber(String number) {
    String regex = "^[0-9]+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(number);
    return !m.matches();
  }

  private boolean isValidText(String text) {
    String regex = "^[a-zA-Z가-힣,()\\s]+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(text);
    return !m.matches();
  }

  private boolean isValidAddress(String address) {
    String regex = "^[a-zA-Z가-힣()0-9 _-]*$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(address);
    return !m.matches();
  }

  private boolean isValidFloat(String number) {
    try {
      float f = Float.parseFloat(number);
      return (f < 0.0) || (f > Float.MAX_VALUE);
    } catch (NumberFormatException e) {
      return true;
    }
  }

  private boolean isValidSmallInt(String number) {
    try {
      int i = Integer.parseInt(number);
      return i < 0 || i > 32767;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  private boolean isValidEmail(String email) {
    String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(email);
    return !m.matches();
  }

  private boolean isValidOk(String ok) {
    String regex = "^[YyNn]$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(ok);
    return (!m.matches() || ok.length() != 1);
  }

  public static boolean isValidPhoneNumber(String number) {
    String regex = "^[0-9]+(-)+[0-9]+(-)+[0-9]*$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(number);
    return !m.matches();
  }

  public static boolean isExistWId(String id, ArrayList<Integer> array) {
    try {
      int i = Integer.parseInt(id);
      for (int a : array) {
        if (i == a) {
          return false;
        }
      }
    } catch(NumberFormatException ex){
      return true;
    }
    return true;
  }

}

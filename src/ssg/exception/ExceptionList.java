package ssg.exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionList {

  public static boolean isValidString(String name) {
    String regex = "^[a-zA-Z가-힣]+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(name);
    return !m.matches();
  }

  public static boolean isValidText(String text) {
    String regex = "^[a-zA-Z가-힣,]+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(text);
    return !m.matches();
  }

  public static boolean isValidNumber(String number) {
    String regex = "^[0-9]+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(number);
    return !m.matches();
  }

  public static boolean isNumberRange1To3(String number) {
    try {
      int num = Integer.parseInt(number);
      return num < 1 || num > 3;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public static boolean isNumberRange1To7(String number) {
    try {
      int num = Integer.parseInt(number);
      return num < 1 || num > 7;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public static boolean isNumberRange1To10(String number) {
    try {
      int num = Integer.parseInt(number);
      return num < 1 || num > 10;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public static boolean isValidAddress(String address) {
    String regex = "^[a-zA-Z0-9 _-]*$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(address);
    return !m.matches();
  }

  public static boolean isValidFloat(String number) {
    try {
      float f = Float.parseFloat(number);
      return (f < Float.MIN_VALUE) || (f > Float.MAX_VALUE);
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public static boolean isValidSmallInt(String number) {
    try {
      int i = Integer.parseInt(number);
      return i < 0 || i > 32767;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public static boolean isValidEmail(String email) {
    String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(email);
    return !m.matches();
  }


  public static boolean isValidOk(String ok) {
    String regex = "^[YyNn]$\n";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(ok);
    return (!m.matches() || ok == null || ok.length() != 1);
  }

  public static boolean isLength50(String s) {
    return (s.length() > 50);
  }

  public static boolean isValidPhoneNumber(String number) {
    String regex = "^[0-9]+(-)+[0-9]+(-)+[0-9]*$";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(number);
    return !m.matches();
  }

//  public static boolean isExistName(String name, Map<String, Address> people) {
//    Set<String> keySet = people.keySet();
//    for (String a : keySet) {
//      if (name.equals(people.get(a).getName())) {
//        return true;
//      }
//    }
//
//    return false;
//  }
//
//  public static boolean isExistNumber(String number, Map<String, Address> people) {
//    Set<String> keySet = people.keySet();
//    for (String a : keySet) {
//      if (number.equals(people.get(a).getPhoneNumber())) {
//        return true;
//      }
//    }
//    return false;
//  }
}

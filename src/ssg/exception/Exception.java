package ssg.exception;

import ssg.enums.errorcode.ErrorCode;

public class Exception extends RuntimeException{
  private ErrorCode errorCode;

  public Exception(ErrorCode codes) {
    this.errorCode = codes;
    System.out.println("\n*************** error 발생 *************** ");
    System.out.println(
        errorCode.getCode() + " / " + errorCode.getMessage() + " / " + errorCode.getStatus());
    System.out.println("*****************************************");
  }
}

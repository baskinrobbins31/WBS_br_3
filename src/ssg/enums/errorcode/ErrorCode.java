package ssg.enums.errorcode;

public enum ErrorCode implements EnumError {

  INVALID_INPUT_EMAIL(400, "유효하지 않는 이메일 형식입니다.", "E001"),
  INVALID_INPUT_PHONENUMBER(400, "유효하지 않는 전화번호 형식입니다.", "P001"),
  EXISTS_ALREADY_NAME(400, "이미 주소록에 존재하는 이름입니다.", "N001"),
  EXISTS_ALREADY_PHONENUMBER(400, "이미 주소록에 존재하는 번호입니다.", "P002"),
  ADDRESSBOOK_NO_INFORMATION(404, "주소록에 존재하지 않는 정보입니다.", "N002");
  private int status;
  private String code;
  private String message;




  ErrorCode(int status, String message, String code) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public int getStatus() {
    return this.status;
  }
}

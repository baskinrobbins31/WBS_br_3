package ssg.enums.errorcode;

public enum ErrorCode implements EnumError {


  INVALID_INPUT_STRING(400, "유효하지 않는 문자 형식입니다.", "I001"),
  INVALID_INPUT_NUMBER(400, "유효하지 않는 숫자 형식입니다.", "I002"),
  INVALID_INPUT_ADDRESS(400, "유효하지 않는 주소 형식입니다. 글자수는 50 이하로 작성해주세요.", "I003"),
  INVALID_INPUT_OKAY(400, "유효하지 않는 입력 형식입니다. Y 또는 N 을 입력해주세요.", "I004"),
  INVALID_INPUT_LENGTH(400, "유효하지 않는 입력 형식입니다. 글자수는 50 이하로 작성해주세요.", "I005"),

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

public enum ResponseType {

  FOUND(302), NOT_FOUND(404), CREATED(201), CONFLICT(409),
  TOO_MANY_REQUESTS(429), BAD_REQUEST(400);

  private final int code;

  ResponseType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

}

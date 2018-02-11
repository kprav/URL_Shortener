public class Response {

  private ResponseType responseType;
  private String URL;

  public Response(ResponseType responseType, String URL) {
    this.responseType = responseType;
    this.URL = URL;
  }

  public ResponseType getResponseType() {
    return responseType;
  }

  public void setResponseType(ResponseType responseType) {
    this.responseType = responseType;
  }

  public String getURL() {
    return URL;
  }

  public void setURL(String uRL) {
    URL = uRL;
  }

}

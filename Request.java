public class Request {

  private String linkID;
  private String URL;
  private RequestType requestType;
  private HttpMethod httpMethod;

  public Request(String linkID, String URL, RequestType requestType, HttpMethod httpMethod) {
    this.linkID = linkID;
    this.URL = URL;
    this.requestType = requestType;
    this.httpMethod = httpMethod;
  }

  public String getLinkID() {
    return linkID;
  }

  public void setLinkID(String linkID) {
    this.linkID = linkID;
  }

  public String getURL() {
    return URL;
  }

  public void setRequestType(RequestType requestType) {
    this.requestType = requestType;
  }

  public RequestType getRequestType() {
    return requestType;
  }

  public void setURL(String uRL) {
    URL = uRL;
  }

  public HttpMethod getHttpMethod() {
    return httpMethod;
  }

  public void setHttpMethod(HttpMethod httpMethod) {
    this.httpMethod = httpMethod;
  }

  public boolean isValidRequest(HttpMethod httpMethod) {
    if (this.httpMethod != httpMethod) {
      return false;
    } else if (this.URL == null) {
      return false;
    }
    return true;
  }

}

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class UrlShortener {

  private HashMap<String, String> createMap = new HashMap<String, String>();
  private HashMap<String, String> resolveMap = new HashMap<String, String>();

  private String generateRandomLinkID() {
    final int length = 6;
    final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    char[] linkID = new char[length];
    boolean alreadyPresent = true;
    while (alreadyPresent) {
      for (int i = 0; i < length; i++) {
        int randomIndex = random.nextInt(chars.length() - 1);
        linkID[i] = chars.charAt(randomIndex);
      }
      if (resolveMap.get(new String(linkID)) == null) {
        alreadyPresent = false;
      }
    }
    return new String(linkID);
  }

  private boolean allowed(String linkId) {
    if (createMap.containsKey(linkId)) {
      return false;
    }
    return true;
  }

  private Response shortenUrl(Request request) {
    if (!request.isValidRequest(HttpMethod.POST)) {
      return new Response(ResponseType.BAD_REQUEST, null);
    }

    String linkID = request.getLinkID();
    String longURL = request.getURL();

    if (linkID == null || linkID.trim().length() == 0) {
      linkID = generateRandomLinkID();
    } else if (!allowed(linkID)) {
      if (!resolveMap.get(linkID).equals(longURL)) {
        return new Response(ResponseType.CONFLICT, null);
      }
    }

    linkID = "http://sho.rt/" + linkID;
    createMap.put(longURL, linkID);
    resolveMap.put(linkID, longURL);

    return new Response(ResponseType.CREATED, linkID);
  }

  private Response resolveUrl(Request request) {
    if (!request.isValidRequest(HttpMethod.GET)) {
      return new Response(ResponseType.BAD_REQUEST, null);
    }
    String shortURL = request.getURL();
    String longURL = resolveMap.get(shortURL);
    Response response = longURL == null ?
                        new Response(ResponseType.NOT_FOUND, null) :
                        new Response(ResponseType.FOUND, longURL);
    return response;
  }

  public Response handleRequest(Request request) {
    if (RequestType.CREATE == request.getRequestType()) {
      return shortenUrl(request);
    } else if (RequestType.RESOLVE == request.getRequestType()) {
      return resolveUrl(request);
    } else {
      return new Response(ResponseType.BAD_REQUEST, null);
    }
  }

  public static void main(String[] args) {
    UrlShortener urls = new UrlShortener();
    Scanner in = new Scanner(System.in);
    System.out.println("");
    while (true) {
      String linkID = null, URL = null;
      RequestType requestType = null;
      HttpMethod httpMethod = null;
      System.out.print("Enter Request Type      : ");
      String req = in.nextLine();
      requestType = req.equalsIgnoreCase("CREATE") ? RequestType.CREATE :
                    req.equalsIgnoreCase("RESOLVE") ? RequestType.RESOLVE : null;
      System.out.print("Enter URL               : ");
      URL = in.nextLine();
      if (RequestType.CREATE == requestType) {
        System.out.print("Enter linkID (optional) : ");
        linkID = in.nextLine();
        httpMethod = HttpMethod.POST;
      } else if (RequestType.RESOLVE == requestType) {
        httpMethod = HttpMethod.GET;
      }
      Request request = new Request(linkID, URL, requestType, httpMethod);
      Response response = urls.handleRequest(request);
      if (response.getResponseType() == ResponseType.CREATED) {
        System.out.println("Shortened URL           : " + response.getURL());
      } else if (response.getResponseType() == ResponseType.FOUND) {
        System.out.println("Resolved  URL           : " + response.getURL());
      } else {
        System.out.println(response.getResponseType().toString());
      }
      System.out.println();
    }
  }

}

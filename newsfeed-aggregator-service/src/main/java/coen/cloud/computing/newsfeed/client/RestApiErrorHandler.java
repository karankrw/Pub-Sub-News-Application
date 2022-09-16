package coen.cloud.computing.newsfeed.client;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestApiErrorHandler extends DefaultResponseErrorHandler {

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    System.out.println("Error in api call: ");
    System.out.println("Response code: "+response.getStatusCode()+ " message : "+
            response.getBody().toString());
  }
}

package coen.cloud.computing.newsfeed.client;

import coen.cloud.computing.newsfeed.config.ApplicationConfiguration;
import coen.cloud.computing.newsfeed.model.common.Article;
import coen.cloud.computing.newsfeed.model.common.ArticleRequest;
import coen.cloud.computing.newsfeed.model.nytimes.NyTimesApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class BrokerServiceClient {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApplicationConfiguration appConfig;

  public void publishArticlesToBroker(List<Article> articles, String topicName) {
    restTemplate.setErrorHandler(new RestApiErrorHandler());
    System.out.println("Publishing articles for topic " + topicName);

    String url = buildUrl(topicName);
    System.out.println("Url for broker: "+url);

    HttpEntity<ArticleRequest> request =
            new HttpEntity<>(new ArticleRequest(articles), null);

    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request,String.class);
    if(!responseEntity.getStatusCode().is2xxSuccessful()){
      System.out.println("Unable to post articles.");
      return;
    }
    System.out.println("Response from brokerService: "+responseEntity.getBody());
    System.out.println("Successfully posted articles");
    return;
  }

  private String buildUrl(String topicName){
    return new StringBuilder(appConfig.getBrokerServiceBaseUrl())
            .append("/")
            .append(topicName)
            .append(appConfig.getBrokerServicePath())
            .toString();
  }

}

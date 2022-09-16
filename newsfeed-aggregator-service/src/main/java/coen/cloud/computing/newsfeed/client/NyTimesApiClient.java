package coen.cloud.computing.newsfeed.client;

import coen.cloud.computing.newsfeed.config.ApplicationConfiguration;
import coen.cloud.computing.newsfeed.helper.ArticleMapper;
import coen.cloud.computing.newsfeed.model.common.Article;
import coen.cloud.computing.newsfeed.model.nytimes.NyTimesApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NyTimesApiClient {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApplicationConfiguration appConfig;


  public List<Article> getArticlesForTopic(String topicName) {
    restTemplate.setErrorHandler(new RestApiErrorHandler());
    System.out.println("Fetching all articles from NY Times for topicName " + topicName);
    List<Article> result = new ArrayList<>();
    String uri = buildUrl(topicName).toString();
    System.out.println("Url formed: " + uri);
    ResponseEntity<NyTimesApiResponse> responseEntity = restTemplate.getForEntity(uri, NyTimesApiResponse.class);
    if (!responseEntity.getStatusCode().is2xxSuccessful()) {
      System.out.println("Error in fetching details from NY times API");
      if (responseEntity.getBody() != null && responseEntity.getBody().getFault() != null)
        System.out.println("Error returned: " + responseEntity.getBody().getFault().getFault());
      return result;
    }
    responseEntity.getBody().getResponse().getDocs().forEach(a -> result.add(ArticleMapper.mapNyTimesToArticle(a)));
    System.out.println("Fetched " + result.size() + " articles for topic " + topicName);
    return result;
  }

  private URI buildUrl(String query) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(appConfig.getNyTimes().getBaseUrl());
    for (Map.Entry<String, String> queryParam : appConfig.getNyTimes().getQueryParameters().entrySet()) {
      builder.queryParam(queryParam.getKey(), queryParam.getValue());
    }
    builder.queryParam("q", query);
    return builder.build().toUri();
  }

}

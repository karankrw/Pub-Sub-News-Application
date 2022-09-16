package coen.cloud.computing.newsfeed.service;

import coen.cloud.computing.newsfeed.client.BrokerServiceClient;
import coen.cloud.computing.newsfeed.client.NyTimesApiClient;
import coen.cloud.computing.newsfeed.model.common.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleListAggregatorImpl implements ArticleListAggregator {

  @Autowired
  private NyTimesApiClient nyTimesClient;

  @Autowired
  private BrokerServiceClient brokerServiceClient;

  @Override
  public void getArticlesAndPublish(String topicName) {

    List<Article> articles = nyTimesClient.getArticlesForTopic(topicName);

    System.out.println("Publishing all articles");

    brokerServiceClient.publishArticlesToBroker(articles, topicName);
  }
}

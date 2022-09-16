package coen.cloud.computing.newsfeed.controller;

import coen.cloud.computing.newsfeed.model.common.Article;
import coen.cloud.computing.newsfeed.service.ArticleListAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

  @Autowired
  private ArticleListAggregator aggregator;

  @GetMapping("/publish/{topicName}")
  public ResponseEntity<String> publishArticles(@PathVariable String topicName) {
    aggregator.getArticlesAndPublish(topicName);
    return new ResponseEntity<>("Articles generated for topic "+topicName, HttpStatus.OK);
  }
}

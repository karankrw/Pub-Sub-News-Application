package coen.ds.newsfeed.controller;

import coen.ds.newsfeed.broker.NewsfeedBroker;
import coen.ds.newsfeed.model.Article;
import coen.ds.newsfeed.model.ArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

  @Autowired
  private NewsfeedBroker newsfeedBroker;

  @GetMapping("/{topicName}/subscribe")
  public ResponseEntity<String> subscribeTopic(@RequestParam String userId, @PathVariable String topicName) {
    return newsfeedBroker.subscribeTopic(topicName.toLowerCase(), userId);
  }

  @GetMapping("/{topicName}/unsubscribe")
  public ResponseEntity<String> unsubscribeTopic(@RequestParam String userId, @PathVariable String topicName) {
    return newsfeedBroker.unsubscribeTopic(topicName.toLowerCase(), userId);
  }

  @PostMapping("/{topicName}/publish/article")
  public ResponseEntity<String> publishToTopic(@RequestBody Article article, @PathVariable String topicName) {
    return newsfeedBroker.publish(topicName.toLowerCase(), article);
  }

  @PostMapping("/{topicName}/publish/articles")
  public ResponseEntity<String> publishListToTopic(@RequestBody ArticleRequest articles, @PathVariable String topicName) {
    return newsfeedBroker.publishList(topicName.toLowerCase(), articles);
  }


}

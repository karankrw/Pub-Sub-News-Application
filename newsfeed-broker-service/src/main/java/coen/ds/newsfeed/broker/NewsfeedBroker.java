package coen.ds.newsfeed.broker;

import coen.ds.newsfeed.model.Article;
import coen.ds.newsfeed.model.ArticleRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NewsfeedBroker {

  private ConcurrentHashMap<String, Vector<String>> topicToUserMap;

  @Autowired
  private Jedis jedisClient;

  @Autowired
  private ObjectMapper objectMapper;

  public NewsfeedBroker() {
    topicToUserMap = new ConcurrentHashMap<>();
    topicToUserMap.put("sports", new Vector<>());
    topicToUserMap.put("politics", new Vector<>());
    topicToUserMap.put("entertainment", new Vector<>());
    topicToUserMap.put("technology", new Vector<>());
  }

  public ResponseEntity<String> subscribeTopic(String topicName, String userId) {
    System.out.println("Subscribing " + userId + " to topic " + topicName);
    if (!topicToUserMap.containsKey(topicName)) {
      System.out.println("Error topic not present");
      return new ResponseEntity<>("Topic not present", HttpStatus.BAD_REQUEST);
    }

    if (topicToUserMap.get(topicName).contains(userId)) {
      System.out.println("User already subscribed to topic");
      return new ResponseEntity<>("User already subscribed to topic", HttpStatus.BAD_REQUEST);
    }

    topicToUserMap.get(topicName).add(userId);
    return new ResponseEntity<>("User subscribed to topic", HttpStatus.OK);
  }

  public ResponseEntity<String> unsubscribeTopic(String topicName, String userId) {
    System.out.println("Unsubscribing " + userId + " to topic " + topicName);

    if (!topicToUserMap.containsKey(topicName)) {
      System.out.println("Error topic not present");
      return new ResponseEntity<>("Topic not present", HttpStatus.BAD_REQUEST);
    }

    if (!topicToUserMap.get(topicName).contains(userId)) {
      System.out.println("User isn't subscribed to topic");
      return new ResponseEntity<>("User isn't subscribed to topic", HttpStatus.BAD_REQUEST);
    }

    topicToUserMap.get(topicName).remove(userId);
    System.out.println("User unsubscribed to topic");
    return new ResponseEntity<>("User unsubscribed from topic", HttpStatus.OK);
  }

  public ResponseEntity<String> publish(String topicName, Article article) {
    System.out.println("Publishing message " + article + " to topic " + topicName);
    if (!topicToUserMap.containsKey(topicName)) {
      System.out.println("Error topic not present. Ignoring message");
      return new ResponseEntity<>("Topic not present", HttpStatus.BAD_REQUEST);
    }
    for (String userId : topicToUserMap.get(topicName)) {
      System.out.println("Pushing message for user " + userId + " for the topic " + topicName);
      try {
        jedisClient.lpush(userId, objectMapper.writeValueAsString(article));
      } catch (JsonProcessingException e) {
        System.out.println("Unable to add article as json. Ignoring message");
        break;
      }
    }
    System.out.println("Added message for all users following topic " + topicName);
    return new ResponseEntity<>("Message processed", HttpStatus.OK);
  }

  public ResponseEntity<String> publishList(String topicName, ArticleRequest articles) {
    System.out.println("Publishing messages " + articles + " to topic " + topicName);
    if (!topicToUserMap.containsKey(topicName)) {
      System.out.println("Error topic not present. Ignoring messages");
      return new ResponseEntity<>("Topic not present", HttpStatus.BAD_REQUEST);
    }

    for (Article article : articles.getArticles()) {
      for (String userId : topicToUserMap.get(topicName)) {
        System.out.println("Pushing message "+ article.getId()+" for user " + userId + " for the topic " + topicName);
        try {
          jedisClient.lpush(userId, objectMapper.writeValueAsString(article));
        } catch (JsonProcessingException e) {
          System.out.println("Unable to add article as json. Ignoring message");
          break;
        }
      }
    }
    return new ResponseEntity<>("Messages processed", HttpStatus.OK);
  }

}

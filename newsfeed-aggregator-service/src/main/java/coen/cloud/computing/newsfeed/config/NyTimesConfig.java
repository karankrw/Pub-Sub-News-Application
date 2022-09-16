package coen.cloud.computing.newsfeed.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter @Setter
@ToString
public class NyTimesConfig {

  private String baseUrl;
  private Map<String, String> queryParameters;

}

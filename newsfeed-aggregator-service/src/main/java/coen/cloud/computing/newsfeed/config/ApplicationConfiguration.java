package coen.cloud.computing.newsfeed.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter @Setter
public class ApplicationConfiguration {

  private NyTimesConfig nyTimes;

  @Value("${brokerServiceUrl}")
  private String brokerServiceBaseUrl;

  @Value("${brokerServicePath}")
  private String brokerServicePath;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

}

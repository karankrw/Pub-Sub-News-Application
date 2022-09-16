package coen.ds.newsfeed.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.ThreadExecutorMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter @Setter
public class Config {

  @Value("${redisCache.host}")
  private String redisHost;

  @Value("${redisCache.port}")
  private Integer redisPort;

  @Bean
  Jedis jedisTemplate() {
    return new Jedis(redisHost, redisPort);
  }

  @Bean
  ObjectMapper objectMapper() { return new ObjectMapper();}

}

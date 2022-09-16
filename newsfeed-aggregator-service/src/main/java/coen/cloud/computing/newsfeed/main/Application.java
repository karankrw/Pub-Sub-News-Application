package coen.cloud.computing.newsfeed.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("coen.cloud.computing.newsfeed.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {


  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}

package coen.ds.newsfeed.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("coen.ds.newsfeed.*")
public class Application {

	
  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}

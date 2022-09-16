package coen.cloud.computing.newsfeed.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Article {

  private String id;
  private String title;
  private String author;
  private String date;
  private String description;
  private String webUrl;
}

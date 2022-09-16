package coen.cloud.computing.newsfeed.model.common;

import lombok.*;

import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class ArticleRequest {

  private List<Article> articles;
}

package coen.cloud.computing.newsfeed.model.nytimes;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Response {
  List<DocArticle> docs;
}

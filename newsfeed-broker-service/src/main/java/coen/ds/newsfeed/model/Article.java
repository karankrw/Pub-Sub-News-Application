package coen.ds.newsfeed.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@ToString @AllArgsConstructor @NoArgsConstructor
public class Article {
    private String id;
    private String title;
    private String description;
    private String author;
    private String date;
    private String webUrl;
}

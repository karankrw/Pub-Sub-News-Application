package coen.cloud.computing.newsfeed.model.nytimes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class NyTimesApiResponse {

  private Fault fault;
  private String status;
  private Response response;

}

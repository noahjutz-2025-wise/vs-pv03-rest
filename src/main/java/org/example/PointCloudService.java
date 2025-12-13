package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("point-clouds")
public class PointCloudService {
  @GET
  @Path("health")
  public String health() {
    return "Hello World!";
  }
}

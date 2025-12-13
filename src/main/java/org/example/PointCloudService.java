package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.example.entity.Point;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("points")
public class PointCloudService {
  @GET
  @Path("health")
  public String health() {
    return "Hello World!";
  }

  @POST
  public Optional<Double> processPointCloud(List<Point> points) {
    final var distance =
        points.stream()
            .map(p -> distance(p.getX(), p.getY(), p.getZ()))
            .min(Comparator.naturalOrder());

    if (distance.isPresent() && distance.get() <= 10) {
      return distance;
    }
    return Optional.empty();
  }

  private double distance(double x, double y, double z) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }
}

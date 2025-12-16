package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.Point;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Path("points")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointService {
  @POST
  public Optional<Double> processPointCloud(List<Point> points) {
    final var distance =
        points.stream()
            .map(p -> distance(p.getX(), p.getY(), p.getZ()))
            .min(Comparator.naturalOrder());

    return distance.filter(it -> it <= 10);
  }

  private double distance(double x, double y, double z) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }
}

package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.Optional;
import org.example.entity.PointCloud;

@Path("point-clouds")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointCloudService {
  @POST
  public Optional<Double> processPointCloud(PointCloud pointCloud) {
    final var distance =
        pointCloud.getPoints().stream()
            .map(p -> distance(p.getX(), p.getY(), p.getZ()))
            .min(Comparator.naturalOrder());

    return distance.filter(it -> it <= 10);
  }

  private double distance(double x, double y, double z) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }
}

package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import org.example.entity.PointCloud;
import org.example.entity.Warning;

@Path("point-clouds")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PointCloudService {
  private final AtomicReference<Optional<Warning>> warning =
      new AtomicReference<>(Optional.empty());

  @POST
  public void processPointCloud(PointCloud pointCloud) {
    final var distance =
        pointCloud.getPoints().stream()
            .map(p -> distance(p.getX(), p.getY(), p.getZ()))
            .min(Comparator.naturalOrder());

    if (distance.map(it -> it <= 10).orElse(false)) {
      warning.set(Optional.of(new Warning(distance.get())));
    }
  }

  @GET
  public Optional<Warning> getWarning() {
    final var w = warning.get();
    warning.set(Optional.empty());
    return w;
  }

  private double distance(double x, double y, double z) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }
}

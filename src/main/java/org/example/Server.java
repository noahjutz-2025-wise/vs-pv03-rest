package org.example;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
  static void main() throws IOException {
    final var config = new ResourceConfig();
    config.register(LidarWarningService.class);
    final var server = HttpServer.create(new InetSocketAddress(8080), 0);
    final var handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
    server.createContext("/api/v1", handler);
    server.start();
  }
}

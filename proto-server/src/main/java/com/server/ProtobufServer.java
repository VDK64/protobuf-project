package com.server;

import io.grpc.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * public static void main(String[] args) throws IOException, InterruptedException {
 *      final ProtobufServer server = new ProtobufServer(services);
 *      server.start();
 *      server.blockUntilShutdown();
 * }
 */
public class ProtobufServer {

    private Server server;

    private final int port;

    private final List<BindableService> services;

    public ProtobufServer(int port, List<BindableService> services) {
        this.port = port;
        this.services = services;
    }

    public void start() throws IOException {
        ServerBuilder<?> serverBuilder = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create());
        services.forEach(serverBuilder::addService);
        server = serverBuilder.build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ProtobufServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }


    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}

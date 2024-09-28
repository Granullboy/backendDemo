package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static final String BASE_URI = "http://localhost:8080/";
    private static final int THREAD_POOL_SIZE = 10;

    public void start() {
        final ResourceConfig rc = new ResourceConfig().packages("com.example.controller");

        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        System.out.println("Server started at " + BASE_URI + "...");
        try {
            server.start();
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.shutdownNow();
            threadPool.shutdown();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}

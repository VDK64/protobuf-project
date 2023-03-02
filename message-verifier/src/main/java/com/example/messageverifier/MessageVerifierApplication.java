package com.example.messageverifier;

import com.server.ProtobufServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MessageVerifierApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MessageVerifierApplication.class);

    @Autowired
    private ProtobufServer protobufServer;

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(MessageVerifierApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("MessageVerifier application listening on port {}", env.getProperty("protobuf.server.port"));
        protobufServer.start();
        protobufServer.blockUntilShutdown();
    }

}
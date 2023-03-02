package com.proto.client;

import com.example.demo.Description;
import com.example.demo.MessengerGrpc;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext().build();

        MessengerGrpc.MessengerBlockingStub stub = MessengerGrpc.newBlockingStub(channel);
        Description.Message message = Description.Message.newBuilder().setFrom("me").setRqId(15).build();
        Empty empty = stub.sendMessage(message);
    }

}

package com.example.messageverifier.config;

import com.example.demo.MessengerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageVerifierApplicationConfiguration {

    @Value("${verifier}")
    private String verifier;

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder.forTarget(verifier)
                .usePlaintext().build();
    }

    @Bean
    public MessengerGrpc.MessengerBlockingStub stub(ManagedChannel channel) {
        return MessengerGrpc.newBlockingStub(channel);
    }

}

package com.example.messengerclient.config;

import com.example.demo.MessageVerifierGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerClientConfiguration {

    @Value("${verifier}")
    private String verifier;

    @Bean
    public ManagedChannel channel() {
        return ManagedChannelBuilder.forTarget(verifier)
                .usePlaintext().build();
    }

    @Bean
    public MessageVerifierGrpc.MessageVerifierBlockingStub stub(ManagedChannel channel) {
        return MessageVerifierGrpc.newBlockingStub(channel);
    }

}

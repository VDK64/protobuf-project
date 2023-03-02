package com.example.messengerclient;

import com.example.demo.Description;
import com.example.demo.MessageVerifierGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class MessageScheduler {

    private final MessageVerifierGrpc.MessageVerifierBlockingStub stub;

    @Autowired
    public MessageScheduler(MessageVerifierGrpc.MessageVerifierBlockingStub stub) {
        this.stub = stub;
    }

    @Scheduled(cron = "*/3 * * * * *")
    public void messageScheduler() {
        stub.verifyMessage(Description.Message.newBuilder()
                .setPayload(generateString())
                .setRqId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .setFrom("9772223344")
                .setTo("9805556677")
                .build());
    }


    public String generateString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(random.nextInt(5, 110))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}

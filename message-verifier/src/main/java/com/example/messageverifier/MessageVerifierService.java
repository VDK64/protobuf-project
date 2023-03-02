package com.example.messageverifier;

import com.example.demo.Description;
import com.example.demo.MessageVerifierGrpc;
import com.example.demo.MessengerGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.Description.VerifyResponse.Status.ERROR;
import static com.example.demo.Description.VerifyResponse.Status.SUCCESS;

@Service
public class MessageVerifierService extends MessageVerifierGrpc.MessageVerifierImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MessageVerifierService.class);

    private final MessengerGrpc.MessengerBlockingStub stub;

    @Autowired
    public MessageVerifierService(MessengerGrpc.MessengerBlockingStub stub) {
        this.stub = stub;
    }

    public void verifyMessage(Description.Message request,
                              StreamObserver<Description.VerifyResponse> responseObserver) {


        String payload = request.getPayload();
        if (payload.matches("[^']{100}")) {
            responseObserver.onNext(Description.VerifyResponse.newBuilder()
                    .setStatus(ERROR)
                    .build());
            logger.info("Request PAYLOAD is {}", request.getPayload());
        } else {
            responseObserver.onNext(Description.VerifyResponse.newBuilder()
                    .setStatus(SUCCESS)
                    .build());
            sendMessageToMessenger(request);
        }
        responseObserver.onCompleted();
    }

    private void sendMessageToMessenger(Description.Message request) {
        stub.sendMessage(Description.Message.newBuilder()
                .setPayload(request.getPayload())
                .setRqId(request.getRqId())
                .setFrom(request.getFrom())
                .setTo(request.getTo())
                .build());
    }

}

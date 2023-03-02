package com.example.messenger;

import com.example.demo.Description;
import com.example.demo.MessengerGrpc;
import com.example.messenger.entities.Message;
import com.example.messenger.repository.MessageRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessengerService extends MessengerGrpc.MessengerImplBase {

    private final MessageRepository messageRepository;

    @Autowired
    public MessengerService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void sendMessage(Description.Message request,
                            StreamObserver<Empty> responseObserver) {

        messageRepository.save(new Message()
                .setRqId(request.getRqId())
                .setFrom(request.getFrom())
                .setTo(request.getTo())
                .setPayLoad(request.getPayload()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}

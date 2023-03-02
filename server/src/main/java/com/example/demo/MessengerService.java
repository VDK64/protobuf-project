package com.example.demo;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class MessengerService extends MessengerGrpc.MessengerImplBase {

    @Override
    public void sendMessage(com.example.demo.Description.Message request, StreamObserver<Empty> responseObserver) {
        System.out.println(request);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

}

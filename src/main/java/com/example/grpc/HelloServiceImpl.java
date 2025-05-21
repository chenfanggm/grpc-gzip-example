package com.example.grpc;

import io.grpc.stub.StreamObserver;
import io.grpc.stub.ServerCallStreamObserver;


public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        // Force gzip compression for this response
        if (responseObserver instanceof ServerCallStreamObserver) {
            ServerCallStreamObserver<?> serverCallObserver = (ServerCallStreamObserver<?>) responseObserver;
            serverCallObserver.setCompression("gzip");
        }

        // Create a large text message (example)
        StringBuilder largeMessage = new StringBuilder();
        largeMessage.append("Hello, ").append(request.getName()).append("! Here is a large text:\n");
        for (int i = 0; i < 1000; i++) {
            largeMessage.append("Line ").append(i).append(": This is some repeated text to make the message large.\n");
        }

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(largeMessage.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
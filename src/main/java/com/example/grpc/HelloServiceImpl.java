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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("HelloHelloHelloHello1234567890");
        }
        String compressible = sb.toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setMessage(compressible.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
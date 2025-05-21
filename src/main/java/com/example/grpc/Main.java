package com.example.grpc;

import com.example.grpc.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;


public class Main {
    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
            .addService(new HelloServiceImpl())
            .addService(ProtoReflectionService.newInstance())
            .intercept(new CompressionInterceptor())
            // Enable gzip compressor and decompressor registries (gzip is enabled by default actually)
            .compressorRegistry(CompressorRegistry.getDefaultInstance())
            .decompressorRegistry(DecompressorRegistry.getDefaultInstance())
            .build();

        server.start();
        System.out.println("Server started on port 50051");
        server.awaitTermination();
    }
}
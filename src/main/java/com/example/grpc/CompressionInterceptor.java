package com.example.grpc;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class CompressionInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next) {
        
        ServerCall<ReqT, RespT> compressedCall = new SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void sendHeaders(Metadata headers) {
                // Check if compression is already set
                String encoding = headers.get(Metadata.Key.of("grpc-encoding", Metadata.ASCII_STRING_MARSHALLER));
                System.out.println("grpc-encoding header: " + encoding);
                // setCompression("gzip");  // force gzip compression on this call
                super.sendHeaders(headers);
            }
        };

        return next.startCall(compressedCall, headers);
    }
}
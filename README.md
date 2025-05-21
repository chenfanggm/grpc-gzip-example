# Run Server

1. Compile

```
mvn clean compile
```

2. Run

```
mvn exec:java -Dexec.mainClass="com.example.grpc.Main"
```

# grpcurl

```
grpcurl -plaintext -v -H "grpc-accept-encoding: gzip" localhost:50051 HelloService.SayHello
```

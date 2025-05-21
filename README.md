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

# nghttp

```
echo -n $'\0\0\0\0\0' | nghttp -v -d - \
  -H 'grpc-accept-encoding: gzip' \
  -H 'content-type: application/grpc' \
  http://localhost:50051/HelloService/SayHello
```

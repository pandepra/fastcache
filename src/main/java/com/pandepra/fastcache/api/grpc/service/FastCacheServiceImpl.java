package com.pandepra.fastcache.api.grpc.service;

import com.pandepra.fastcache.ds.RBTreeMap;
import com.pandepra.proto.FastCacheProtos.GetRequest;
import com.pandepra.proto.FastCacheProtos.GetResponse;
import com.pandepra.proto.FastCacheProtos.PutRequest;
import com.pandepra.proto.FastCacheProtos.PutResponse;
import com.pandepra.proto.FastCacheServiceGrpc.FastCacheServiceImplBase;
import io.grpc.stub.StreamObserver;

public class FastCacheServiceImpl extends FastCacheServiceImplBase {

  private RBTreeMap<String, String> cache = new RBTreeMap<>();

  @Override
  public void put(PutRequest request, StreamObserver<PutResponse> responseObserver) {
    System.out.println(request.toString());
    cache.put(request.getKey(), request.getValue());
    responseObserver.onCompleted();
  }

  @Override
  public void get(GetRequest request, StreamObserver<GetResponse> responseObserver) {
    String val = cache.get(request.getKey());
    GetResponse response = GetResponse.newBuilder().setVal(val).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}

package com.pandepra.fastcache.api.rest.controller;

import com.pandepra.fastcache.api.rest.model.PutRequest;
import com.pandepra.fastcache.core.Cache;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import java.io.IOException;

@RestController
@RequestMapping(path = "/fc")
public class CacheController {

  @PostMapping(path = "/put/{key}/{value}")
  public DeferredResult<ResponseEntity<String>> put(
      @PathVariable String key, @PathVariable String val, @RequestHeader String traceId) {

    DeferredResult<ResponseEntity<String>> dr = new DeferredResult<>();
    PutRequest putRequest = new PutRequest().key(key).value(val);

    Mono.just(putRequest)
        .map(req -> Cache.getInstance().put(key, val))
        .subscribeOn(Schedulers.parallel())
        .subscriberContext(Context.of("traceId", traceId))
        .subscribe();

    return dr;
  }

  @GetMapping(path = "/get/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<ResponseEntity<String>> get(
      @PathVariable String key, @RequestHeader String traceId) {
    DeferredResult<ResponseEntity<String>> dr = new DeferredResult<>();
    return dr;
  }
}

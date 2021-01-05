package com.pandepra.fastcache.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping(path = "/doc", produces = MediaType.APPLICATION_JSON_VALUE)
public class PutCacheController {
}

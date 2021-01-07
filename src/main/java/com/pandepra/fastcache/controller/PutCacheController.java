package com.pandepra.fastcache.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/doc", produces = MediaType.APPLICATION_JSON_VALUE)
public class PutCacheController {
}

package com.pandepra.fastcache.api.rest.model;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true, fluent = true)
public class PutRequest {
  private String key;
  private String value;
}

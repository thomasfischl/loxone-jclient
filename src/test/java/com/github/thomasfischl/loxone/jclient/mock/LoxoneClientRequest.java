package com.github.thomasfischl.loxone.jclient.mock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoxoneClientRequest {

  private String url;

  private String response;

}

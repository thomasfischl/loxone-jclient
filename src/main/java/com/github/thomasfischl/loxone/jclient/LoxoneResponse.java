package com.github.thomasfischl.loxone.jclient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LoxoneResponse {

  private String control;

  private String value;

  @SerializedName(value = "Code")
  private String code;

  public int getValueAsInt() {
    return Integer.parseInt(value);
  }

  public double getValueAsDouble() {
    return Double.parseDouble(value);
  }

  public List<LoxoneObject> getValueAsObjects() {
    Iterable<String> values = Splitter.on("),").omitEmptyStrings().trimResults().split(value);

    List<LoxoneObject> result = new ArrayList<>();
    for (String value : values) {

      Matcher m = Pattern.compile("([\\w\\(\\)\\. ]*)\\(([\\w\\.,]*)\\)?").matcher(value);

      if (m.matches()) {
        result.add(new LoxoneObject(m.group(0).trim(), m.group(1).trim()));
      }

    }
    return result;
  }

  public int getCodeAsInt() {
    return Integer.parseInt(code);
  }

}

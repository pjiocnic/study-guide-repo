package com.example.lambda.config;

import java.util.HashMap;
import java.util.Map;

/** Minimal JSON parser for a flat object like {"k":"v","x":"y"}; use Jackson for complex secrets. */
class JsonMini {
  static Map<String,String> parseFlatObject(String json) {
    Map<String,String> out = new HashMap<>();
    if (json == null) return out;
    String s = json.trim();
    if (s.startsWith("{")) s = s.substring(1);
    if (s.endsWith("}")) s = s.substring(0, s.length()-1);
    if (s.isEmpty()) return out;
    for (String part : s.split(",")) {
      String[] kv = part.split(":", 2);
      if (kv.length == 2) {
        String k = strip(kv[0]);
        String v = strip(kv[1]);
        out.put(k, v);
      }
    }
    return out;
  }
  private static String strip(String s) {
    s = s.trim();
    if (s.startsWith(""") && s.endsWith(""") && s.length() >= 2) {
      s = s.substring(1, s.length()-1);
    }
    return s;
  }
}

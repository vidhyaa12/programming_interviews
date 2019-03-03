package io.branch.core;

import java.util.HashMap;
import java.util.Map;

public class Utils {

  /**
   * Takes a uri and parses the query params out into a map of String -> String
   * @param uri
   * @return
   */
  static Map<String, String> parseQueryParams(String uri) {
    HashMap<String, String> queryParams = new HashMap<>();
    if (uri.split("\\?").length == 1){
      return queryParams;
    }
    String query = uri.split("\\?")[1];
    System.out.println(query);
    for(String paramPair:query.split("\\&")) {
      int idx = paramPair.indexOf('=');
      String queryParamKey = paramPair.substring(0, idx + 1);
      queryParams.put(queryParamKey, paramPair.substring(idx + 1));
//      String[] kv = paramPair.split("=");
//      queryParams.put(kv[0], kv[1]);
    }


    return queryParams;
  }

}

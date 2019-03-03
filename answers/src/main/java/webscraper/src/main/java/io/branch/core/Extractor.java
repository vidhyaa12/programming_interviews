//package io.branch.core;
//
//import io.branch.db.ScrapeResponse;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Stream;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.DataNode;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//public class Extractor {
//
//  public static ScrapeResponse extractData(String shtml, String uri) {
//    Document doc = Jsoup.parse(shtml);
//    Map<String, Object> parsedData = new HashMap<>();
//    parsedData
//        .put("canonical_url", getOneAttribute(doc, "href", "link[rel=canonical]").orElse(null));
//    parsedData
//        .put("fb_app_id", getOneAttribute(doc, "content", "meta[property=fb:app_id]").orElse(null));
//    parsedData
//        .put("description", getOneAttribute(doc, "content", "meta[name=description]").orElse(null));
//    parsedData
//        .put("twitter_data", getOneAttribute(doc, "divs", "div:not(:has(div))").orElse(null));
//    parsedData.put("og_data", getAttributeMap(doc, "property", "content", "meta[property^=og:]"));
//    parsedData
//        .put("applink_data", getAttributeMap(doc, "property", "content", "meta[property^=al:]"));
//    parsedData
//        .put("twitter_data", getAttributeMap(doc, "name", "content", "meta[name^=twitter:app]"));
//    parsedData
//        .put("key_data", getBranchKeyData(doc));
//    return new ScrapeResponse(Utils.parseQueryParams(uri), parsedData);
//  }
//
//  private static Map<String, String> getBranchKeyData(Document doc) {
//    Map<String, String> keyData = new HashMap<>();
//    Elements scriptElements = doc.select("script");
//    Pattern p = Pattern.compile(".*\\..*\\('(key_(live|test)_([a-zA-Z0-9]*))'");
//    for (Element element : scriptElements ){
//      for (DataNode node : element.dataNodes()) {
//        Matcher m = p.matcher(node.toString());
//        if(m.find(0)) {
//          keyData.put("full_key", m.group(1));
//          keyData.put("env", m.group(2));
//          keyData.put("hash", m.group(3));
//        }
//      }
//    }
//    return keyData;
//  }
//
//  private static Optional<String> getOneAttribute(Document doc, String attr, String... css) {
//    return Stream.of(css).flatMap(q -> doc.select(q).stream()).map(e -> e.attr(attr))
//        .filter(v -> !isNullOrEmtpy(v)).findFirst();
//  }
//
//  private static Map<String, String> getAttributeMap(Document doc, String keyAttr, String valueAttr,
//      String... css) {
//    return Stream.of(css)
//        .flatMap(q -> doc.select(q).stream())
//        .filter(e -> !isNullOrEmtpy(e.attr(keyAttr))
//            && !isNullOrEmtpy(e.attr(valueAttr)))
//        .collect(HashMap::new, (m, e) -> {
//          m.putIfAbsent(e.attr(keyAttr), e.attr(valueAttr));
//        }, (u, v) -> {
//          throw new IllegalStateException(String.format("Duplicate key %s", u));
//        });
//  }
//
//  private static boolean isNullOrEmtpy(String v) {
//    return ("".equals(v) || v == null);
//  }
//}
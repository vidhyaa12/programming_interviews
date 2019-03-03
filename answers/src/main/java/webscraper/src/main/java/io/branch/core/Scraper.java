//package io.branch.core;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.branch.core.Extractor;
//import io.branch.db.ScrapeResponse;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.charset.UnsupportedCharsetException;
//import java.util.concurrent.CompletableFuture;
//import javax.ws.rs.WebApplicationException;
//import org.eclipse.jetty.client.HttpClient;
//import org.eclipse.jetty.client.HttpRequest;
//import org.eclipse.jetty.client.HttpResponse;
//import org.eclipse.jetty.client.api.Request;
//import org.eclipse.jetty.client.api.Response;
//import org.eclipse.jetty.client.api.Result;
//import org.eclipse.jetty.client.util.BufferingResponseListener;
//import org.eclipse.jetty.client.util.FutureResponseListener;
//import org.eclipse.jetty.http.HttpMethod;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Scraper {
//  static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);
//
//  private final HttpClient http;
//
//  public Scraper(HttpClient http){
//    this.http = http;
//  }
//
//  public CompletableFuture<String> scrapeUri(String uri) {
//    CompletableFuture<String> future = new CompletableFuture<>();
//    http.newRequest(uri).method(HttpMethod.GET).send(new BufferingResponseListener() {
//      @Override
//      public void onComplete(Result result) {
//        if (result.isFailed()) {
//          Throwable ex = result.getFailure();
//          Throwable cause = ex.getCause() == null ? ex : ex.getCause();
//          LOGGER.warn("Failed to scrape " + uri, cause);
//          future.completeExceptionally(cause);
//        } else {
//          LOGGER.debug("Scraped {}", uri);
//          String content = decodeContent(this.getContent(), this.getEncoding());
//          future.complete(content);
//        }
//      }
//    });
//    return future;
//  }
//
//  private String decodeContent(byte[] content, String encoding) {
//    if (content != null && content.length != 0) {
//      if (encoding == null) {
//        return new String(content, StandardCharsets.UTF_8);
//      } else {
//        try {
//          return new String(content, encoding);
//        } catch (UnsupportedEncodingException var3) {
//          throw new UnsupportedCharsetException(encoding);
//        }
//      }
//    } else {
//      return null;
//    }
//  }
//
//
//}
//package io.branch.db;
//
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//public class ScrapeResponse {
//  private Map<String, Object> scrapeData;
//  private Map<String, String> queryParams;
//
//  public Map<String, Object> getScrapeData() {
//    return scrapeData;
//  }
//
//  public void setScrapeData(Map<String, Object> scrapeData) {
//    this.scrapeData = scrapeData;
//  }
//
//  public Map<String, String> getQueryParams() {
//    return queryParams;
//  }
//
//  public void setQueryParams(Map<String, String> queryParams) {
//    this.queryParams = queryParams;
//  }
//
//  public ScrapeResponse() {
//  }
//
//  public ScrapeResponse(Map<String, String> queryParams, Map<String, Object> scrapeData) {
//    this.queryParams = queryParams;
//    this.scrapeData = scrapeData;
//  }
//
//
//}

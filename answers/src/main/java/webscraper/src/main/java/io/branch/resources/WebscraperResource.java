//package io.branch.resources;
//
//import io.branch.core.Extractor;
//import io.branch.core.Scraper;
//import java.util.Optional;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.container.AsyncResponse;
//import javax.ws.rs.container.Suspended;
//import javax.ws.rs.core.MediaType;
//import com.codahale.metrics.annotation.Timed;
//import io.branch.db.ScrapeResponse;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@Path("/v1")
//@Api("webscraper")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class WebscraperResource {
//
//  private final Scraper scraper;
//
//  public WebscraperResource(Scraper scraper) {
//    this.scraper = scraper;
//  }
//
//  @GET
//  @Timed
//  @Path("/scrape")
//  @ApiOperation(
//      value = "Scrapes a url and provides a structured object of data in response",
//      response = ScrapeResponse.class)
//  public void scrape(@QueryParam("url") Optional<String> scrapeUrl, @Suspended final AsyncResponse asyncResponse) {
//    String url = scrapeUrl.orElse(null);
//    if (url == null || "".equals(url)){
//      throw new WebApplicationException("Missing required parameter 'url'", 400);
//    }
//    scraper.scrapeUri(url)
//        .thenApply(html->Extractor.extractData(html, url))
//        .whenComplete((ok, ex) -> {
//          if (ex != null){
//            asyncResponse.resume(ex);
//          } else {
//            asyncResponse.resume(ok);
//          }
//        });
//  }
//
//
//
//}

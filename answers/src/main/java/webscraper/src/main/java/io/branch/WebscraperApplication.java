//package io.branch;
//
//import io.branch.core.Scraper;
//import io.branch.resources.WebscraperResource;
//import io.dropwizard.Application;
//import io.dropwizard.lifecycle.Managed;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;
//import io.federecio.dropwizard.swagger.SwaggerBundle;
//import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
//import org.eclipse.jetty.client.HttpClient;
//import org.eclipse.jetty.util.ssl.SslContextFactory;
//
//public class WebscraperApplication extends Application<WebscraperConfiguration> {
//
//  public static void main(final String[] args) throws Exception {
//    new WebscraperApplication().run(args);
//  }
//
//  @Override
//  public String getName() {
//    return "webscraper";
//  }
//
//  @Override
//  public void initialize(final Bootstrap<WebscraperConfiguration> bootstrap) {
//    bootstrap.addBundle(new SwaggerBundle<WebscraperConfiguration>() {
//      @Override
//      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(WebscraperConfiguration configuration) {
//        return configuration.swaggerBundleConfiguration;
//      }
//    });
//  }
//
//  @Override
//  public void run(final WebscraperConfiguration configuration,
//      final Environment environment) {
//    HttpClient http = new HttpClient(new SslContextFactory());
//    environment.lifecycle().manage(new Managed() {
//      @Override
//      public void start() throws Exception {
//        http.start();
//      }
//
//      @Override
//      public void stop() throws Exception {
//        http.stop();
//      }
//    });
//    Scraper scraper = new Scraper(http);
//    environment.jersey().register(new WebscraperResource(scraper));
//  }
//
//}

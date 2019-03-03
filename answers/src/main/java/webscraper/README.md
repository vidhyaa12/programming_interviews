
How to start the webscraper application
---
1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/webscraper-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080/swagger`

### Notes about making requests
The request format for a scrape is `http://<server url>/1/scrape?url=<encoded url>`
If the value of `?url` is not encoded, then the URL parameters in the tests won't be properly decoded.

### Debugging

A customer has identified that for some urls, the query parameters returned in the JSON response do not match the values on the URL itself.

Url: `https://branch.io/content?refdata=Xbasdwhasda/=dsfedah2e2ad/fasd==&secondary=click`

Example Request:

```
curl -vvv localhost:8080/v1/scrape?url=https%3A%2F%2Fbranch.io%2Fcontent%3Frefdata%3DXbasdwhasda%2F%3Ddsfedah2e2ad%2Ffasd%3D%3D%26secondary%3Dclick
```

Customer's expected response:

```
{
  "scrape_data": {
    "fb_app_id": null,
    "og_data": {
      "og:image": "https://branch.io/img/open-graph/og-img.png",
      "og:type": "website",
      "og:title": "4-0-4 - Whoops | Branch",
      "og:url": "https://branch.io/404.html",
      "og:description": "Build high quality user experiences with best-in-class deep linking, all while measuring the complete journey of your users. From desktop to mobile web to native app, at every touch point across organic and paid channels, Branch is the platform for mobile growth."
    },
    "description": "Build high quality user experiences with best-in-class deep linking, all while measuring the complete journey of your users. From desktop to mobile web to native app, at every touch point across organic and paid channels, Branch is the platform for mobile growth.",
    "twitter_data": {},
    "applink_data": {},
    "canonical_url": null,
    "key_data": {}
  },
  "query_params": {
    "secondary": "click",
    "refdata": "Xbasdwhasda/=dsfedah2e2ad/fasd=="
  }
}
```


Another customer reported that some requests to the webscraper take several seconds or longer to complete, with some hanging indefinitely. We'd like to debug further and get all urls to return data in less than 2 seconds (on average):
Urls:
  - https://airbnb.com
  - https://foursquare.com/v/4840c7c2f964a5202d501fe3
  - https://jet.com

# actual response
Vidhyaas-MacBook-Air:webscraper vidhyaa$ curl -vvv localhost:8080/v1/scrape?url=https%3A%2F%2Fbranch.io%2Fcontent%3Frefdata%3DXbasdwhasda%2F%3Ddsfedah2e2ad%2Ffasd%3D%3D%26secondary%3Dclick
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> GET /v1/scrape?url=https%3A%2F%2Fbranch.io%2Fcontent%3Frefdata%3DXbasdwhasda%2F%3Ddsfedah2e2ad%2Ffasd%3D%3D%26secondary%3Dclick HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.49.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Date: Fri, 26 Oct 2018 21:33:20 GMT
< Content-Type: application/json
< Vary: Accept-Encoding
< Content-Length: 894
<
* Connection #0 to host localhost left intact
{"scrape_data":{"fb_app_id":null,"og_data":{"og:image":"https://branch.io/img/open-graph/og-img.png","og:type":"website","og:title":"4-0-4 - Whoops | Branch","og:url":"https://branch.io/404.html","og:description":"Build high quality user experiences with best-in-class deep linking, all while measuring the complete journey of your users. From desktop to mobile web to native app, at every touch point across organic and paid channels, Branch is the platform for mobile growth."},"description":"Build high quality user experiences with best-in-class deep linking, all while measuring the complete journey of your users. From desktop to mobile web to native app, at every touch point across organic and paid channels, Branch is the platform for mobile growth.","twitter_data":{},"applink_data":{},"canonical_url":null,"key_data":{}},"query_params":{"secondary":"click","refdata":"Xbasdwhasda/"}}Vidhyaas-MacBook-Air:webscraper vidhyaa$
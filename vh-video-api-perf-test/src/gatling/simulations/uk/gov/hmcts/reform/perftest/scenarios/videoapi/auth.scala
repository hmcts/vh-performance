package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object auth {
  val scope = ".default";

  val baseUrl = "-dev.azurewebsites.net";
  val header = Map("Content-Type" -> """application/x-www-form-urlencoded""");
  val authURI = "https://login.microsoftonline.com";


  val auth = scenario("GetToken")
    .exec(http("Microsoft Token Generation")
      .post(s"$authURI/" +"${tenant}"+ s"/oauth2/v2.0/token")
      .formParam("scope", "${videoApiUrl}"+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", "${clientSecret}")
      .formParam("client_id", "${clientId}")
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerx")))

  val httpProtocol = http
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36")
    .shareConnections
  val headers = {
    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearerx}")
  }
}

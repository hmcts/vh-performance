package uk.gov.hmcts.reform.perftest.scenarios.bookingapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.perftest.utils.Environment

object auth {

  val tenant        = Environment.tenantId;
  val scope         = ".default";
  val header        = Map("Content-Type" -> """application/x-www-form-urlencoded""");
  val authURI       = "https://login.microsoftonline.com";


  val userauth = scenario("User GetToken")
    .exec(http("Microsoft Token Generation-User")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", s"https://"+Environment.userBaseURL+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", Environment.userClientSecret)
      .formParam("client_id", Environment.userClientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearer")))


  val Bookingauth = scenario("Service GetToken")
    .exec(http("Microsoft Token Generation-Service")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", s"https://"+Environment.bookingBaseURL+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", Environment.bookingsClientSecret)
      .formParam("client_id", Environment.bookingsClientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearers")))

  val videoAuth = scenario("VideoApi GetToken")
    .exec(http("Microsoft Token Generation")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", s"https://"+Environment.videoApiBaseURL+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", Environment.videoClientSecret)
      .formParam("client_id", Environment.videoClientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerxs")))

  val testApiAuth = scenario("User GetToken")
    .exec(http("Microsoft Token Generation-User")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", s"https://"+Environment.testApiBaseURL+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", Environment.testApiClientSecret)
      .formParam("client_id", Environment.testApiClientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearerxst")))


  val httpProtocol  = http.acceptHeader("application/json").acceptEncodingHeader("gzip, deflate, br").userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36").shareConnections
  val headers       = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearer}")   }
  val headersrv     = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearers}")  }
  val headersrvx     = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearerxs}")  }
  val headersrvxt     = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearerxst}")  }
}

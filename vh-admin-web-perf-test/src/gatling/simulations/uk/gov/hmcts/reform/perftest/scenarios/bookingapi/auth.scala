package uk.gov.hmcts.reform.perftest.scenarios.bookingapi

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.adminweb.scenarios.utils.Environment

object auth {

  val tenant        = Environment.tenantId;
  val scope         = ".default";
  val header        = Map("Content-Type" -> """application/x-www-form-urlencoded""");
  val authURI       = "https://login.microsoftonline.com";


  val Bookingauth = scenario("Service GetToken")
    .exec(http("Microsoft Token Generation-Service")
      .post(s"$authURI/$tenant/oauth2/v2.0/token")
      .formParam("scope", s"https://"+Environment.bookingBaseURL+s"/$scope")
      .formParam("grant_type", "client_credentials")
      .formParam("client_secret", Environment.bookingsClientSecret)
      .formParam("client_id", Environment.bookingsClientId)
      .headers(header).check(status.is(200))
      .check(jsonPath("$..access_token").saveAs("bearers")))


  val httpProtocol  = http.acceptHeader("application/json").acceptEncodingHeader("gzip, deflate, br").userAgentHeader("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36").shareConnections
  val headers       = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearer}")   }
  val headersrv     = {    Map("Content-Type" -> """application/json""", "Authorization" -> "bearer ${bearers}")  }
}

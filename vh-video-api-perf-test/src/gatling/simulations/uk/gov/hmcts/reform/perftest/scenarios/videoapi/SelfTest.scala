package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SelfTest  {
  def SelfTestScenario() = {
    exec(http("SelfTest-SelfTest").get("${videoApiUrl}/selftest").headers(auth.headers).check(status.is(200)))
    .pause(Environment.minTime,Environment.maxTime)
  }
}

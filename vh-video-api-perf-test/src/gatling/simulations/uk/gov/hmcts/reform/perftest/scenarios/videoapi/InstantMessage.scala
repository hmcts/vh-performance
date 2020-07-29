package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object InstantMessage{

  def InstantMessageScenario () = {
       exec(http("InstantMsg-Msg").get("${videoApiUrl}/conferences/${conf_Idx}/instantmessages").headers(auth.headers)
      .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("InstantMsg-Save").post("${videoApiUrl}/conferences/${conf_Idx}/instantmessages").headers(auth.headers)
      .body(StringBody(
                """{
                      "from": "${participant_Idx}",
                      "message_text": "Video Api Perf test Save Instant Message",
                      "to": "TestRecipient"
            					}""")).asJson
      .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("InstantMsg-Expired")
      .get("${videoApiUrl}/conferences/expiredIM").headers(auth.headers).check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("InstantMsg-Delete").delete("${videoApiUrl}/conferences/${conf_Idx}/instantmessages").headers(auth.headers)
      .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)
  }
}

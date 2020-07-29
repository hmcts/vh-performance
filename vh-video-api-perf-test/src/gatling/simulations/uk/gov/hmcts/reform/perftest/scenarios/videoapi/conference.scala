package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object conference {
  val DynamicVideoApiFeeder = Feeders.DynamicVideoApiFeeder

  def BookConference() = {
    feed(DynamicVideoApiFeeder)
      .exec(http("Conference-BookConf").post("${videoApiUrl}/conferences").headers(auth.headers)
        .body(ElFileBody("data/videoApiData/ConferenceCreate.json")).asJson
        .check(status.is(session => 201)).check(jsonPath("$.id").saveAs("conf_Idx"))
        .check(jsonPath("$.participants[0].id").saveAs("participant_Idx"))
        .check(jsonPath("$.participants[0].ref_id").saveAs("participant_RefIdx"))
        .check(jsonPath("$.id").saveAs("Hearing_RefIdx"))
        .check(jsonPath("$.participants[1].username").saveAs("Judge_usernamex")))
      .pause(Environment.minTime, Environment.maxTime)
  }
  def ConferenceChecks() = {
      exec(http("Conference-Update").put("${videoApiUrl}/conferences").headers(auth.headers)
        .body(ElFileBody("data/videoApiData/ConferenceUpdate.json")).asJson
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Conference-ConfToday").get("${videoApiUrl}/conferences/today/vho").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Conference-ByHearingId").get("${videoApiUrl}/conferences/hearings/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Conference-Expired").get("${videoApiUrl}/conferences/expired").headers(auth.headers).check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Conference-AllConfToday").get("${videoApiUrl}/conferences/today/judge?username=${Judge_usernamex}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Conference-OpenConf").get("${videoApiUrl}/conferences/today/individual?username=${Judge_usernamex}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def ConferenceClose() = {
      exec(http("Conference-Close").put("${videoApiUrl}/conferences/${conf_Idx}/close").headers(auth.headers).check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def ConferenceDelete() = {
      exec(http("Conference-Delete").delete("${videoApiUrl}/conferences/${conf_Idx}").headers(auth.headers).check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def ConferenceGetDetails() = {
     exec(http("Conference-GetConfDetails")
    .get("${videoApiUrl}/conferences/${conf_Idx}").headers(auth.headers).check(bodyString.transform(_.size > 2).is(true)).check(status.is(200))
    .check(jsonPath("$.participants[2].id").saveAs("secondparticipant_Idx"))
    .check(jsonPath("$.participants[2].ref_id").saveAs("secondparticipant_Ref_Idx"))
    .check(jsonPath("$.participants[2].username").saveAs("secondparticipant_usernamex")))
    .pause(Environment.minTime,Environment.maxTime)
  }

}

package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Participants {
  def ParticipantsScenario() = {
    exec(http("Participants-Add").put("${videoApiUrl}/conferences/${conf_Idx}/participants").headers(auth.headers)
    .body(ElFileBody("data/videoApiData/ParticipantAdd.json")).asJson
    .check(status.is(204)))
    .pause(Environment.minTime,Environment.maxTime)

    .exec(http("Participants-Patch")
    .patch("${videoApiUrl}/conferences/${conf_Idx}/participants/${participant_Idx}").headers(auth.headers)
    .body(ElFileBody("data/videoApiData/ParticipantPatchData.json")).asJson
    .check(status.is(204)))
    .pause(Environment.minTime,Environment.maxTime)

    /*.exec(http("Participants-SelfTest")
    .get("""/conferences/${conf_Idx}/participants/${participant_Idx}/selftestresult""").headers(auth.headers)
    .check(status.is(200)))
    .pause(Environment.minTime,Environment.maxTime)*/


    .exec(http("Participants-Heartbeat")
      .post("${videoApiUrl}/conferences/${conf_Idx}/participant/${participant_Idx}/heartbeat").headers(auth.headers)
      .body(ElFileBody("data/videoApiData/ParticipantHeartbeatData.json")).asJson
      .check(status.is(204)))
    .pause(Environment.minTime,Environment.maxTime)

    .exec(http("Participants-GetHeartBeat")
      .get("${videoApiUrl}/conferences/${conf_Idx}/participant/${participant_Idx}/heartbeatrecent").headers(auth.headers).check(status.is(200)))
    .pause(Environment.minTime,Environment.maxTime)

    /*.exec(http("Participants-IndependentSelfTestResult")
    .get("${videoApiUrl}/conferences/independentselftestresult?participantId=${participant_Idx}").headers(auth.headers)
    .check(status.is(200)))
    .pause(Environment.minTime,Environment.maxTime)*/
  }

    def ParticipantsDelete() = {
         exec(http("Participants-Delete")
        .delete("${videoApiUrl}/conferences/${conf_Idx}/participants/${participant_Idx}").headers(auth.headers)
        .check(status.is(204)))
        .pause(Environment.minTime,Environment.maxTime)

      }
}
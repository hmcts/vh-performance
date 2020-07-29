package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object VideoEvents {
  def VideoEventScenario() = {
    /*exec(http("VideoEvent-Accept_Publish")
      .post("${videoApiUrl}/conferences/${conf_Idx}/tasks/${taskIdx}").headers(auth.headers)
      .body(StringBody(
        """{
            "event_id": "string",
            "event_type": "None",
            "time_stamp_utc": "${scheduled_date_timex}",
            "conference_id": "${conf_Idx}",
            "participant_id": "${participant_Idx}",
            "transfer_from": "WaitingRoom1",
            "transfer_to": "WaitingRoom2",
            "reason": "Perf Test Video Event Waiting Room Transfer"
					}"""))
      .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)*/

    exec(http("VideoEvent-Participant_One_Joined")
      .post("${videoApiUrl}/events").headers(auth.headers)
      .body(StringBody(
        """{
      "event_id": "3bb2a0a1-0677-40a3-96c6-e5f6f447d37b",
      "event_type": "Joined",
      "time_stamp_utc":  "2020-05-20T15:51:46.282Z",
      "conference_id": "${conf_Idx}",
     "participant_id": "${participant_Idx}",
      "transfer_from": "HearingRoom",
      "transfer_to": "WaitingRoom",
      "reason": "Joined"
    }"""))
      .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("VideoEvent-Participant_Second_Joined")
        .post("${videoApiUrl}/events").headers(auth.headers)
        .body(StringBody(
          """{
      "event_id": "3bb2a0a1-0677-40a3-96c6-e5f6f447d37b",
      "event_type": "Joined",
      "time_stamp_utc":  "2020-05-20T15:51:46.282Z",
      "conference_id": "${conf_Idx}",
     "participant_id": "${secondparticipant_Idx}",
      "transfer_from": "HearingRoom",
      "transfer_to": "WaitingRoom",
      "reason": "Joined"
    }"""))
        .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)
  }
}

package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object consultation {
  def ConsultationScenario() = {
      exec(http("Consultation-Consult").post("${videoApiUrl}/consultations").headers(auth.headers)
      .body(StringBody(
            """{
            "conference_id": "${conf_Idx}",
            "requested_by": "${secondparticipant_Idx}",
            "requested_for": "${participant_Idx}",
            "answer": "Accepted"
            }""")).asJson
      .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec( http("Consultation-Respond")
        .post("${videoApiUrl}/consultations/vhofficer/respond").headers(auth.headers)
        .body(StringBody(
          """{
                "conference_id": "${conf_Idx}",
                "participant_id": "${participant_Idx}",
                "consultation_room": "ConsultationRoom1",
                "answer": "Accepted"
                }""")).asJson
        .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("Consultation-Leave")
      .post("${videoApiUrl}/consultations/leave").headers(auth.headers)
      .body(StringBody(
              """{
    						  "conference_id": "${conf_Idx}",
                  "participant_id": "${secondparticipant_Idx}"
    					    }""")).asJson
      .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)
  }
}

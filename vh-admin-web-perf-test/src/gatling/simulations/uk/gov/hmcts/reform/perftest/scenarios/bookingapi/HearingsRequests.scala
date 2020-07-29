package uk.gov.hmcts.reform.perftest.scenarios.bookingapi

import uk.gov.hmcts.reform.perftest.scenarios.Feeders
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.adminweb.scenarios.utils.Environment


object HearingsRequests {

  val ServiceFeeder = Feeders.Feederx
  val ServiceCsvFeeder = csv("data/credentials.csv").circular

  val bookingUrl  = Environment.bookingURL


  def set_feeders()= {
    feed(ServiceFeeder)
      .feed(ServiceCsvFeeder)
  }


  def create_hearing()= {
       exec(http("Booking-Create.Hearing").post(bookingUrl+"/hearings").headers(auth.headersrv)
        .body(ElFileBody("data/CreateHearing.json")).asJson
        .check(jsonPath("$.id").saveAs("SrvHearingRefIdx"))
        .check(jsonPath("$.cases[0].number").saveAs("SrvCaseNox"))
        .check(jsonPath("$.cases[0].name").saveAs("SrvCaseNamex"))
        .check(jsonPath("$.participants[1].username").saveAs("IndParticipantNamex"))
        .check(jsonPath("$.participants[1].id").saveAs("IndParticipantIdx"))
         .check(jsonPath("$.participants[2].username").saveAs("RepParticipantNamex"))
         .check(jsonPath("$.participants[2].id").saveAs("RepParticipantIdx"))
        .check(status.is(session => 201)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def claimant_questionnaire()= {
    exec(http("Booking-Claimant.Questionnaire").put(bookingUrl+"/hearings/${BookedHearingRefIdx}/participants/${IndParticipantIdx}/suitability-answers").headers(auth.headersrv)
      .body(ElFileBody("data/UpdateQuestionnaire.json")).asJson
      .check(status.is(session => 204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def solicitor_questionnaire()= {
    exec(http("Booking-Solicitor.Questionnaire").put(bookingUrl+"/hearings/${BookedHearingRefIdx}/participants/${RepParticipantIdx}/suitability-answers").headers(auth.headersrv)
      .body(ElFileBody("data/UpdateQuestionnaire.json")).asJson
      .check(status.is(session => 204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_hearing()= {
       exec(http("Booking-Delete.Hearing")
         .delete(bookingUrl+"/hearings/${BookedHearingRefIdx}")
         .headers(auth.headersrv)
      .check(status.is(session => 204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

}

package uk.gov.hmcts.reform.perftest.simulation

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.bookingapi._
import uk.gov.hmcts.reform.perftest.scenarios.serviceweb._
import uk.gov.hmcts.reform.perftest.utils.Environment
import scala.concurrent.duration._


class CleanUpScenarios  extends Simulation {

  val csvFeeder = csv("data/deleteList.csv").circular

  val ServiceWebSCN = scenario("CleanUp-Web-Perf-Test")
    .exec(HearingsRequests.set_feeders())

    .exec(auth.userauth)
    .repeat(8){
      feed(csvFeeder)
         .exec(HearingsRequests.delete_user())
    }

//    .exec( session => session.set("PerformanceUserName", "Performance00.Performance00@hearings.reform.hmcts.net"))
//    .exec( session => session.set("SrvHearingRefIdx", "B9A3B374-F418-403C-99EF-DE6F4E7AE1DE"))

//    .exec(auth.Bookingauth)
//    .repeat(1050){
//      feed(csvFeeder)
//        .exec(HearingsRequests.delete_hearing())
//    }

//    .exec( session => session.set("SrvHearingRefIdx", "B9A3B374-F418-403C-99EF-DE6F4E7AE1DE"))
//    .exec( session => session.set("ParticipantId", "B9A3B374-F418-403C-99EF-DE6F4E7AE1DE"))
//    .exec(auth.Bookingauth, HearingsRequests.delete_hearing_participant())
//    .repeat(48){
//      feed(csvFeeder)
//        .exec(HearingsRequests.delete_hearing_participant())
//    }

//    .exec(auth.videoAuth)
//    .repeat(1015){
//      feed(csvFeeder)
//        .exec(HearingsRequests.delete_conference())
//    }

//    .exec( session => session.set("SrvHearingRefIdx", "B9A3B374-F418-403C-99EF-DE6F4E7AE1DE"))
//    .exec( session => session.set("ParticipantId", "B9A3B374-F418-403C-99EF-DE6F4E7AE1DE"))
//    .exec(auth.videoAuth, HearingsRequests.delete_conference_participant())
//    .repeat(1){
//      feed(csvFeeder)
//        .exec(HearingsRequests.delete_conference_participant())
//    }

  setUp(ServiceWebSCN.inject(atOnceUsers(1))).protocols(Environment.httpProtocol)
//    setUp(ServiceWebSCN.inject(rampUsers(5) during (75 seconds))).protocols(Environment.httpProtocol).maxDuration(5 minutes)
}
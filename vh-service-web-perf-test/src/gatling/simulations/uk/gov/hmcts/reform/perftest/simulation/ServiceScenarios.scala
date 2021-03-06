package uk.gov.hmcts.reform.perftest.simulation

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.bookingapi._
import uk.gov.hmcts.reform.perftest.scenarios.serviceweb.ServiceRequests
import uk.gov.hmcts.reform.perftest.utils.Environment


class ServiceScenarios  extends Simulation {

  val csvFeeder = csv("data/deleteList.csv").circular

  val ServiceWebSCN = scenario("Service-Web-Perf-Test")
    //        .forever() {
    .exec(HearingsRequests.set_feeders())
    .exec(auth.testApiAuth, HearingsRequests.test_api_allocate_user())
    .exec(HearingsRequests.test_api_create_hearing())
    .exec(auth.userauth,HearingsRequests.setIndividualFirstName,HearingsRequests.setIndividualLastName)
    .exec(HearingsRequests.update_password())
    .exec(HearingsRequests.setIndividualUserName)
    .exec(ServiceRequests.Home())
    .exec(ServiceRequests.LoginReset())
    .exec(ServiceRequests.Questionaire())
    .exec(ServiceRequests.Logout())
    .exec(HearingsRequests.setRepresentativeFirstName,HearingsRequests.setRepresentativeLastName)
    .exec(HearingsRequests.update_password())
    .exec(HearingsRequests.setRepresentativeUserName)
    .exec(ServiceRequests.Home())
    .exec(ServiceRequests.LoginReset())
    .exec(ServiceRequests.Questionaire())
    .exec(ServiceRequests.Logout())
  //        }

  setUp(ServiceWebSCN.inject(atOnceUsers(1))).protocols(Environment.httpProtocol)
  //    setUp(ServiceWebSCN.inject(rampUsers(10) during (300 seconds))).protocols(Environment.httpProtocol).maxDuration(10 minutes)
}
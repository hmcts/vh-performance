package uk.gov.hmcts.reform.perftest.simulation

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.bookingapi._
import uk.gov.hmcts.reform.perftest.scenarios.serviceweb._
import uk.gov.hmcts.reform.perftest.utils.Environment
import scala.concurrent.duration._


class ServiceScenarios  extends Simulation {

  val csvFeeder = csv("data/deleteList.csv").circular

  val ServiceWebSCN = scenario("Service-Web-Perf-Test")
        .forever() {
             exec(HearingsRequests.set_feeders())
            .exec(HearingsRequests.setIndividualUserType, HearingsRequests.setIndividualGenNo, HearingsRequests.setIndividualGenNos)
            .exec(auth.userauth, HearingsRequests.create_user())
            .doIf("${UserRefIdx.exists()}") {
              exec(HearingsRequests.group_users())
                .exec(HearingsRequests.setRepresentativeUserType, HearingsRequests.setRepresentativeGenNo, HearingsRequests.setRepresentativeGenNos)
                .exec(HearingsRequests.create_user())
                .doIf("${UserRefIdx.exists()}") {
                  exec(HearingsRequests.group_users())
                    .exec(auth.Bookingauth, HearingsRequests.create_hearing())
                    .exec(HearingsRequests.setIndividualUserName)
                    .exec(ServiceRequests.Home())
                    .exec(ServiceRequests.LoginReset())
                    .exec(ServiceRequests.Questionaire())
                    .exec(ServiceRequests.Logout())
                    .exec(HearingsRequests.setRepresentativeUserName)
                    .exec(ServiceRequests.Home())
                    .exec(ServiceRequests.LoginReset())
                    .exec(ServiceRequests.Questionaire())
                    .exec(ServiceRequests.Logout())

                }
            }
        }

//  setUp(ServiceWebSCN.inject(atOnceUsers(1))).protocols(Environment.httpProtocol)
    setUp(ServiceWebSCN.inject(rampUsers(100) during (3000 seconds))).protocols(Environment.httpProtocol).maxDuration(100 minutes)
}
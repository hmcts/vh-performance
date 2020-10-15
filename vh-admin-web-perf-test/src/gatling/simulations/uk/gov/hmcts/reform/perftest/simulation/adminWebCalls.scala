package uk.gov.hmcts.reform.simulation

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.adminweb.scenarios.adminWebReq
import uk.gov.hmcts.reform.adminweb.scenarios.utils.Environment
import uk.gov.hmcts.reform.perftest.scenarios.azureblobapi.AzureBlobRequests
import uk.gov.hmcts.reform.perftest.scenarios.bookingapi.{HearingsRequests, auth}
import scala.concurrent.duration._

class adminWebCalls  extends Simulation {

  val adminWebSCN = scenario("Admin-Web-Perf-Test")
            .forever() {
                 exec(adminWebReq.Home())
                .exec(adminWebReq.Login())
                .doIf("${IdTokenx.exists()}") {
                   exec(adminWebReq.BackToDashboard())
                  .exec(adminWebReq.BookVideoHearing())
                  .exec(adminWebReq.HearingDetails())
                  .exec(adminWebReq.Hearingschedule())
                  .exec(adminWebReq.AssignJudge())
                  .exec(adminWebReq.AddParticipant())
                  .exec(adminWebReq.OtherInfo())
                  .exec(adminWebReq.SubmitVideoHearing())
                     .doIf("${BookedHearingRefIdx.exists()}") {
                        exec(adminWebReq.BookingList())
                       .exec(adminWebReq.BookingLitDrilldown())
                       .exec(adminWebReq.SendToVideoApp())
                        .doIf("${ConferenceRefIdx.exists()}") {
                            exec(AzureBlobRequests.upload_blob())
                           .exec(adminWebReq.GetAudioLink())
                           .exec(AzureBlobRequests.delete_blob())
                            exec(auth.TestApiauth,HearingsRequests.test_api_claimant_questionnaire())
                           .exec(HearingsRequests.test_api_solicitor_questionnaire())
                           .exec(adminWebReq.Questnnaire())
                        }
                       .exec(adminWebReq.setIndividualUserName)
                       .exec(adminWebReq.ChangeUserPassword())
                       .exec(adminWebReq.VerifyUser())
                       .exec(adminWebReq.DeleteUser())
                       .exec(adminWebReq.setRepresentativeUserName)
                       .exec(adminWebReq.ChangeUserPassword())
                       .exec(adminWebReq.VerifyUser())
                       .exec(adminWebReq.DeleteUser())
                     }
                  .exec(adminWebReq.Logout())
                  }
            }

//  setUp(adminWebSCN.inject(atOnceUsers(1))).protocols(Environment.httpProtocol)
    setUp(adminWebSCN.inject(rampUsers(10) during (200 seconds))).protocols(Environment.httpProtocol).maxDuration(6 minutes)
}
package uk.gov.hmcts.reform.perftest.simulation

import io.gatling.core.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.bookingapi.{HearingsRequests, auth}
import uk.gov.hmcts.reform.perftest.scenarios.serviceweb.ServiceRequests
import uk.gov.hmcts.reform.perftest.utils.Environment


class MultiHearingScenario  extends Simulation {
  val csvFeeder = csv("data/vhoUsers.csv").circular
  val ServiceWebSCN = scenario("Service-Web-Perf-Test")
              .exec(session =>{
                session.set("observerIndex",0)
              })
              .exec(session =>{
                session.set("panelMemberIndex",0)
              })
              .exec(session =>{
                session.set("claimantIndex",0)
              })
              .exec(session =>{
                session.set("judgeIndex",0)
              })
              .exec(session =>{
                session.set("representativeIndex",0)
              })
              .exec(session =>{
                session.set("vhoIndex",0)
              })
              .repeat(6) {
                  feed(csvFeeder)
                  .exec(HearingsRequests.set_feeders())
                  .exec(auth.userauth, HearingsRequests.create_new_user())
//                  .exec( HearingsRequests.update_password())
                  .exec(HearingsRequests.setUserName)
                  .exec(ServiceRequests.Home())
                  .exec(ServiceRequests.LoginReset())
                  .exec(ServiceRequests.Logout())
                  .exec(session => {
                    session.set("userGroup", session("Role").as[String])
                  })
                  .doIfEquals(session => session("Role").as[String], "Judge") {
                    exec(HearingsRequests.addUserToGroup("Internal"), HearingsRequests.addUserToGroup("VirtualRoomJudge"), HearingsRequests.addUserToGroup("vh_video_kinly_saml2_test1_users"))
                      .exec(HearingsRequests.incrementVar("judgeIndex"))
                      .exec(HearingsRequests.setIndexVar("Judge","judgeIndex"))
                  }
                  .doIfEqualsOrElse(session => session("Role").as[String], "Representative") {
                    exec(HearingsRequests.addUserToGroup("External"), HearingsRequests.addUserToGroup("VirtualRoomProfessionalUser"))
                      .exec(HearingsRequests.incrementVar("representativeIndex"))
                      .exec(HearingsRequests.setIndexVar("Representative","representativeIndex"))
                  }  {
                    exec(HearingsRequests.addUserToGroup("External"))
                      .doIfEquals(session => session("Role").as[String], "Claimant"){
                        exec(HearingsRequests.incrementVar("claimantIndex"))
                          .exec(HearingsRequests.setIndexVar("Claimant","claimantIndex"))
                      }
                      .doIfEquals(session => session("Role").as[String], "PanelMember"){
                        exec(HearingsRequests.incrementVar("panelMemberIndex"))
                          .exec(HearingsRequests.setIndexVar("PanelMember","panelMemberIndex"))
                      }
                      .doIfEquals(session => session("Role").as[String], "Observer"){
                        exec(HearingsRequests.incrementVar("observerIndex"))
                          .exec(HearingsRequests.setIndexVar("Observer","observerIndex"))
                      }
                      .doIfEquals(session => session("Role").as[String], "Vho"){
                        exec(HearingsRequests.addUserToGroup("Internal"),
                              HearingsRequests.addUserToGroup("VirtualRoomAdministrator"),
                                HearingsRequests.addUserToGroup("vh_video_kinly_saml2_test1_users"))
                          .exec(HearingsRequests.incrementVar("vhoIndex"))
                          .exec(HearingsRequests.setIndexVar("Vho","vhoIndex"))
                      }
                  }
              }
             .exec(auth.Bookingauth, HearingsRequests.multi_pariticpants_hearing())

  setUp(ServiceWebSCN.inject(atOnceUsers(1))).protocols(Environment.httpProtocol)
}
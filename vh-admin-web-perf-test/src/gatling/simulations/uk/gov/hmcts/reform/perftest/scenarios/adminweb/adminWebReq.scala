package uk.gov.hmcts.reform.adminweb.scenarios
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.adminweb.scenarios.utils.Environment

object adminWebReq {
  val adminURL      = Environment.adminUrl
  val authURL       = Environment.authUrl
  val AdminWebFeeder = adminWebFeeder.DynamicAdminFeeder
  val AdminWebcsvFeed = csv("data/credentials.csv").circular
  val AdminWebcsvUsers = csv("data/vhoUsers.csv").circular


  def Home()= {
    feed(AdminWebFeeder)
    .feed(AdminWebcsvFeed)
      .feed(AdminWebcsvUsers)
      .exec(http("01.01.Admin.Portal.Home").get(adminURL + "/api/config").headers(adminWebHeaders.headers_0)
        .check(jsonPath("$.client_id").saveAs("clientIdx"))
        .check(jsonPath("$.tenant_id").saveAs("tenantIdx"))
        .check(regex("""redirect_uri":"https://(.*?)/""").saveAs("adminUri")))
        .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.02.Admin.Portal.Home")
        .get(authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri="+adminURL+"/home&state=40bdbd96-17ff-4d6b-93bc-828b316f2c2f&client-request-id=65ac9818-81db-48c9-82f9-9041327f3a46&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=9a34b4ca-f00c-476d-b482-d868b0de901c")
        .headers(adminWebHeaders.headers_1).check(status.is(session => 200)))
        .pause(Environment.minTime,Environment.maxTime)

        .exec(http("01.03.Admin.Portal.Home")
        .get(authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri="+adminURL+"/home&state=40bdbd96-17ff-4d6b-93bc-828b316f2c2f&client-request-id=65ac9818-81db-48c9-82f9-9041327f3a46&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=9a34b4ca-f00c-476d-b482-d868b0de901c&sso_reload=true")
        .headers(adminWebHeaders.headers_1).check(status.is(session => 200))
        .check(regex("""apiCanary":"(.*?)",""").saveAs("apiCanaryIdx"))
        .check(regex("""canary":"(.*?)",""").saveAs("canaryIdx"))
        .check(regex("""hpgid":(.*?),""").saveAs("hpgIdx"))
        .check(regex("""sessionId":"(.*?)",""").saveAs("sessionIdx"))
        .check(regex("""sCtx":"(.*?)",""").saveAs("CtxIdx"))
        .check(regex("""sFT":"(.*?)",""").saveAs("flowTokenx")))
        .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.04.Admin.Portal.Home").post(authURL + "/common/GetCredentialType?mkt=en-GB")
        .headers(adminWebHeaders.headers_2).body(ElFileBody("data/BookLogin.json")).asJson)
        .pause(Environment.minTime,Environment.maxTime)
  }

  def Login()= {
    exec(http("02.00.Admin.Portal.Login")
      .post(authURL + "/${tenantIdx}/login")
      .headers(adminWebHeaders.headers_3)
      .formParam("i13", "0")
      .formParam("login", "${vhoUserId}@${appDomain}")
      .formParam("loginfmt", "${vhoUserId}@${appDomain}")
      .formParam("type", "11")
      .formParam("LoginOptions", "3")
      .formParam("lrt", "")
      .formParam("lrtPartition", "")
      .formParam("hisRegion", "")
      .formParam("hisScaleUnit", "")
      .formParam("passwd", "${passwordx}")
      .formParam("ps", "2")
      .formParam("psRNGCDefaultType", "")
      .formParam("psRNGCEntropy", "")
      .formParam("psRNGCSLK", "")
      .formParam("canary", "${canaryIdx}")
      .formParam("ctx", "${CtxIdx}")
      .formParam("hpgrequestid", "${sessionIdx}")
      .formParam("flowToken", "${flowTokenx}")
      .formParam("PPSX", "")
      .formParam("NewUser", "1")
      .formParam("FoundMSAs", "")
      .formParam("fspost", "0")
      .formParam("i21", "0")
      .formParam("CookieDisclosure", "1")
      .formParam("IsFidoSupported", "1")
      .formParam("isSignupPost", "0")
      .formParam("i2", "1")
      .formParam("i17", "")
      .formParam("i18", "")
      .formParam("i19", "12166")
      .check(headerRegex("Location", """id_token=(.+?)&""").saveAs("IdTokenx"))
      .check(status.is(session => 302).saveAs("RespCodex")))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def Questnnaire()={
    exec(http("17.01.Admin.Portal.Questnnaire").get(adminURL + "/api/user").headers(adminWebHeaders.headers_15).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("17.02.Admin.Portal.Questnnaire")
        .get(adminURL + "/api/suitability-answers?cursor=&limit=100")
        .headers(adminWebHeaders.headers_16)
        .check(jsonPath("$.participant_suitability_answer_response[?(@.participant_id=='${IndParticipantIdx}')].answers").saveAs("claimantAnswers"))
        .check(jsonPath("$.participant_suitability_answer_response[?(@.participant_id=='${RepParticipantIdx}')].answers").saveAs("solicitorAnswers"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def BackToDashboard()={
    exec(http("05.01.Admin.Portal.Back2Dashboard").get(adminURL + "/api/user").headers(adminWebHeaders.headers_12).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("05.02.Admin.Portal.Back2Dashboard").get(adminURL + "/api/user").headers(adminWebHeaders.headers_13).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def BookVideoHearing()={
    exec(http("06.01.Admin.Portal.BookHearing").get(adminURL + "/api/user").headers(adminWebHeaders.headers_18).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("06.02.Admin.Portal.BookHearing").get(adminURL + "/api/reference/types").headers(adminWebHeaders.headers_19).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def HearingDetails()={
    exec(http("07.01.Admin.Portal.HearingDetails").get(adminURL + "/api/user").headers(adminWebHeaders.headers_21).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("07.02.Admin.Portal.HearingDetails").get(adminURL + "/api/reference/courts").headers(adminWebHeaders.headers_22).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def Hearingschedule()={
    exec(http("08.01.Admin.Portal.HearingSchedule").get(adminURL + "/api/user").headers(adminWebHeaders.headers_23).check(status.is(session => 200))).pause(4).pause(Environment.minTime,Environment.maxTime)
      .exec(http("08.02.Admin.Portal.HearingSchedule").get(adminURL + "/api/accounts/judges").headers(adminWebHeaders.headers_24).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def AssignJudge()={
    exec(http("09.01.Admin.Portal.AssignJudge").get(adminURL + "/api/user").headers(adminWebHeaders.headers_25).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def AddParticipant()={
    exec(http("10.01.Admin.Portal.AddParticipant").get(adminURL + "/api/user").headers(adminWebHeaders.headers_26).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("10.03.Admin.Portal.AddParticipant").post(adminURL + "/api/persons").headers(adminWebHeaders.headers_27).body(ElFileBody("data/BookAddParticipantTwo.json")).asJson.check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("10.05.Admin.Portal.AddParticipant").get(adminURL + "/api/user").headers(adminWebHeaders.headers_28).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  def OtherInfo()={
    exec(http("11.01.Admin.Portal.OtherInfo").get(adminURL + "/api/user").headers(adminWebHeaders.headers_29).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  def SubmitVideoHearing()={
    exec(http("12.01.Admin.Portal.SubmitHearing").post(adminURL + "/api/hearings").headers(adminWebHeaders.headers_30)
      .body(ElFileBody("data/BookSubmitOne.json")).asJson
      .check(status.is(session => 201))
      .check(jsonPath("$.id").saveAs("BookedHearingRefIdx"))
      .check(jsonPath("$.cases[0].number").saveAs("SrvCaseNox"))
      .check(jsonPath("$.cases[0].name").saveAs("SrvCaseNamex"))
      .check(jsonPath("$.participants[?(@.user_role_name == 'Individual')].username").saveAs("IndParticipantNamex"))
      .check(jsonPath("$.participants[?(@.user_role_name == 'Individual')].id").saveAs("IndParticipantIdx"))
      .check(jsonPath("$.participants[?(@.user_role_name == 'Representative')].username").saveAs("RepParticipantNamex"))
      .check(jsonPath("$.participants[?(@.user_role_name == 'Representative')].id").saveAs("RepParticipantIdx")))
      .pause(Environment.minTime,Environment.maxTime)
      .doIf("${BookedHearingRefIdx.exists()}") {
        exec(http("12.02.Admin.Portal.SubmitHearing").get(adminURL + "/api/user").headers(adminWebHeaders.headers_31).check(status.is(session => 200))).pause(Environment.minTime, Environment.maxTime)
       .exec(http("12.03.Admin.Portal.SubmitHearing").get(adminURL + "/api/hearings/${BookedHearingRefIdx}").headers(adminWebHeaders.headers_32).check(status.is(session => 200))).pause(Environment.minTime, Environment.maxTime)
      }

  }

  def BookingList()={
    exec(http("13.01.Admin.Portal.Booking.List").get(adminURL + "/api/user").headers(adminWebHeaders.headers_33).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("13.02.Admin.Portal.Booking.List").get(adminURL + "/api/hearings?cursor=0&limit=100").headers(adminWebHeaders.headers_34).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  def BookingLitDrilldown()={
    exec(http("14.01.Admin.Portal.Booking.Drilldown").get(adminURL + "/api/user").headers(adminWebHeaders.headers_35).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("14.02.Admin.Portal.Booking.Drilldown").get(adminURL + "/api/hearings/${BookedHearingRefIdx}").headers(adminWebHeaders.headers_36).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("14.03.Admin.Portal.Booking.Drilldown").get(adminURL + "/api/user").headers(adminWebHeaders.headers_37).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  def SendToVideoApp()={
    exec(http("15.01.Admin.Portal.SendToVideoApp").patch(adminURL +"/api/hearings/${BookedHearingRefIdx}").headers(adminWebHeaders.headers_38).body(ElFileBody("data/BookSendToVideoAppOne.json")).asJson).pause(Environment.minTime,Environment.maxTime)
      .exec(http("15.02.Admin.Portal.SendToVideoApp").get(adminURL +"/api/hearings/${BookedHearingRefIdx}").headers(adminWebHeaders.headers_39)
        .check(jsonPath("$.id").saveAs("ConferenceRefIdx"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  val setIndividualUserName = exec { session =>
    session.set("ParticipantUserName", session("IndParticipantNamex").as[String])
  }

  val setRepresentativeUserName = exec { session =>
    session.set("ParticipantUserName", session("RepParticipantNamex").as[String])
  }

  def ChangeUserPassword()={
    exec(http("15.01.Admin.Portal.ChangeUserPassword").patch(adminURL +"/api/accounts/updateUser")
      .headers(adminWebHeaders.headers_38).body(StringBody(""""${ParticipantUserName}""""))
        .check(jsonPath("$.password").saveAs("ResetPassword"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)

  }

  def VerifyUser()={
    exec(http("15.02.Admin.Portal.VerifyUser").get(adminURL +"/api/persons/username/hearings?username=${ParticipantUserName}")
        .headers(adminWebHeaders.headers_39)
        .check(jsonPath("$..hearing_id").saveAs("ConferenceRefIdx"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def DeleteUser()={
    exec(http("15.02.Admin.Portal.DeleteUser").delete(adminURL +"/api/persons/username/hearings?username=${ParticipantUserName}")
      .headers(adminWebHeaders.headers_39)
      .check(status.is(session => 204))).pause(Environment.minTime,Environment.maxTime)
  }

  def GetAudioLink()={
    exec(http("16.01.Admin.Portal.GetAudioLink").get(adminURL + "/api/user").headers(adminWebHeaders.headers_15).check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("16.02.Admin.Portal.GetAudioLink").get(adminURL + "/api/hearings/casenumber?caseNumber=${SrvCaseNox}")
        .headers(adminWebHeaders.headers_16).check(jsonPath("$[*].id").saveAs("audioRecordingIdx"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
      .exec(http("16.03.Admin.Portal.GetAudioLink").get(adminURL + "/api/audio/${audioRecordingIdx}")
        .headers(adminWebHeaders.headers_16).check(jsonPath("$.audio_file_link").saveAs("audioFileLink"))
        .check(status.is(session => 200))).pause(Environment.minTime,Environment.maxTime)
  }

  def Logout()={
    exec(http("18.00.Admin.Portal.Logout").get(authURL + "/${tenantIdx}/oauth2/logout?post_logout_redirect_uri="+adminURL+"/logout")
      .headers(adminWebHeaders.headers_logout).check(status.is(session => 200)))
      .pause(Environment.consTime)
  }
}

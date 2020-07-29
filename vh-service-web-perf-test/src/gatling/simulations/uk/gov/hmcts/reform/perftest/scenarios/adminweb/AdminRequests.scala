package uk.gov.hmcts.reform.perftest.scenarios.adminweb
import uk.gov.hmcts.reform.perftest.scenarios.Feeders
import uk.gov.hmcts.reform.perftest.utils.Environment
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AdminRequests {
  val adminURL      = Environment.adminUrl
  val vsServiceURL  = Environment.vsServiceUrl
  val authURL       = Environment.authUrl
  val AdminFeeder   = Feeders.Feederx
  val AdminCsvFeed  = csv("data/credentials.csv").circular


  def Home()= {
    feed(AdminFeeder)
      .feed(AdminCsvFeed)
        .exec(http("01.01.Admin.Web.Home").get(adminURL + "/api/config").headers(AdminHeaders.headers_0)
        .check(jsonPath("$.client_id").saveAs("clientIdx"))
        .check(jsonPath("$.tenant_id").saveAs("tenantIdx"))
        .check(regex("""redirect_uri":"https://(.*?)/""").saveAs("adminUri"))
        .check(regex("""validate_email":"(.*?)"""").saveAs("emaildomainx")).check(status.is(session => 200)))
        .pause(Environment.minTime,Environment.maxTime)

        .exec(http("01.02.Admin.Web.Home")
        .get(authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri="+adminURL+"/home&state=40bdbd96-17ff-4d6b-93bc-828b316f2c2f&client-request-id=65ac9818-81db-48c9-82f9-9041327f3a46&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=9a34b4ca-f00c-476d-b482-d868b0de901c")
        .headers(AdminHeaders.headers_1).check(status.is(session => 200)))
        .pause(Environment.minTime,Environment.maxTime)

        .exec(http("01.03.Admin.Web.Home")
        .get(authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri="+adminURL+"/home&state=40bdbd96-17ff-4d6b-93bc-828b316f2c2f&client-request-id=65ac9818-81db-48c9-82f9-9041327f3a46&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=9a34b4ca-f00c-476d-b482-d868b0de901c&sso_reload=true")
        .headers(AdminHeaders.headers_1).check(status.is(session => 200))
        .check(regex("""apiCanary":"(.*?)",""").saveAs("apiCanaryIdx"))
        .check(regex("""canary":"(.*?)",""").saveAs("canaryIdx"))
        .check(regex("""hpgid":(.*?),""").saveAs("hpgIdx"))
        .check(regex("""sessionId":"(.*?)",""").saveAs("sessionIdx"))
        .check(regex("""sCtx":"(.*?)",""").saveAs("CtxIdx"))
        .check(regex("""sFT":"(.*?)",""").saveAs("flowTokenx")))
        .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.04.Admin.Web.Home").post(authURL + "/common/GetCredentialType?mkt=en-GB").headers(AdminHeaders.headers_2).body(ElFileBody("data/admin/BookLogin.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def Login()= {
       exec(http("02.00.Admin.Web.Login")
      .post(authURL + "/${tenantIdx}/login")
      .headers(AdminHeaders.headers_3)
      .formParam("i13", "0")
      .formParam("login", "Performance01.VideoHearingsOfficer00${GenNoxxx}${emaildomainx}")
      .formParam("loginfmt", "Performance01.VideoHearingsOfficer00${GenNoxxx}${emaildomainx}")
      .formParam("type", "11")
      .formParam("LoginOptions", "3")
      .formParam("lrt", "")
      .formParam("lrtPartition", "")
      .formParam("hisRegion", "")
      .formParam("hisScaleUnit", "")
      .formParam("passwd", "${AdminPassword}")
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
       exec(http("04.01.Admin.Web.Questionnaire").get(adminURL + "/api/user").headers(AdminHeaders.headers_15).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("04.02.Admin.Web.Questionnaire").get(adminURL + "/api/suitability-answers?cursor=&limit=100").headers(AdminHeaders.headers_16).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("04.03.Admin.Web.Questionnaire").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookQuestOne.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("04.04.Admin.Web.Questionnaire").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookQuestTwo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def BackToDashboard()={
       exec(http("05.01.Admin.Web.Back2Dashboard").get(adminURL + "/api/user").headers(AdminHeaders.headers_12).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("05.02.Admin.Web.Back2Dashboard").get(adminURL + "/api/user").headers(AdminHeaders.headers_13).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("05.03.Admin.Web.Back2Dashboard").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BackToDashboardOne.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def BookVideoHearing()={
       exec(http("06.01.Admin.Web.BookHearing").get(adminURL + "/api/user").headers(AdminHeaders.headers_18).check(status.is(session => 200)))
       .pause(Environment.minTime,Environment.maxTime)

      .exec(http("06.02.Admin.Web.BookHearing").get(adminURL + "/api/reference/types").headers(AdminHeaders.headers_19).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("06.03.Admin.Web.BookHearing").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookVideoHear.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def HearingDetails()={
       exec(http("07.01.Admin.Web.HearingDetails").get(adminURL + "/api/user").headers(AdminHeaders.headers_21).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("07.02.Admin.Web.HearingDetails").get(adminURL + "/api/reference/courts").headers(AdminHeaders.headers_22).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("07.03.Admin.Web.HearingDetails").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookHearDetailsOne.json")).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("07.04.Admin.Web.HearingDetails").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookHearDetailsTwo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def Hearingschedule()={
       exec(http("08.01.Admin.Web.HearingSchedule").get(adminURL + "/api/user").headers(AdminHeaders.headers_23).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("08.02.Admin.Web.HearingSchedule").get(adminURL + "/api/accounts/judges").headers(AdminHeaders.headers_24).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("08.03.Admin.Web.HearingSchedule").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookSchOne.json")).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("08.04.Admin.Web.HearingSchedule").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookSchTwo.json")).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def AssignJudge()={
       exec(http("09.01.Admin.Web.AssignJudge").get(adminURL + "/api/user").headers(AdminHeaders.headers_25).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("09.02.Admin.Web.AssignJudge").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookAssignJudge.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def AddParticipant()={
       exec(http("10.01.Admin.Web.AddParticipant").get(adminURL + "/api/user").headers(AdminHeaders.headers_26).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("10.02.Admin.Web.AddParticipant").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookAddParticipantOne.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("10.03.Admin.Web.AddParticipant").post(adminURL + "/api/persons").headers(AdminHeaders.headers_27).body(ElFileBody("data/admin/BookAddParticipantTwo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("10.04.Admin.Web.AddParticipant").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookAddParticipantThree.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("10.05.Admin.Web.AddParticipant").get(adminURL + "/api/user").headers(AdminHeaders.headers_28).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("10.06.Admin.Web.AddParticipant").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookAddParticipantFour.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def OtherInfo()={
       exec(http("11.01.Admin.Web.OtherInfo").get(adminURL + "/api/user").headers(AdminHeaders.headers_29).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("11.02.Admin.Web.OtherInfo").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookOtherInfo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def SubmitVideoHearing()={
       exec(http("12.01.Admin.Web.SubmitHearing").post(adminURL + "/api/hearings").headers(AdminHeaders.headers_30).body(ElFileBody("data/admin/BookSubmitOne.json")).asJson.check(status.is(session => 201)).check(jsonPath("$.id").saveAs("BookedHearimgRefIdx")))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("12.02.Admin.Web.SubmitHearing").get(adminURL + "/api/user").headers(AdminHeaders.headers_31).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("12.03.Admin.Web.SubmitHearing").get(adminURL + "/api/hearings/${BookedHearimgRefIdx}").headers(AdminHeaders.headers_32).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("12.04.Admin.Web.SubmitHearing").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookSubmitTwo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def BookingList()={
       exec(http("13.01.Admin.Web.Booking.List").get(adminURL + "/api/user").headers(AdminHeaders.headers_33).check(status.is(session => 200)))
       .pause(Environment.minTime,Environment.maxTime)

      .exec(http("13.02.Admin.Web.Booking.List").get(adminURL + "/api/hearings?cursor=0&limit=100").headers(AdminHeaders.headers_34).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("13.03.Admin.Web.Booking.List").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookListOne.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def BookingLitDrilldown()={
       exec(http("14.01.Admin.Web.Booking.Drilldown").get(adminURL + "/api/user").headers(AdminHeaders.headers_35).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("14.02.Admin.Web.Booking.Drilldown").get(adminURL + "/api/hearings/${BookedHearimgRefIdx}").headers(AdminHeaders.headers_36).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("14.03.Admin.Web.Booking.Drilldown").get(adminURL + "/api/user").headers(AdminHeaders.headers_37).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("14.04.Admin.Web.Booking.Drilldown").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookListDrilldown.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def SendToVideoApp()={
       exec(http("15.01.Admin.Web.SendToVideoApp").patch(adminURL +"/api/hearings/${BookedHearimgRefIdx}").headers(AdminHeaders.headers_38).body(ElFileBody("data/admin/BookSendToVideoAppOne.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("15.02.Admin.Web.SendToVideoApp").get(adminURL +"/api/hearings/${BookedHearimgRefIdx}").headers(AdminHeaders.headers_39).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("15.03.Admin.Web.SendToVideoApp").post(vsServiceURL + "/v2/track").headers(AdminHeaders.headers_6).body(ElFileBody("data/admin/BookSendToVideoAppTwo.json")).asJson.check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
  def Logout()={
       exec(http("16.00.Admin.Web.Logout").get(authURL + "/${tenantIdx}/oauth2/logout?post_logout_redirect_uri="+adminURL+"/logout").headers(AdminHeaders.headers_logout).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
}

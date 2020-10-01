package uk.gov.hmcts.reform.perftest.scenarios.serviceweb
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.perftest.utils.Environment

object ServiceRequests {
  val serviceURL  = Environment.serviceUrl
  val SrvVsURL    = Environment.SrvVsUrl
  val authURL     = Environment.authUrl
  val msLogin     = Environment.mslogin

    val srvWebcsvFeed = csv("data/credentials.csv").circular
    val uri1 = "https://login.live.com"
    val uri2 = "https://secure.aadcdn.microsoftonline-p.com/c1c6b6c8-dinlqikq-eq5gvhx1irb2gxk9rqycmmv1itiowivzt8/logintenantbranding/0/bannerlogo"


  def   Home() = {
       exec(http("01.01.Srv.Web.Home").get(serviceURL + "/api/config").headers(ServiceHeaders.headers_1)
      .check(jsonPath("$.client_id").saveAs("clientIdx")).check(jsonPath("$.tenant_id").saveAs("tenantIdx"))
      .check(regex("""redirect_uri":"https://(.*?)/""").saveAs("srvUri"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.02.Srv.Web.Home").get( authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri=https%3A%2F%2F${srvUri}%2F&state=4255710c-f0ae-4160-9669-7f5511edf805&client-request-id=84afc564-c40e-4dfc-8154-e6aa6b6f356b&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=24df2543-90a0-43be-87a1-27f86673805c").headers(ServiceHeaders.headers_2)
        .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.03.Srv.Web.Home").get( authURL + "/${tenantIdx}/oauth2/authorize?response_type=id_token&client_id=${clientIdx}&redirect_uri=https%3A%2F%2F${srvUri}%2F&state=4255710c-f0ae-4160-9669-7f5511edf805&client-request-id=84afc564-c40e-4dfc-8154-e6aa6b6f356b&x-client-SKU=Js&x-client-Ver=1.0.15&nonce=24df2543-90a0-43be-87a1-27f86673805c&sso_reload=true")
      .headers(ServiceHeaders.headers_3)
      .check(regex("""apiCanary":"(.*?)",""").saveAs("srvApiCanaryIdx"))
      .check(regex("""canary":"(.*?)",""").saveAs("srvCanaryIdx"))
      .check(regex("""hpgid":(.*?),""").saveAs("hpgIdx"))
      .check(regex("""sessionId":"(.*?)",""").saveAs("srvSessionIdx"))
      .check(regex("""sCtx":"(.*?)",""").saveAs("srvCtxIdx"))
      .check(regex("""sFT":"(.*?)",""").saveAs("srvFlowTokenx"))
      .check(regex("""firstname.surname(.*?)",""").saveAs("emaildomainx"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("01.04.Srv.Web.Home").post(authURL+"/common/instrumentation/reportpageload?mkt=en-US").headers(ServiceHeaders.headers_4)
        .body(ElFileBody("data/service/01.04.srv.web.home.json")).asJson
        .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def LoginReset()= {
       exec(http("02.01.Srv.Web.LoginReset").post(authURL + "/common/GetCredentialType?mkt=en-US").headers(ServiceHeaders.headers_5)
      .body(ElFileBody("data/service/02.01.Srv.Web.LoginReset.json")).asJson)
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("02.02.Srv.Web.LoginReset")
        .post(authURL + "/${tenantIdx}/login").headers(ServiceHeaders.headers_31)
        .formParam("i13", "0")
        .formParam("login", "${SrvParticipantNamex}")
        .formParam("loginfmt", "${SrvParticipantNamex}")
        .formParam("type", "11")
        .formParam("LoginOptions", "3")
        .formParam("lrt", "")
        .formParam("lrtPartition", "")
        .formParam("hisRegion", "")
        .formParam("hisScaleUnit", "")
        .formParam("passwd", Environment.oldPassword)
        .formParam("ps", "2")
        .formParam("psRNGCDefaultType", "")
        .formParam("psRNGCEntropy", "")
        .formParam("psRNGCSLK", "")
        .formParam("canary", "${srvCanaryIdx}")
        .formParam("ctx", "${srvCtxIdx}")
        .formParam("hpgrequestid", "${srvSessionIdx}")
        .formParam("flowToken", "${srvFlowTokenx}")
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
        .formParam("i19", "28650")
//        .check(regex("""sCtx":"(.*?)",""").saveAs("srvCtxIdx"))
//        .check(regex("""sFT":"(.*?)",""").saveAs("srvFlowTokenx")))
        .check(headerRegex("Location", """id_token=(.+?)&""").saveAs("srvIdTokenx"))
         .check(status.is(302)))
        .pause(Environment.minTime,Environment.maxTime)

//      .exec(http("02.05.Srv.Web.LoginReset").post(authURL + "/common/SSPR/Begin").headers(ServiceHeaders.headers_8)
//      .body(ElFileBody("data/service/02.02.Srv.Web.LoginReset.json")).asJson
//        .check(jsonPath("$.FlowToken").saveAs("srvFlowTokenx"))
//        .check(jsonPath("$.Ctx").saveAs("srvCtxIdx"))
//        .check(jsonPath("$.CoupledDataCenter").saveAs("srvDataCenter")))
//      .pause(Environment.minTime,Environment.maxTime)
//
//      .exec(http("02.06.Srv.Web.LoginReset").post(authURL + "/common/SSPR/Poll").headers(ServiceHeaders.headers_8)
//      .body(ElFileBody("data/service/02.06.Srv.Web.LoginReset.json")).asJson
//        .check(jsonPath("$.FlowToken").saveAs("srvFlowTokenx"))
//        .check(jsonPath("$.Ctx").saveAs("srvCtxIdx")))
//      .pause(Environment.minTime,Environment.maxTime)
//
//      .exec(http("02.07.Srv.Web.LoginReset")
//      .post(authURL + "/common/SSPR/End")
//      .headers(ServiceHeaders.headers_9)
//      .formParam("ctx", "${srvCtxIdx}")
//      .formParam("hpgrequestid", "${srvSessionIdx}")
//      .formParam("flowToken", "${srvFlowTokenx}")
//      .formParam("canary", "${srvCanaryIdx}")
//      .formParam("currentpasswd", Environment.oldPassword)
//      .formParam("newpasswd", Environment.newPassword)
//      .formParam("confirmnewpasswd", Environment.newPassword)
//      .formParam("i2", "")
//      .formParam("i17", "")
//      .formParam("i18", "")
//      .formParam("i19", "28528")
//      .check(headerRegex("Location", """id_token=(.+?)&""").saveAs("srvIdTokenx"))
//      .check(status.is(302)))
//      .pause(Environment.minTime,Environment.maxTime)

  }

  def Questionaire()= {
        exec(http("03.01.Srv.Web.Questionaire").get(serviceURL + "/").headers(ServiceHeaders.headers_10)
      .check(status.in(200,304)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("03.02.Srv.Web.Questionaire").get(serviceURL + "/api/config").headers(ServiceHeaders.headers_11)
        .check(status.is(session => 200)))

      .exec(http("03.03.Srv.Web.Questionaire").get(serviceURL + "/api/profile").headers(ServiceHeaders.headers_12)
        .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("03.04.Srv.Web.Questionaire")
        .get(serviceURL + "/api/hearing-suitability")
        .headers(ServiceHeaders.headers_14)
        .check(jsonPath("$[*].hearing_id").saveAs("hearingIdx"))
        .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("03.05.Srv.Web.Questionaire")
        .put(serviceURL + s"/api/hearings/"+"${hearingIdx}")
        .headers(ServiceHeaders.headers_14)
        .body(ElFileBody("data/service/03.05.Srv.Web.Questionaire.json")).asJson)
      .pause(Environment.minTime,Environment.maxTime)
  }

  def Logout()={
    exec(http("04.01.Srv.Web.Logout").get(authURL + "/${tenantIdx}/oauth2/logout?post_logout_redirect_uri="+serviceURL+"/logout")
      .headers(ServiceHeaders.headers_logout).check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }

}
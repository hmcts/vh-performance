package uk.gov.hmcts.reform.perftest.scenarios.bookingapi

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.Feeders
import uk.gov.hmcts.reform.perftest.utils.Environment


object HearingsRequests {

  val ServiceFeeder = Feeders.Feederx
  val ServiceCsvFeeder = csv("data/credentials.csv").circular

  val userUrl      = Environment.userURL
  val bookingUrl  = Environment.bookingURL


  def incrementVar(name: String) = {
    exec(session =>{
      val temp = session(name).as[Int] + 1
      session.set(name,temp)
    })
  }

  def setIndexVar(name: String, index: String) = {
    exec(session =>{
      val temp = name + "FirstName" + session(index).as[Int]
      session.set(s"$temp",session("ParticipantFirstName").as[String])
    })
    .exec(session =>{
      val temp = name + "LastName" + session(index).as[Int]
      session.set(s"$temp",session("ParticipantLastName").as[String])
    })
  }

  def addUserToGroup(test: String) = {
    exec(session => { session.set("userGroup", test) })
      .exec(http("Booking-Add.User.Group").patch(userUrl + "/accounts/user/group").headers(auth.headers)
      .body(StringBody(
        """{
              "user_id": "${UserRefIdx}",
              "group_name": "${userGroup}"
              }"""))
      .check(status.in(202,404)))
//      .pause(Environment.minTime,Environment.maxTime)

  }


  val setIndividualUserType = exec { session =>
    session.set("userType", "Claimant")
  }

  val setRepresentativeUserType = exec { session =>
    session.set("userType", "Solicitor")
  }

  val setIndividualGenNo = exec { session =>
    session.set("GenNo", session("GenNox").as[String])
  }

  val setIndividualGenNos = exec { session =>
    session.set("GenNos", session("GenNoxx").as[String])
  }

  val setRepresentativeGenNo = exec { session =>
    session.set("GenNo", session("GenNoxx").as[String])
  }

  val setRepresentativeGenNos = exec { session =>
    session.set("GenNos", session("GenNox").as[String])
  }

  val setIndividualUserName = exec { session =>
    session.set("SrvParticipantNamex", session("IndParticipantNamex").as[String])
  }

  val setUserName = exec { session =>
    session.set("SrvParticipantNamex", session("UsernameOnex").as[String])
  }

  val setRepresentativeUserName = exec { session =>
    session.set("SrvParticipantNamex", session("RepParticipantNamex").as[String])
  }

  def set_feeders()= {
    feed(ServiceFeeder)
      .feed(ServiceCsvFeeder)
  }

  def create_user()= {
      exec(http("Booking-Hearing-${userType}.User.Exists")
        .get(userUrl + "/users/userName/Performance${GenNos}.${userType}${GenNo}@${appDomain}")
          .headers(auth.headers)
          .check(status.saveAs("userExistStatus"))
          .check(status.in(200,404)))
        .pause(Environment.minTime,Environment.maxTime)
      .doIfOrElse(session => session("userExistStatus").as[String] == "404") {
        exec(http("Booking-Hearing-${userType}.User.Registration").post(userUrl + "/users").headers(auth.headers)
          .body(StringBody(
            """{
                  "first_name": "Performance${GenNos}",
                  "last_name": "${userType}${GenNo}",
                  "recovery_email": "Performance${GenNos}.${userType}${GenNo}@test.com"
                  }"""))
          .check(jsonPath("$.user_id").saveAs("UserRefIdx"))
          .check(jsonPath("$.username").saveAs("UsernameOnex"))
          .check(status.is(201)))
        .pause(Environment.minTime,Environment.maxTime)
      } {
        exec(http("Booking-Hearing-${userType}.User.Get")
          .get(userUrl + "/users/userName/Performance${GenNos}.${userType}${GenNo}@${appDomain}")
            .headers(auth.headers)
            .check(jsonPath("$.user_id").saveAs("UserRefIdx"))
            .check(jsonPath("$.user_name").saveAs("UsernameOnex"))
            .check(status.in(200)))
          .pause(Environment.minTime,Environment.maxTime)
          .exec(http("Booking-Hearing-${userType}.User.Password.Reset")
            .patch(userUrl + "/users")
            .headers(auth.headers)
            .body(StringBody(""""Performance${GenNo}.${userType}${GenNo}@${appDomain}""""))
            .check(status.in(204)))
          .pause(Environment.minTime,Environment.maxTime)
      }

  }

  def create_new_user()= {
    exec(http("Booking-Hearing-${ParticipantFirstName}${ParticipantLastName}.User.Exists")
      .get(userUrl + "/users/userName/${ParticipantFirstName}.${ParticipantLastName}@${appDomain}")
      .headers(auth.headers)
      .check(status.saveAs("userExistStatus"))
      .check(status.in(200,404)))
//      .pause(Environment.minTime,Environment.maxTime)
      .doIfOrElse(session => session("userExistStatus").as[String] == "404") {
        exec(http("Booking-Hearing-${ParticipantFirstName}${ParticipantLastName}.User.Registration").post(userUrl + "/users").headers(auth.headers)
          .body(StringBody(
            """{
                  "first_name": "${ParticipantFirstName}",
                  "last_name": "${ParticipantLastName}",
                  "recovery_email": "${ParticipantFirstName}${ParticipantLastName}@test.com"
                  }"""))
          .check(jsonPath("$.user_id").saveAs("UserRefIdx"))
          .check(jsonPath("$.username").saveAs("UsernameOnex"))
          .check(status.is(201)))
//          .pause(Environment.minTime,Environment.maxTime)
      } {
        exec(http("Booking-Hearing-${ParticipantFirstName}${ParticipantLastName}.User.Get")
          .get(userUrl + "/users/userName/${ParticipantFirstName}.${ParticipantLastName}@${appDomain}")
          .headers(auth.headers)
          .check(jsonPath("$.user_id").saveAs("UserRefIdx"))
          .check(jsonPath("$.user_name").saveAs("UsernameOnex"))
          .check(status.in(200)))
          .pause(Environment.minTime,Environment.maxTime)
          .exec(http("Booking-Hearing-${ParticipantFirstName}${ParticipantLastName}.User.Password.Reset")
            .patch(userUrl + "/users")
            .headers(auth.headers)
            .body(StringBody(""""${ParticipantFirstName}.${ParticipantLastName}@${appDomain}""""))
            .check(status.in(204)))
//          .pause(Environment.minTime,Environment.maxTime)
      }
  }

  def group_users()= {
    exec(http("Booking-Add.User.Group").patch(userUrl + "/accounts/user/group").headers(auth.headers)
      .body(StringBody(
        """{
                "user_id": "${UserRefIdx}",
                "group_name": "External"
					      }"""))
      .check(status.in(202,404)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def create_hearing()= {
       exec(http("Booking-Create.Hearing").post(bookingUrl+"/hearings").headers(auth.headersrv)
        .body(ElFileBody("data/booking/01.Create.Hearing.json")).asJson
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

  def multi_pariticpants_hearing()= {
    exec(http("Booking-Create.Hearing").post(bookingUrl+"/hearings").headers(auth.headersrv)
      .body(ElFileBody("data/booking/02.Multiple.Hearing.json")).asJson
      .check(jsonPath("$.id").saveAs("MultiParticipantsSrvHearingRefIdx"))
      .check(status.is(session => 201)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_hearing()= {
       exec(http("Booking-Delete.Hearing")
         .delete(bookingUrl+"/hearings/${SrvHearingRefIdx}")
         .headers(auth.headersrv)
      .check(status.is(session => 204)))
//      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_hearing_participant()= {
    exec(http("Booking-Delete.Participant")
      .delete(bookingUrl+"/hearings/${SrvHearingRefIdx}/participants/${ParticipantId}")
      .headers(auth.headersrv)
      .check(status.is(session => 204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_conference()= {
    exec(http("VideoApi-Delete.Conference")
      .delete(Environment.videoApiURL+"/conferences/${SrvHearingRefIdx}")
      .headers(auth.headersrvx)
      .check(status.is(session => 204)))
//      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_conference_participant()= {
    exec(http("VideoApi-Delete.Participant")
      .delete(Environment.videoApiURL+"/conferences/${SrvHearingRefIdx}/participants/${ParticipantId}")
      .headers(auth.headersrvx)
      .check(status.is(session => 204)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_user()= {
    exec(http("Booking-Hearing-Check.User.Exists")
      .get(userUrl + "/users/userName/${PerformanceUserName}")
      .headers(auth.headers)
      .check(status.saveAs("userExistStatus"))
      .check(status.in(200,404)))
//      .pause(Environment.minTime,Environment.maxTime)
      .doIf(session => session("userExistStatus").as[String] == "200") {
        exec(http("Booking-Hearing-Delete.User").delete(userUrl + "/users/userName/${PerformanceUserName}").headers(auth.headers)
          .check(status.is(204)))
//          .pause(Environment.minTime,Environment.maxTime)
      }

  }

}

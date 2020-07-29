package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Tasks  {

  def TaskScenarios() = {
    exec(http("Task-GetTask")
      .get("${videoApiUrl}/conferences/${conf_Idx}/tasks").headers(auth.headers)
      .check(status.is(200)))
        .pause(Environment.minTime,Environment.maxTime)

//    .exec(http("Task-Update")
//      .patch("${videoApiUrl}/conferences/${conf_Idx}/tasks/${taskIdx}").headers(auth.headers)
//      .body(StringBody(
//        s"""{
//              "updated_by": "Perf Test Task Update"
//					}"""))
//      .check(status.is(200)))
        // .pause(Environment.minTime,Environment.maxTime)
  }
}

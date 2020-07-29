package uk.gov.hmcts.reform.perftest.simulation
import io.gatling.core.Predef._
import uk.gov.hmcts.reform.perftest.scenarios.videoapi._

import scala.concurrent.duration._

class VideoApiScenarios extends Simulation {
  val videpApiCsv = csv("data/videoApiData/credentials.csv").circular
  val VideoApiPerfTest = scenario("VideoApi Perf Test")
      .feed(videpApiCsv)
//    .forever() {
         .exec(auth.auth, conference.BookConference())
        .doIf("${bearerx.exists()}") {
          exec(conference.ConferenceChecks())
         .exec(Participants.ParticipantsScenario())
         .exec(conference.ConferenceGetDetails())
         .exec(VideoEvents.VideoEventScenario())
         .exec(consultation.ConsultationScenario())
         .exec(InstantMessage.InstantMessageScenario())
         .exec(SelfTest.SelfTestScenario())
         .exec(Tasks.TaskScenarios())
         .exec(AudioRecording.AudioReordingScenario())

//          Conference Close and Delete
         .exec(conference.ConferenceClose())
         .exec(Participants.ParticipantsDelete())
         .exec(conference.ConferenceDelete())
        }
//    }

  setUp(VideoApiPerfTest.inject(atOnceUsers(1))).protocols(auth.httpProtocol).maxDuration(2 minutes)
//  setUp(VideoApiPerfTest.inject(rampUsers(60) during (720 seconds))).protocols(auth.httpProtocol).maxDuration(60 minutes)
}
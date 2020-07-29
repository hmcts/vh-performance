package uk.gov.hmcts.reform.perftest.scenarios.videoapi
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AudioRecording {
  def AudioReordingScenario() = {
    exec(http("AudioRecord-GetAudio")
      .get("${videoApiUrl}/conferences/audioapplications/${GenHearRefIdx}").headers(auth.headers)
      .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      /* .exec(http("AudioRecord-CreateAudioAppStr")
        .post("${videoApiUrl}/conferences/audioapplications/audiostream/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(200)))
        .pause(Environment.minTime,Environment.maxTime)*/

      .exec(http("AudioRecord-GetAudioStream")
        .get("${videoApiUrl}/conferences/audiostreams/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("AudioRecord-GetAudioStreamMonitoring")
        .get("${videoApiUrl}/conferences/audiostreams/${GenHearRefIdx}/monitoring").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("AudioRecord-DeleteAudioStream")
        .delete("${videoApiUrl}/conferences/audiostreams/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("AudioRecord-CreateAudioStream")
        .post("${videoApiUrl}/conferences/audiostreams/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("AudioRecord-DeleteAudioApplication")
        .delete("${videoApiUrl}/conferences/audioapplications/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(204)))
      .pause(Environment.minTime,Environment.maxTime)

      .exec(http("AudioReord-Create")
        .post("${videoApiUrl}/conferences/audioapplications/${GenHearRefIdx}").headers(auth.headers)
        .check(status.is(200)))
      .pause(Environment.minTime,Environment.maxTime)
  }
}

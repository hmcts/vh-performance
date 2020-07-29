package uk.gov.hmcts.reform.perftest.scenarios.azureblobapi

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import uk.gov.hmcts.reform.adminweb.scenarios.utils.Environment

object AzureBlobRequests {

  val credentialsFeed = csv("data/credentials.csv").circular

  val headers_0 = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "Content-Type" -> "text/plain",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36")

  val headerAuth = Map(
    "Connection" -> "keep-alive",
    "x-ms-date" -> "${xmsDateIdx}",
    "x-ms-version" -> "2017-04-17",
    "Authorization" -> "${authorizationIdx}",
    "Host" -> Environment.azureBlobBaseURL)

  val headerUpload = Map(
    "Content-Length" -> "1222931",
    "x-ms-date" -> "${xmsDateIdx}",
    "x-ms-version" -> "2017-04-17",
    "x-ms-blob-type" -> "BlockBlob",
    "Authorization" -> "${authorizationIdx}",
    "Host" -> Environment.azureBlobBaseURL)


  val setStorageAccountName = exec { session =>
    session.set("storageAccountName", session("StorageAccountNamePrefix").as[String] + Environment.appEnvironment)
  }

  def list_blob()= {
     feed(credentialsFeed)
    .exec(setStorageAccountName)
    .exec(http("AzureBlob-List-Auth").get(Environment.azureAuthUrl).headers(headers_0)
      .body(ElFileBody("data/blobList.json")).asJson
      .check(jsonPath("$.XmsDate").saveAs("xmsDateIdx"))
      .check(jsonPath("$.Authorization").saveAs("authorizationIdx"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
    .exec(http("AzureBlob-List").get(Environment.azureBlobUrl+"?comp=list").headers(headerAuth)
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def upload_blob()= {
     feed(credentialsFeed)
    .exec(setStorageAccountName)
    .exec(http("AzureBlob-Upload-Auth").get(Environment.azureAuthUrl).headers(headers_0)
      .body(ElFileBody("data/blobUpload.json"))
      .check(jsonPath("$.XmsDate").saveAs("xmsDateIdx"))
      .check(jsonPath("$.Authorization").saveAs("authorizationIdx"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
    .exec(http("AzureBlob-Upload").put(Environment.azureBlobUrl+"/recordings/${BookedHearingRefIdx}.mp4").headers(headerUpload)
      .body(RawFileBody("data/TestAudioFile.mp4"))
      .check(status.is(session => 201)))
      .pause(Environment.minTime,Environment.maxTime)

  }

  def get_blob()= {
    exec(http("AzureBlob-List-Auth").get(Environment.azureAuthUrl).headers(headers_0)
      .body(ElFileBody("data/blobGet.json")).asJson
      .check(jsonPath("$.XmsDate").saveAs("xmsDateIdx"))
      .check(jsonPath("$.Authorization").saveAs("authorizationIdx"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
    .exec(http("AzureBlob-Get").get(Environment.azureBlobUrl+"/recordings/${BookedHearingRefIdx}.mp4").headers(headerAuth)
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
  }

  def delete_blob()= {
     feed(credentialsFeed)
    .exec(setStorageAccountName)
    .exec(http("AzureBlob-Delete-Auth").get(Environment.azureAuthUrl).headers(headers_0)
      .body(ElFileBody("data/blobDelete.json")).asJson
      .check(jsonPath("$.XmsDate").saveAs("xmsDateIdx"))
      .check(jsonPath("$.Authorization").saveAs("authorizationIdx"))
      .check(status.is(session => 200)))
      .pause(Environment.minTime,Environment.maxTime)
      .exec(http("AzureBlob-Delete").delete(Environment.azureBlobUrl+"/recordings/${BookedHearingRefIdx}.mp4").headers(headerAuth)
        .check(status.is(session => 202)))
      .pause(Environment.minTime,Environment.maxTime)
  }

}

package uk.gov.hmcts.reform.perftest.scenarios.adminweb
import uk.gov.hmcts.reform.perftest.utils.Environment

object AdminHeaders {
  val adminURL      = Environment.adminUrl

  val headers_0 = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Connection" -> "keep-alive",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36")

  val headers_1 = Map(
    "response_type"->"id_token",
    "user-agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36",
    "Sec-Fetch-Dest" -> "document",
    "Connection" -> "keep-alive",
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9" )

  val headers_2 = Map(
    "Accept" -> "application/json",
    "Sec-Fetch-Dest" -> "",
    "Sec-Fetch-Mode" -> "cors",
    "Connection" -> "keep-alive",
    "Origin" -> "https://login.microsoftonline.com",
    "canary" -> "${apiCanaryIdx}",
    "client-request-id" -> "",
    "hpgact" -> "1800",
    "hpgid" -> "1104",
    "hpgrequestid" -> "")

  val headers_3 = Map("Accept" -> "application/json", "Sec-Fetch-Dest" -> "document",  "Sec-Fetch-Mode" -> "navigate",  "Sec-Fetch-User" -> "?1", "Connection" -> "keep-alive", "Cache-Control" -> "max-age=0", "Upgrade-Insecure-Requests" -> "1")


  val headers_4 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.hcb/a",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_5 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.nQeh",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_6 = Map(
    "Content-Type" -> "application/json",
    "Content-type" -> "application/json",
    "Origin" -> s"${adminURL}",
    "Sdk-Context" -> "appId",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "cross-site")

  val headers_7 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.stKCs",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_8 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.jK5P9",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_9 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.osEdF",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_10 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.Lbfkd",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_12 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.7P/Ql",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_13 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.PI2N/",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_15 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.zne5j",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_16 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|UZM32.aGMmR",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_18 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|zGIfb.Gm818",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_19 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|zGIfb.HeBqp",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_21 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.Kut7",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_22 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.2KCJ+",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_23 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.VcYaF",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_24 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.M7v/S",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_25 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.C6xFd",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")
  val headers_26 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.+BxfF",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_27 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Content-Type" -> "application/json-patch+json",
    "Origin" -> s"${adminURL}",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.yfjjR",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_28 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.XzwN/",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_29 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.SQyoR",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_30 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Content-Type" -> "application/json-patch+json",
    "Origin" -> s"${adminURL}",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.ua4eN",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_31 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.g9ZiP",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_32 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|lWtAg.PtSEl",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_logout = Map(
    "Sec-Fetch-Dest" -> "document",
    "Sec-Fetch-Mode" -> "navigate",
    "Sec-Fetch-Site" -> "cross-site",
    "Sec-Fetch-User" -> "?1",
    "Upgrade-Insecure-Requests" -> "1")
  //============================================================
  val headers_33 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|Kcj5y.wHMTr",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_34 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|Kcj5y.FH0iM",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_35 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|c+aAT.IeGRS",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_36 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|c+aAT.qaLId",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_37 = Map(
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|c+aAT.ct7Bh",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_38 = Map(
    "Accept" -> "application/json, text/plain, */*",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Content-Type" -> "application/json-patch+json",
    "Origin" -> s"${adminURL}",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|c+aAT.0bYJG",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")

  val headers_39 = Map(
    "Accept" -> "application/json",
    "Authorization" -> "Bearer ${IdTokenx}",
    "Cache-Control" -> "no-cache",
    "Pragma" -> "no-cache",
    "Request-Context" -> "appId=cid-v1:60c0927b-3326-45d2-b0a6-13a3a5b8cb8e",
    "Request-Id" -> "|c+aAT.jgFk2",
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin")



}

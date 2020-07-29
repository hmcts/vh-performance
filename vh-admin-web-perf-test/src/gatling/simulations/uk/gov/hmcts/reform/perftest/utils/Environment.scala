package uk.gov.hmcts.reform.adminweb.scenarios.utils

import io.gatling.core.Predef._
import io.gatling.core.session
import io.gatling.http.Predef._

object Environment {

    //  ADMIN WEB VARIABLES
    var appEnvironment = "demo";
    val httpPrefix = "https://";
    val adminUrl        = httpPrefix+"${AdminWebUrlPrefix}-"+appEnvironment+".${appDomain}"
    val bookingBaseURL  = "${BookingApiUrlPrefix}-"+appEnvironment+".${apiDomain}";
    val bookingURL      = httpPrefix+bookingBaseURL;
    val storageAccountName = "${StorageAccountNamePrefix}"+appEnvironment;
    val azureBlobBaseURL = storageAccountName+".blob.core.windows.net"
    var azureBlobUrl    = httpPrefix+azureBlobBaseURL;
    var azureAuthUrl    = httpPrefix+"${AzureAuthUrl}"+".${apiDomain}/api/GenerateHashKey"
    val authUrl         = httpPrefix+"login.microsoftonline.com"
    val httpProtocol    =  http.disableFollowRedirect

    //client Secrets
    val tenantId = "${TenantId}";
    val bookingsClientId = "${BookingsClientId}";
    val bookingsClientSecret = "${BookingsClientSecret}";

    val minTime     = 5
    val maxTime     = 15
    val consTime    = 30
}

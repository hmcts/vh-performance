package uk.gov.hmcts.reform.perftest.utils
import io.gatling.http.Predef._
import io.gatling.core.Predef._

object Environment {

    //  ADMIN WEB VARIABLES
    val appEnvironment  = "demo";
    val httpPrefix      = "https://";
    val adminUrl        = httpPrefix+"${AdminWebUrlPrefix}-"+appEnvironment+".${appDomain}"
    val vsServiceUrl    = httpPrefix+"dc.services.visualstudio.com"
    val authUrl         = httpPrefix+"login.microsoftonline.com"
    val httpProtocol    = http.disableFollowRedirect

    //BOOKING API
    val bookingBaseURL  = "${BookingsApiUrlPrefix}-"+appEnvironment+".${apiDomain}";
    val videoApiBaseURL = "${VideoApiUrlPrefix}-"+appEnvironment+".${apiDomain}";
    val userBaseURL     = "${UserApiUrlPrefix}-"+appEnvironment+".${apiDomain}";
    val userURL         = httpPrefix+userBaseURL;
    val bookingURL      = httpPrefix+bookingBaseURL;
    val videoApiURL     = httpPrefix+videoApiBaseURL;

    //  SERVICE WEB
    val serviceUrl      = httpPrefix+"${ServiceWebUrlPrefix}-"+appEnvironment+".${appDomain}"
    val SrvVsUrl        = "https://dc.services.visualstudio.com"
    val mslogin         = "https://login.live.com"

    //client Secrets
    val tenantId = "${TenantId}";
    val bookingsClientId = "${BookingsClientId}";
    val bookingsClientSecret = "${BookingsClientSecret}";
    val videoClientId = "${VideoClientId}";
    val videoClientSecret = "${VideoClientSecret}";
    val userClientId = "${UserClientId}";
    val userClientSecret = "${UserClientSecret}";
    val oldPassword = "${OldPassword}";
    val newPassword = "${NewPassword}";

    val minTime     = 5
    val maxTime     = 15
    val constTime   = 30

}

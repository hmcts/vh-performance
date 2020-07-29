package uk.gov.hmcts.reform.perftest.scenarios.videoapi

import java.sql.Timestamp
import java.util.Date

import scala.util.Random

object Feeders {
  val random = new scala.util.Random
  def randomString(alphabet: String)(n: Int): String =
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString
  def RandAlphaNumStr(n: Int) = randomString("abcdef0123456789")(n)

  def GenHearing_ref_idx() = (math.ceil(Random.nextFloat() * 1E11).toLong).toString
  def GenParty_ref_idx() = (math.ceil(Random.nextFloat() * 1E12).toLong).toString
  def GenTimex() = ((((new Timestamp(new Date().getTime())).toString) + "Z").replace(" ", "T")).toString
//  def GenNox() = (math.ceil(Random.nextFloat() * 1E4).toLong).toString
def GenNox(n: Int) = randomString("0123456789")(n)

  var Gen_Timex = ""
  var GenHearRef_Idx =""
  var GenPartOneRef_Idx =""
  var GenPartTwoRef_Idx =""
  var GenPartThreeRef_Idx=""

  var participant_disp_namex = ""
  var participant_usernamex = ""
  var case_numberx = ""
  var case_namex = ""
  var representeex = ""
  var hearing_venue_namex = ""
  var Second_Party_Id = ""

  def GenCaseNox() :String = {  case_numberx = GenNox(4)
    case_numberx  }
  def GenTimeStampx() :String=   { Gen_Timex = GenTimex()
    Gen_Timex  }

  def GenJudgeDispName() :String = {  participant_disp_namex = ("Performance01 Judge" + GenNox(4))
    participant_disp_namex  }
  def GenJudgeUsernamex() :String = {  participant_usernamex = ("Performance01Judge" + GenNox(4))
    participant_usernamex  }
  def GenPartyDispName() :String = {  participant_disp_namex = ("Performance01 Representative" + GenNox(4))
    participant_disp_namex  }
  def GenPartyUsernamex() :String = {  participant_usernamex = ("Performance01Representative" + GenNox(4))
    participant_usernamex  }


  def GenCaseNamex() :String = {  case_namex = ("Case Name VideoAPi PerfTest - " + GenNox(4))
    case_namex  }
  def GenRepresenteex() :String = {  representeex = ("Representee" + GenNox(4))
    representeex  }
  def GenVenueNamex() :String = {  hearing_venue_namex = ("HearingVenueName -" + GenNox(4))
    hearing_venue_namex  }

  def GenHearRefIdx() :String = {  GenHearRef_Idx = (RandAlphaNumStr(8) +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ RandAlphaNumStr(12))
    GenHearRef_Idx  }
  def GenPartOneRefIdx() :String = {  GenPartOneRef_Idx = (RandAlphaNumStr(8) +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ RandAlphaNumStr(12))
    GenPartOneRef_Idx  }
  def GenPartTwoRefIdx() :String = {  GenPartTwoRef_Idx = (RandAlphaNumStr(8) +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ RandAlphaNumStr(12))
    GenPartTwoRef_Idx  }
  def GenPartThreeRefIdx() :String = {  GenPartThreeRef_Idx = (RandAlphaNumStr(8) +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ GenCaseNox() +"-"+ RandAlphaNumStr(12))
    GenPartThreeRef_Idx  }

  val DynamicVideoApiFeeder = Iterator.continually(Map(
//    "GenPartyRefIdx" -> ({GenPartyRefIdx()}),
    "GenPartyDispName" -> ({GenPartyDispName()}),
    "GenPartyUsernamex" -> ({GenPartyUsernamex()}),
    "GenCaseNox" -> ({GenCaseNox()}),
    "GenCaseNamex" -> ({GenCaseNamex()}),
    "GenRepresenteex" -> ({GenRepresenteex()}),
    "GenVenueNamex" -> ({GenVenueNamex()}),
    "GenTimeStampx" -> ({GenTimeStampx()}),
    "GenJudgeDispName"-> ({GenJudgeDispName()}),
    "GenJudgeUsernamex"-> ({GenJudgeUsernamex()}),
    "GenHearRefIdx"-> ({GenHearRefIdx}),
    "GenPartOneRefIdx"-> ({GenPartOneRefIdx}),
    "GenPartTwoRefIdx"-> ({GenPartTwoRefIdx}),
    "GenPartThreeRefIdx"-> ({GenPartThreeRefIdx})
  ));

//println("GenHearRefIdx-------"+GenHearRefIdx)
//  println("GenPartOneRefIdx-------"+GenPartOneRefIdx)
//  println("GenPartTwoRefIdx-------"+GenPartTwoRefIdx)

}
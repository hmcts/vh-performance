package uk.gov.hmcts.reform.perftest.scenarios

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.{Calendar, Date, UUID}

import scala.util.Random

object Feeders {
  var Gen_Timex = ""
  var Gen_Timexx=""
  var Gen_nox=""
  var Gen_noxx=""
  var Gen_noxxx=""
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


  //========================================================
  val random = new scala.util.Random
  def randomString(alphabet: String)(n: Int): String = Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString
  def RandAlphaNumStr(n: Int) = randomString("abcdef0123456789")(n)

  def GenHearing_ref_idx()  = (math.ceil(Random.nextFloat() * 1E11).toLong).toString
  def GenParty_ref_idx()    = (math.ceil(Random.nextFloat() * 1E12).toLong).toString
  def GenNox(n: Int)        = randomString("0123456789")(n)
  val calender              = Calendar.getInstance()
                            calender.roll(Calendar.DAY_OF_YEAR, +5)
  val sdf                   = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
  def HearingDatexx()       = (((((sdf.format(calender.getTime())).toString).toString) + "Z").replace(" ", "T")).toString
//  def GenTimex()          = (((((sdf.format(calender.getTime())).toString).toString) + "Z").replace(" ", "T")).toString
  def GenTimex()            = ((((new Timestamp(new Date().getTime())).toString) + "Z").replace(" ", "T")).toString
//  println("========================================"+HearingDatexx)


  //========================================================

  def GenTimeStampx() :String=   { Gen_Timex = GenTimex()
    Gen_Timex  }
  def GenCaseNox() :String = {  case_numberx = GenNox(4)
    case_numberx  }
  def HearingDatex() :String=   { Gen_Timexx = HearingDatexx
    Gen_Timexx  }
  def GenNox() :String=   { Gen_nox =  GenNox(2) + GenNox(2)
    Gen_nox  }
  def GenNoxx() :String=   { Gen_noxx = GenNox(2) + GenNox(2)
    Gen_noxx  }
  def GenNoxxx() :String=   { Gen_noxx =  GenNox(2)
    Gen_noxx  }

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

  val Feederx = Iterator.continually(Map(
//    "GenPartyRefIdx" -> ({GenPartyRefIdx()}),
    "GenNox"            -> ({GenNox()}),
    "GenNoxx"           -> ({GenNoxx()}),
    "GenNoxxx"          -> ({GenNoxxx()}),
    "HearingDatex"      -> ({HearingDatex()}),
    "GenTimeStampx"     -> ({GenTimeStampx}),

    "GenPartyDispName"  -> ({GenPartyDispName()}),
    "GenPartyUsernamex" -> ({GenPartyUsernamex()}),
    "GenCaseNox"        -> ({GenCaseNox()}),
    "GenCaseNamex"      -> ({GenCaseNamex()}),
    "GenRepresenteex"   -> ({GenRepresenteex()}),
    "GenVenueNamex"     -> ({GenVenueNamex()}),

    "GenJudgeDispName"  -> ({GenJudgeDispName()}),
    "GenJudgeUsernamex" -> ({GenJudgeUsernamex()}),
    "GenHearRefIdx"     -> ({GenHearRefIdx}),
    "GenPartOneRefIdx"  -> ({GenPartOneRefIdx}),
    "GenPartTwoRefIdx"  -> ({GenPartTwoRefIdx}),
    "GenPartThreeRefIdx"-> ({GenPartThreeRefIdx}),
    "NewGuid" -> UUID.randomUUID.toString()
  ));

//println("GenHearRefIdx-------"+HearingDatex)
//  println("GenPartOneRefIdx-------"+GenPartOneRefIdx)
//  println("GenPartTwoRefIdx-------"+GenPartTwoRefIdx)

}

package uk.gov.hmcts.reform.adminweb.scenarios

import java.sql.Timestamp
import java.util.Date

object adminWebFeeder {
  val random = new scala.util.Random
  def randomString(alphabet: String)(n: Int): String =
    Stream.continually(random.nextInt(alphabet.size)).map(alphabet).take(n).mkString
  def GenNox(n: Int) = randomString("0123456789")(n)
  def GenTimex() = ((((new Timestamp(new Date().getTime())).toString) + "Z").replace(" ", "T")).toString

  var Gen_Timex = ""
  var Gen_Nox = ""

  def GenTimeStampx() :String=   { Gen_Timex = GenTimex()
    Gen_Timex  }
  def GenNox() :String=   { Gen_Nox =  GenNox(2)
    Gen_Nox  }
  def GenNoxx() :String=   { Gen_Nox =  GenNox(2)
    Gen_Nox  }
  def GenNoxxx() :String=   { Gen_Nox =  GenNox(2)
    Gen_Nox  }

  val DynamicAdminFeeder = Iterator.continually(Map(
    "GenTimeStampx" -> ({GenTimeStampx}),
    "GenNox"-> ({GenNox}),
    "GenNoxx"-> ({GenNoxx}),
    "GenNoxxx"-> ({GenNoxxx})
  ));
}
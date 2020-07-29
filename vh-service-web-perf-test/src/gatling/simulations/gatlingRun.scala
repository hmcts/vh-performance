import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import uk.gov.hmcts.reform.perftest.simulation.{AdminWebScenarios, CleanUpScenarios, ServiceScenarios}


object gatlingRun {
  def main(args: Array[String]): Unit = {
    val simClass = classOf[ServiceScenarios].getName
//    val simClass = classOf[CleanUpScenarios].getName
    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)
    Gatling.fromMap(props.build)
  }
}
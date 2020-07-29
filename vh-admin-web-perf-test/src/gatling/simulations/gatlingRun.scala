import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import uk.gov.hmcts.reform.simulation.adminWebCalls


object gatlingRun {
  def main(args: Array[String]): Unit = {
    val simClass = classOf[adminWebCalls].getName
    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)
    Gatling.fromMap(props.build)
  }
}
package it.pps.course.testing

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(features = Array("classpath:features/MyFeature.feature"),
  tags = "not @Wip",
  glue = Array("classpath:it.pps.course.testing.steps"),
  plugin = Array("pretty", "html:target/cucumber/html")
)
class RunCucumberTests

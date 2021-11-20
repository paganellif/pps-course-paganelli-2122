package it.pps.course.testing

import org.junit.runner.RunWith
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatestplus.junit.JUnitRunner

class TVSet {
  private var on: Boolean = false
  def isOn: Boolean = on
  def pressPowerButton(): Unit = { on = !on }
}

@RunWith(classOf[JUnitRunner])
class TVSetSpec extends AnyFeatureSpec with GivenWhenThen {
  info("As a TV set owner")
  info("I want to be able to turn the TV on and off")
  info("So I can watch TV when I want")
  info("And save energy when I'm not watching TV")

  feature("TV power button") {
    scenario("User presses power button when TV is off") {
      Given("a TV set that is switched off")
      val tv = new TVSet
      assert(!tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch on")
      assert(tv.isOn)
    }
  }

  scenario("User presses power button when TV is on") { pending }
}
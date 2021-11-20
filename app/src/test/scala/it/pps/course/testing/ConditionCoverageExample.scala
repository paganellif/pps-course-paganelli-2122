package it.pps.course.testing

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import org.scalatest.funsuite.AnyFunSuite

@RunWith(classOf[JUnitRunner])
class ConditionCoverageExample extends AnyFunSuite {
  test("Cover all the conditions") {
    ProgramToCover.methodToCover(false, true, false, true, true)
    ProgramToCover.methodToCover(true, false, true, false, false)
  }

  test("Cover all the decision branches") {
    ProgramToCover.methodToCover(true, true, false, false, true)
    ProgramToCover.methodToCover(false, false, true, false, false)
  }
}
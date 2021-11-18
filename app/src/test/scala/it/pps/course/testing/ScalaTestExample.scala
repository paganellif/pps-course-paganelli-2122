package it.pps.course.testing

import it.pps.course.u06lab.{BasicParser, NonEmptyParser, NotTwoConsecutiveParser}
import org.junit.runner.RunWith
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaTestExample extends AnyFlatSpec with should.Matchers {

  "A BasicParser [a,b,c]" should "parse aabc" in {
    def parser = new BasicParser(Set('a', 'b', 'c'))
    parser.parseAll("aabc".toList) should be (true)
  }

  it should "parse an empty string" in {
    def parser = new BasicParser(Set('a', 'b', 'c'))
    parser.parseAll("".toList) should be (true)
  }

  it should "not parse aabcdc" in {
    def parser = new BasicParser(Set('a', 'b', 'c'))
    parser.parseAll("aabcdc".toList) should be (false)
  }

  "A NotEmptyParser [0,1]" should "parse 0101" in {
    def parserNE = new NonEmptyParser(Set('0', '1'))
    parserNE.parseAll("0101".toList) should be (true)
  }

  it should "not parse 0123" in {
    def parserNE = new NonEmptyParser(Set('0', '1'))
    parserNE.parseAll("0123".toList) should be (false)
  }

  it should "not parse an empty string" in {
    def parserNE = new NonEmptyParser(Set('0', '1'))
    parserNE.parseAll("".toList) should be (false)
  }

  "A NotTwoConsecutiveParser [X,Y,Z]" should "parse XYZ" in {
    def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
    parserNTC.parseAll("XYZ".toList) should be (true)
  }

  it should "not parse XYYZ" in {
    def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
    parserNTC.parseAll("XYYZ".toList) should be (false)
  }

  it should " parse an empty string" in {
    def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
    parserNTC.parseAll("".toList) should be (true)
  }

  /*

  // note we do not need a class name here, we use the structural type
  def parserNTCNE = new BasicParser(Set('X', 'Y', 'Z')) with NotTwoConsecutive[Char] with NonEmpty[Char]
  def sparser: Parser[Char] = "abc".charParser()

  @Test
  def testNotEmptyAndNotTwoConsecutiveParser(): Unit = {
    assertTrue(parserNTCNE.parseAll("XYZ".toList))
    assertFalse(parserNTCNE.parseAll("XYYZ".toList))
    assertFalse(parserNTCNE.parseAll("".toList))
  }

  @Test
  def testStringParser(): Unit = {
    assertTrue(sparser.parseAll("aabc".toList))
    assertFalse(sparser.parseAll("aabcdc".toList))
    assertTrue(sparser.parseAll("".toList))
  }
   */

}

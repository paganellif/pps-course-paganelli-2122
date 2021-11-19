package it.pps.course.testing

import it.pps.course.u06lab.TryParsers.CharParser
import it.pps.course.u06lab.{BasicParser, NonEmpty, NonEmptyParser, NotTwoConsecutive, NotTwoConsecutiveParser, Parser}
import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ScalaTestExample extends AnyFunSpec with should.Matchers {

  describe("A BasicParser on [a,b,c]") {
    def parser = new BasicParser(Set('a', 'b', 'c'))
    it("should parse aabc") {
      assert(parser.parseAll("aabc".toList))
    }

    it ("should parse an empty string") {
      assert(parser.parseAll("".toList))
    }

    it("should not parse aabcdc") {
      assert(!parser.parseAll("aabcdc".toList))
    }
  }

  describe("A NotEmptyParser on [0,1]") {
    def parserNE = new NonEmptyParser(Set('0', '1'))
    it("should parse 0101"){
      assert(parserNE.parseAll("0101".toList))
    }

    it("should not parse 0123") {
      assert(!parserNE.parseAll("0123".toList))
    }

    it("should not parse an empty string") {
      assert(!parserNE.parseAll("".toList))
    }
  }

  describe( "A NotTwoConsecutiveParser on [X,Y,Z]"){
    def parserNTC = new NotTwoConsecutiveParser(Set('X', 'Y', 'Z'))
    it("should parse XYZ") {
      assert(parserNTC.parseAll("XYZ".toList))
    }

    it("should not parse XYYZ") {
      assert(!parserNTC.parseAll("XYYZ".toList))
    }

    it("should parse an empty string") {
      assert(parserNTC.parseAll("".toList))
    }
  }

  describe("A NotTwoConsecutiveNonEmptyParser on [X,Y,Z]"){
    def parserNTCNE = new BasicParser(Set('X', 'Y', 'Z')) with NotTwoConsecutive[Char] with NonEmpty[Char]
    it("should parse XYZ") {
      assert(parserNTCNE.parseAll("XYZ".toList))
    }

    it("should not parse XYYZ") {
      assert(!parserNTCNE.parseAll("XYYZ".toList))
    }

    it("should not parse an empty string") {
      assert(!parserNTCNE.parseAll("".toList))
    }
  }

  describe("An implicit StringParser on [a,b,c]"){
    def sparser: Parser[Char] = "abc".charParser()
    it("should parse aabc") {
      assert(sparser.parseAll("aabc".toList))
    }

    it ("should parse an empty string") {
      assert(sparser.parseAll("".toList))
    }

    it("should not parse aabcdc") {
      assert(!sparser.parseAll("aabcdc".toList))
    }
  }

}

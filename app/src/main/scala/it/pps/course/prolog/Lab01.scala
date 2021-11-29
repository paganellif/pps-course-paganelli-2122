package it.pps.course.prolog

import alice.tuprolog.{Prolog, Theory}

import java.nio.file.{Files, Paths}

object Lab01 {
  def apply(): Prolog = {
    val engine: Prolog = new Prolog

    implicit class TheoryFromFile(resourceFile: String){
      def toTheory: Theory = {
        new Theory(Files.readString(Paths.get(getClass.getClassLoader.getResource(resourceFile).toURI)))
      }
    }

    engine.setTheory("prolog/lab10.pl".toTheory)

    engine
  }
}

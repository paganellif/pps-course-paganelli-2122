package it.pps.course.prolog

import alice.tuprolog.{Prolog, Theory}

import java.nio.file.{Files, Paths}

object PrologLab {
  def apply(filePath: String): Prolog = {
    val engine: Prolog = new Prolog

    implicit class TheoryFromFile(resourceFile: String){
      def toTheory: Theory = {
        new Theory(Files.readString(Paths.get(getClass.getClassLoader.getResource(resourceFile).toURI)))
      }
    }

    engine.setTheory(filePath.toTheory)

    engine
  }
}

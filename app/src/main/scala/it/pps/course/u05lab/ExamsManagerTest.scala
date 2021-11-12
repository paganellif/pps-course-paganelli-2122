package it.pps.course.u05lab

import scala.collection._

object ExamsManagerTest extends App {

  /* See: https://bitbucket.org/mviroli/oop2018-esami/src/master/a01b/e1/Test.java */

  trait Kind

  object Kind {
    case class Retired() extends Kind
    case class Failed() extends Kind
    case class Succeeded() extends Kind
  }

  trait ExamResult {
    def getKind: Kind
    def getEvaluation: Option[Int]
    def cumLaude(): Boolean
  }

  object ExamResult {
    def failed(): ExamResult = new ExamResult {
      override def getKind: Kind = Kind.Failed()
      override def getEvaluation: Option[Int] = Option.empty
      override def cumLaude(): Boolean = false
      override def toString: String = "FAILED"
    }

    def retired(): ExamResult = new ExamResult {
      override def getKind: Kind = Kind.Retired()
      override def getEvaluation: Option[Int] = Option.empty
      override def cumLaude(): Boolean = false
      override def toString: String = "RETIRED"
    }

    def succeededCumLaude(): ExamResult = new ExamResult {
      override def getKind: Kind = Kind.Succeeded()
      override def getEvaluation: Option[Int] = Option(30)
      override def cumLaude(): Boolean = true
      override def toString: String = "SUCCEEDED(30L)"
    }

    def succeeded(evaluation: Int): ExamResult = {
      if(evaluation > 17 && evaluation < 31) {
        new ExamResult {
          override def getKind: Kind = Kind.Succeeded()
          override def getEvaluation: Option[Int] = Option(evaluation)
          override def cumLaude(): Boolean = false
          override def toString: String = "SUCCEEDED("+getEvaluation.get+")"
        }
      } else throw new IllegalArgumentException("Evaluation must be > 17 and < 31")
    }
  }

  trait ExamsManager {
    def createNewCall(call: String): Unit
    def addStudentResult(call: String, student: String, result: ExamResult): Unit
    def getAllStudentsFromCall(call: String): collection.Set[String]
    def getEvaluationsMapFromCall(call: String): immutable.Map[String, Int]
    def getResultsMapFromStudent(student: String): immutable.Map[String, String]
    def getBestResultFromStudent(student: String): Option[Int]
  }

  object ExamsManager{
    def apply(): ExamsManager = ExamsManagerImpl()

    private case class ExamsManagerImpl() extends ExamsManager {
      // [ call -> [ student -> exam-result ]
      private val callHistory = mutable.Map[String, mutable.Map[String, ExamResult]]()

      override def createNewCall(call: String): Unit = {
        if(callHistory.contains(call))
          throw new IllegalArgumentException("Cannot create a call twice")
        else
          callHistory.put(call, mutable.Map[String, ExamResult]())
      }

      override def addStudentResult(call: String, student: String, result: ExamResult): Unit = {
        if(callHistory(call).contains(student))
          throw new IllegalArgumentException("Cannot insert a result twice")
        else
          callHistory(call).put(student, result)
      }

      override def getAllStudentsFromCall(call: String): Set[String] = callHistory(call).keySet

      override def getEvaluationsMapFromCall(call: String): immutable.Map[String, Int] = immutable.Map
        .from(callHistory(call)
          .filter(entry => entry._2.getEvaluation.nonEmpty)
          .map(entry => (entry._1, entry._2.getEvaluation.get))
        )

      override def getResultsMapFromStudent(student: String): immutable.Map[String, String] = immutable.Map.from(callHistory
        .filter(entry => entry._2.contains(student))
        .map(entry => (entry._1, entry._2(student).toString)))

      override def getBestResultFromStudent(student: String): Option[Int] = {
        println(callHistory)
        val evaluations = callHistory
          .map(entry => entry._2(student).getEvaluation)
          .filter(evaluation => evaluation.nonEmpty)
        println(evaluations)
        if(evaluations.isEmpty) Option.empty else evaluations.max
      }
    }
  }
}
package it.pps.course.u05lab

import it.pps.course.u05lab.ExamsManagerTest.{ExamResult, ExamsManager, Kind}
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class ExamManagerTest {

  private val em: ExamsManager = ExamsManager()

  // verifica base di ExamResultFactory
  @Test def testExamResultsBasicBehaviour(): Unit = {
    // esame fallito, non c'è voto
    assertEquals(ExamResult.failed().getKind, Kind.Failed())
    assertFalse(ExamResult.failed().getEvaluation.nonEmpty)
    assertFalse(ExamResult.failed().cumLaude())
    assertEquals(ExamResult.failed().toString, "FAILED")

    // lo studente si è ritirato, non c'è voto
    assertEquals(ExamResult.retired().getKind, Kind.Retired())
    assertFalse(ExamResult.retired().getEvaluation.nonEmpty)
    assertFalse(ExamResult.retired().cumLaude())
    assertEquals(ExamResult.retired().toString, "RETIRED")

    // 30L
    assertEquals(ExamResult.succeededCumLaude().getKind, Kind.Succeeded())
    assertEquals(ExamResult.succeededCumLaude().getEvaluation, Option(30))
    assertTrue(ExamResult.succeededCumLaude().cumLaude())
    assertEquals(ExamResult.succeededCumLaude().toString, "SUCCEEDED(30L)")

    // esame superato, ma non con lode
    assertEquals(ExamResult.succeeded(28).getKind, Kind.Succeeded())
    assertEquals(ExamResult.succeeded(28).getEvaluation, Option(28))
    assertFalse(ExamResult.succeeded(28).cumLaude())
    assertEquals(ExamResult.succeeded(28).toString, "SUCCEEDED(28)")
    }

  // verifica eccezione in ExamResultFactory
  @Test def optionalTestEvaluationCantBeGreaterThan30(): Unit = {
    assertThrows(classOf[IllegalArgumentException], () => ExamResult.succeeded(32))
  }

  // verifica eccezione in ExamResultFactory
  @Test def optionalTestEvaluationCantBeSmallerThan18(): Unit = {
    assertThrows(classOf[IllegalArgumentException], () => ExamResult.succeeded(17))
  }

  // metodo di creazione di una situazione di risultati in 3 appelli
  private def prepareExams(): Unit = {
    em.createNewCall("gennaio")
    em.createNewCall("febbraio")
    em.createNewCall("marzo")

    em.addStudentResult("gennaio", "rossi", ExamResult.failed()) // rossi -> fallito
    em.addStudentResult("gennaio", "bianchi", ExamResult.retired()) // bianchi -> ritirato
    em.addStudentResult("gennaio", "verdi", ExamResult.succeeded(28)) // verdi -> 28
    em.addStudentResult("gennaio", "neri", ExamResult.succeededCumLaude()) // neri -> 30L

    em.addStudentResult("febbraio", "rossi", ExamResult.failed()) // etc..
    em.addStudentResult("febbraio", "bianchi", ExamResult.succeeded(20))
    em.addStudentResult("febbraio", "verdi", ExamResult.succeeded(30))

    em.addStudentResult("marzo", "rossi", ExamResult.succeeded(25))
    em.addStudentResult("marzo", "bianchi", ExamResult.succeeded(25))
    em.addStudentResult("marzo", "viola", ExamResult.failed())
  }

  // verifica base della parte obbligatoria di ExamManager
  @Test def testExamsManagement(): Unit = {
    this.prepareExams()
    // partecipanti agli appelli di gennaio e marzo
    assertEquals(em.getAllStudentsFromCall("gennaio"), Set.from(Array("rossi","bianchi","verdi","neri")))
    assertEquals(em.getAllStudentsFromCall("marzo"), Set.from(Array("rossi","bianchi","viola")))

    // promossi di gennaio con voto
    assertEquals(em.getEvaluationsMapFromCall("gennaio").size, 2)
    assertEquals(em.getEvaluationsMapFromCall("gennaio")("verdi").intValue(), 28)
    assertEquals(em.getEvaluationsMapFromCall("gennaio")("neri").intValue(),30)
    // promossi di febbraio con voto
    assertEquals(em.getEvaluationsMapFromCall("febbraio").size,2)
    assertEquals(em.getEvaluationsMapFromCall("febbraio")("bianchi").intValue(),20)
    assertEquals(em.getEvaluationsMapFromCall("febbraio")("verdi").intValue(),30)

    // tutti i risultati di rossi (attenzione ai toString!!)
    assertEquals(em.getResultsMapFromStudent("rossi").size,3)
    assertEquals(em.getResultsMapFromStudent("rossi")("gennaio"),"FAILED")
    assertEquals(em.getResultsMapFromStudent("rossi")("febbraio"),"FAILED")
    assertEquals(em.getResultsMapFromStudent("rossi")("marzo"),"SUCCEEDED(25)")
    // tutti i risultati di bianchi
    assertEquals(em.getResultsMapFromStudent("bianchi").size,3)
    assertEquals(em.getResultsMapFromStudent("bianchi")("gennaio"),"RETIRED")
    assertEquals(em.getResultsMapFromStudent("bianchi")("febbraio"),"SUCCEEDED(20)")
    assertEquals(em.getResultsMapFromStudent("bianchi")("marzo"),"SUCCEEDED(25)")
    // tutti i risultati di neri
    assertEquals(em.getResultsMapFromStudent("neri").size,1)
    assertEquals(em.getResultsMapFromStudent("neri")("gennaio"),"SUCCEEDED(30L)")
  }

  // verifica del metodo ExamManager.getBestResultFromStudent
  @Test def optionalTestExamsManagement(): Unit = {
    this.prepareExams()
    // miglior voto acquisito da ogni studente, o vuoto..
    assertEquals(em.getBestResultFromStudent("rossi"),Option(25))
    assertEquals(em.getBestResultFromStudent("bianchi"),Option(25))
    //assertEquals(em.getBestResultFromStudent("neri"),Option(30))
    //assertEquals(em.getBestResultFromStudent("viola"),Option.empty)
  }


  @Test def optionalTestCantCreateACallTwice(): Unit = {
    this.prepareExams()
    assertThrows(classOf[IllegalArgumentException], () => em.createNewCall("marzo"))
  }

  @Test def optionalTestCantRegisterAnEvaluationTwice(): Unit = {
    this.prepareExams()
    assertThrows(classOf[IllegalArgumentException],
      () => em.addStudentResult("gennaio", "verdi", ExamResult.failed())
    )
  }
}
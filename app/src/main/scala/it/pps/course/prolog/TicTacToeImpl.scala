package it.pps.course.prolog

import java.io.FileInputStream
import alice.tuprolog.Theory
import Scala2P._

import java.nio.file.{Files, Paths}

/**
  * Created by mirko on 4/10/17.
  */
class TicTacToeImpl(fileName: String) extends TicTacToe {

  private val engine = mkPrologEngine(
    new Theory(Files.readString(Paths.get(getClass.getClassLoader.getResource(fileName).toURI))).toString
  )
  private var tboard = null
  createBoard()

  override def createBoard(): Unit = {
    val goal = "retractall(board(_)),newboard(B),assert(board(B))"
    solveWithSuccess(engine,goal)
  }

  override def checkCompleted(): Boolean =
    solveWithSuccess(engine,"board(B),boardfilled(B)")


  override def checkVictory(): Boolean =
    solveWithSuccess(engine,"board(B),threeinarow(B)")


  override def isAFreeCell(i: Int, j: Int): Boolean =
    solveWithSuccess(engine,s"board(B),not(filledsquare(B,${i*3+j+1}))")

  private def setCell(pos:Int, player: String): Unit = {
    val goal = s"retract(board(B)),!,setsquare(B,$pos,$player,B2),assert(board(B2))"
    solveWithSuccess(engine,goal)
  }

  override def setHumanCell(i: Int, j: Int): Unit = {
    setCell(i * 3 + j + 1,"'X'")
  }

  override def setComputerCell(): Array[Int] = {
    // a solution which just seeks for the first freecell
    val pos = (for {
      i <- 0 to 2
      j <- 0 to 2
      if isAFreeCell(i, j)
      k = i*3+j+1
    } yield k).head
    // change above, by calling predicate 'response'
    setCell(pos,"'O'")
    Array((pos-1)/3,(pos-1)%3)
  }

  override def toString: String =
    solveOneAndGetTerm(engine,"board(B)","B").toString
}

package it.pps.course.u06lab

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object TicTacToe extends App {
  sealed trait Player {
    def other: Player = this match {
      case X => O;
      case _ => X
    }

    override def toString: String = this match {
      case X => "X";
      case _ => "O"
    }
  }

  case object X extends Player

  case object O extends Player

  case class Mark(x: Int, y: Int, player: Player)
  case class Tris(m1: (Int, Int), m2: (Int, Int), m3: (Int,Int)){
    def toMarkList(player: Player): List[Mark] =
      List(Mark(m1._1, m1._2, player), Mark(m2._1, m2._2, player), Mark(m3._1, m3._2, player))
  }

  type Board = List[Mark]
  type Game = List[Board]

  val winningPositions: List[Tris] = List(Tris((0,0),(0,1),(0,2)), Tris((0,0),(1,0),(2,0)), Tris((0,0),(1,1),(2,2)),
                                          Tris((1,0),(1,1),(1,2)), Tris((0,1),(1,1),(2,1)), Tris((0,2),(1,1),(2,0)),
                                          Tris((2,0),(2,1),(2,2)), Tris((0,2),(1,2),(2,2)))

  implicit class tupleToMarkConverter(tuple2: (Int, Int)){
    def intTupleToMark(player: Player): Mark = Mark(tuple2._1, tuple2._2, player)
  }

  @tailrec
  def find(board: Board, x: Int, y: Int): Option[Player] = board match {
    case h :: t => if(h.x == x && h.y == y) Option(h.player) else find(t, x, y)
    case _ => Option.empty
  }

  def placeAnyMark(board: Board, player: Player): Seq[Board] = {
    val boards: ListBuffer[Board] = ListBuffer()
    for(i <- 0 to 2; j <- 0 to 2){
      val tmp = ListBuffer.from(board)

      if(find(board, i, j).isEmpty) {
        tmp.addOne(Mark(i,j, player))
        boards.addOne(tmp.toList)
      }

    }
    boards.toSeq
  }

  def hasWon(board: Board, player: Player): Boolean = {
    winningPositions.exists(tris => board.contains(tris.m1.intTupleToMark(player)) &&
      board.contains(tris.m2.intTupleToMark(player)) &&
      board.contains(tris.m3.intTupleToMark(player))) &&
    !winningPositions.exists(tris => board.contains(tris.m1.intTupleToMark(player.other)) &&
      board.contains(tris.m2.intTupleToMark(player.other)) &&
      board.contains(tris.m3.intTupleToMark(player.other)))
  }

  def computeAnyGame(player: Player, moves: Int): Stream[Game] = {
    @tailrec
    def playTurn(p: Player, previousTurn: ListBuffer[Board], turn: Int): ListBuffer[Board] = {
      println(previousTurn.size)
      if (turn == moves)
        previousTurn.filter(b => hasWon(b, player) || hasWon(b, player.other))
      else
        playTurn(p.other, previousTurn.foldRight(ListBuffer[Board]())((b, acc) => acc.addAll(placeAnyMark(b, p))), turn + 1)
    }

    if(moves > 0)
      Stream(playTurn(player.other, ListBuffer.from(placeAnyMark(List(), player)),1).toList)
    else
      Stream.empty
  }


  def printBoards(game: Seq[Board]): Unit =
    for (y <- 0 to 2; board <- game.reverse; x <- 0 to 2) {
      print(find(board, x, y) map (_.toString) getOrElse ("."))
      if (x == 2) {
        print(" "); if (board == game.head) println()
      }
    }

  // Exercise 1: implement find such that..
  println(find(List(Mark(0, 0, X)), 0, 0)) // Some(X)
  println(find(List(Mark(0, 0, X), Mark(0, 1, O), Mark(0, 2, X)), 0, 1)) // Some(O)
  println(find(List(Mark(0, 0, X), Mark(0, 1, O), Mark(0, 2, X)), 1, 1)) // None

  // Exercise 2: implement placeAnyMark such that..
  printBoards(placeAnyMark(List(), X))
  //... ... ..X ... ... .X. ... ... X..
  //... ..X ... ... .X. ... ... X.. ...
  //..X ... ... .X. ... ... X.. ... ...
  printBoards(placeAnyMark(List(Mark(0, 0, O)), X))
  //O.. O.. O.X O.. O.. OX. O.. O..
  //... ..X ... ... .X. ... ... X..
  //..X ... ... .X. ... ... X.. ...

  //O.. O.. O.X O.. O.. OX. O.. OXO
  //... ..X ... ... .X. ... ... XOX
  //..X ... ... .X. ... ... X.. XOX

  // Exercise 3 (ADVANCED!): implement computeAnyGame such that..
  computeAnyGame(O, 6) foreach { g => printBoards(g); println(g.size); println() }
  //... X.. X.. X.. XO.
  //... ... O.. O.. O..
  //... ... ... X.. X..
  //              ... computes many such games (they should be 9*8*7*6 ~ 3000).. also, e.g.:
  //
  //... ... .O. XO. XOO
  //... ... ... ... ...
  //... .X. .X. .X. .X.

  // Exercise 4 (VERY ADVANCED!) -- modify the above one so as to stop each game when someone won!!
}

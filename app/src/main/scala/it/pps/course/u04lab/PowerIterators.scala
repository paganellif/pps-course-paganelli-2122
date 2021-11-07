package it.pps.course.u04lab

import it.pps.course.u04lab.Lists._
import it.pps.course.u04lab.Optionals._

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A])
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = ???

  override def fromList[A](list: List[A]): Unit = ???

  override def randomBooleans(size: Int): PowerIterator[Boolean] = ???
}

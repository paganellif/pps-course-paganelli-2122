package it.pps.course.u04lab

import scala.util.Random

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {
  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A]
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
    fromStream(Stream.iterate(start)(successive))

  override def fromList[A](list: List[A]): PowerIterator[A] = fromStream(list.toStream)

  override def randomBooleans(size: Int): PowerIterator[Boolean] =
    fromStream(Stream.continually(Random.nextBoolean()).take(size))

  private def fromStream[A](stream: Stream[A]): PowerIterator[A] = new PowerIterator[A] {
    private var pastList: List[A] = List()
    private val iterator: Iterator[A] = stream.iterator

    override def next(): Option[A] = if (iterator.hasNext) {
      val a = iterator.next()
      pastList ++= List(a)
      Option(a)
    } else {
      Option.empty
    }

    override def allSoFar(): List[A] = pastList

    override def reversed(): PowerIterator[A] = fromStream(pastList.reverse.toStream)
  }
}

package it.pps.book

/* La keyword object crea un oggetto di tipo Singleton:
 * simile a creare un istanza di una classe anonima in Java.
 * Non esiste in Scala l'equivalente della keyword static
 * di Java: la keyword object è utilizzata in Scala dove
 * in Java si utilizza una classe con i membri static.
 *
 */
object SimpleProg {
  def abs(n: Int): Int = if (n < 0) -n else n

  def formatAbs(x: Int): String = {
    val msg = "The absolut value of %d is %d"
    msg.format(x, abs(x))
  }

  def objToString[A](a: A): String = a.toString

  @annotation.tailrec
  def isSorted[A](ordered: (A,A) => Boolean)(as: Array[A]): Boolean = as.length match {
    case 2 => ordered(as(0), as(1))
    case 1 | 0 => false
    case _ => if (ordered(as(0), as(1))) isSorted(ordered)(as.drop(1)) else false
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
  }
}

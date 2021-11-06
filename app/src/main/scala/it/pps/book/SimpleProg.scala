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

  def formatAbs(x: Int) = {
    val msg = "The absolut value of %d is %d"
    msg.format(x, abs(x))
  }

  def objToString[A](a: A): String = a.toString

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
  }
}

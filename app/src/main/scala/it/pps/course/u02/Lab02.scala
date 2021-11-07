package it.pps.course.u02

object Lab02 {
  def factorial(n: Int): Int = {
    /* Inner Function - recursion: loop without mutate a variable counter
     * TailRec: tail recursion
     * Ottimizzazione che porta la ricorsione ad essere efficiente come un ciclo
     * for/while. Chiamata tail call elimination, è una ottimizzazione
     * applicata quando non c'è più lavoro da fare dopo che la chiamata ricorsiva
     * ritorna.
     * Nel caso del fattoriale troviamo già il risultato quando la chiamata ritorna,
     * non c'è bisogno di combinare i risultati di tutto lo stack di funzioni creato
     * dalla funzione ricorsiva.
     */
    @annotation.tailrec
    def go(n: Int, acc: Int): Int = if (n <= 0) acc else go(n-1, n*acc)

    go(n,1)
  }

  def fib(n: Int): Int = n match {
    case 0 | 1 => n
    case _ => fib(n-1)+fib(n-2)
  }

  def tailFib(n: Int): Int = {
    def loop(n: Int, a: Int, b: Int): Int = {
      if(n == 0)
        a
      else if(n == 1)
        b
      else
        loop(n-1, b, a+b)
    }
    loop(n,0,1)
  }

  val parityLiteralFunc: (Int => String) = (n: Int) => if ( n % 2 == 0) "even" else "odd"

  def parityMethodSyntax(n: Int): String = if ( n % 2 == 0) "even" else "odd"

  val empty: String => Boolean = _==""

  val neg: (String => Boolean) => (String => Boolean) = (f: String => Boolean) => !f(_)

  val notEmpty: String => Boolean = neg(empty)

  def genericNeg[A]: (A => Boolean) => (A => Boolean) = (f: A => Boolean) => !f(_)

  val p1: Int => Int => Int => Boolean = x => y => z => x<=y && y<=z
  val p2: (Int, Int, Int) => Boolean = (x,y,z) => p1(x)(y)(z)
  def p3(x: Int)(y: Int)(z: Int): Boolean = x<=y && y<=z
  def p4(x: Int, y: Int, z: Int): Boolean = p3(x)(y)(z)

  def compose[A](f: A => A, g: A => A)(a: A): A = f(g(a))

  sealed trait Shape {
    val perimeter: Double
    val area: Double
  }

  case class Rectangle(b: Double, h: Double) extends Shape {
    override val perimeter: Double = this.b*2 + this.h*2
    override val area: Double = this.b * this.h
  }

  case class Square(l: Double) extends Shape {
    override val perimeter: Double = this.l*4
    override val area: Double = Math.pow(this.l, 2)
  }

  case class Circle(r: Double) extends Shape {
    val pi: Double = 3.14
    override val perimeter: Double = 2*this.r*this.pi
    override val area: Double = Math.pow(this.r, 2)*this.pi
  }

}

package it.pps.course.u13

trait Producer {
  def get(): String
}

// not a subtype of Producer, but truly a mixin
trait ProducerUtils {
  producer: Producer =>
    def getMany(n: Int): Seq[String] = for(i <- 0 until n) yield producer.get()
}

class RandomProducer extends Producer {
  override def get(): String = Math.random().toString
}

class ConstantProducer(s: String) extends Producer {
  override def get(): String = s
}

object Mixins extends App {
  // attaching utils to any Producer
  val producer = new RandomProducer() with ProducerUtils
  println(producer.getMany(2))

  val producer2 = new ConstantProducer("a") with ProducerUtils
  println(producer2.getMany(4))
}

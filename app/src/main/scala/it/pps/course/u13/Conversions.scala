package it.pps.course.u13

import scala.language.implicitConversions

object Conversions extends App {
  case class Point(n:Int, m:Int)

  object Point {
    implicit def tupleToPoint(t: (Int,Int)): Point = {
      println("Converting tuple "+t+" to a point..")
      Point(t._1, t._2)
    }
  }

  def pointToRadius(p: Point): Double = Math.sqrt(p.n*p.n + p.m*p.m)

  println(pointToRadius((3,4)))
  // facility to check if an implicit is active
  println(implicitly[Point]((10,20)))
}

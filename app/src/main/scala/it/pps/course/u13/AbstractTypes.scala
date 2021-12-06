package it.pps.course.u13

import scala.language.{postfixOps, reflectiveCalls}

trait Graph {
  type Node
  type Edge
  def addEdge(n1: Node, n2: Node, e: Edge): Unit
  def nodes: Set[Node]
  def outEdges(n: Node): Set[Edge]
  def inEdges(n: Node): Set[Edge]
}

abstract class GraphImpl() extends Graph {
  private var data = Set[(Node,Edge,Node)]()

  override def addEdge(n1: Node, n2: Node, e: Edge): Unit = data += ((n1,e,n2))
  override def nodes: Set[Node] = (data map(_._1)) ++ (data map(_._3))
  override def outEdges(n: Node): Set[Edge] = data filter (_._1 == n) map (_._2)
  override def inEdges(n: Node): Set[Edge] = data filter (_._3 == n) map (_._2)
}

// Note the whole family of Node and Edge is here specialised by family polimorphism
trait ColouredWeightedGraph extends Graph {
  // Further-bounding Node and Edge with structural types
  type Node <: { def colour: Int } // Nodes should have at least a colour
  type Edge <: { def weight: Double } // Edge should have at least a weight

  // a function using both colours and weights: summing weights out of nodes with colour
  def totalOutWeight(colour: Int): Double =
    (nodes filter (_.colour == colour) toList) flatMap outEdges map (_.weight) sum
}

// An implementation of nodes and edges compliant to ColouredWeightedGraph
case class MyNode(name: String, colour: Int)
case class MyEdge(weight: Double)

// A mixin specifying concrete nodes and edges for graphs
trait MyNodesAndEdges { self: Graph =>
  type Node = MyNode
  type Edge = MyEdge
}

// Some utilities to work for any Graph family
object GraphUtilities {
  // g.Edge is a path-dependent type.. the Edge-type of g
  def getEdges(g: Graph): Set[g.Edge] = (g nodes) flatMap (g outEdges(_))

  // currying is here needed by Scala, to correctly type g.Edge as argument
  def getSource(g: Graph)(e: g.Edge): Option[g.Node] = (g nodes) find (g outEdges(_) contains e)
  def getTarget(g: Graph)(e: g.Edge): Option[g.Node] = (g nodes) find (g inEdges(_) contains e)
}

object AbstractTypes extends App {
  val g = new GraphImpl{
    type Node = String
    type Edge = Int
  }

  g.addEdge("a","b",1)
  g.addEdge("c","d",2)
  g.addEdge("a","d",3)
  println(g.nodes,g.outEdges("a"),g.inEdges("d")) //(Set(a, c, b, d),Set(1, 3),Set(2, 3))

  // Note how the trait ColouredWeightedGraph mixes-in the abstract implementation
  val g1 = new GraphImpl() with MyNodesAndEdges with ColouredWeightedGraph

  // Without family polimorphism , e.g. in Java , we cannot
  // guarantee that both Node and Edge are coherently specialised
  g1.addEdge(MyNode("a",1),MyNode("b",1),MyEdge(1.0))
  g1.addEdge(MyNode("c",3),MyNode("d",4),MyEdge(2.0))
  g1.addEdge(MyNode("a",1),MyNode("d",4),MyEdge(3.0))
  g1.addEdge(MyNode("b",1),MyNode("d",4),MyEdge(1.0))
  println(g1.totalOutWeight(1)) // 5.0

  // Scala correctly types g.Edge as being MyEdge
  val nodes: Set[MyEdge] = GraphUtilities.getEdges(g1)
  println(nodes) // Set(MyEdge(1.0), MyEdge(3.0), MyEdge(2.0))
  println(GraphUtilities.getSource(g1)(MyEdge(1.0))) // Some(MyNode(a,1))
  println(GraphUtilities.getTarget(g1)(MyEdge(1.0))) // Some(MyNode(b,2))
}

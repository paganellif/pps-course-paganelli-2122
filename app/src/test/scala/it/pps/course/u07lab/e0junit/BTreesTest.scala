package it.pps.course.u07lab.e0junit


// JUnit 4
// import org.junit.Test
// import org.junit.Assert._

// JUnit 5
import it.pps.course.u02.BTrees.Tree.{Branch, Leaf, count, find, size}
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class BTreesTest {
  val tree = Branch(Branch(Leaf(1),Leaf(2)),Leaf(1))

  @Test def testCount(){
    assertEquals(2, count(tree,1))
    assertEquals(1, count(tree,2))
    assertEquals(0, count(tree,3))
  }

  @Test def testSize(){
    assertEquals(3, size(tree))
  }

  @Test def testFind(){
    assertTrue(find(tree,2))
    assertFalse(find(tree,4))
  }
}
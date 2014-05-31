package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("times") {
    new TestTrees {
      val result = times(List('a', 'b', 'a'))
      assert(result.contains('a', 2))
      assert(result.contains('b', 1))
      assert(result.size == 2)
    }
  }

/*  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  } */

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    assert(singleton(List(Leaf('a', 1))))
    assert(!singleton(List(Leaf('a', 1), Leaf('b', 3))))
    assert(singleton(List(Fork(Leaf('a', 1), Leaf('b', 3), List('a', 'b'), 4))))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

/*  test("full code tree") {
    val tree = createCodeTree(List('h', 'e', 'l', 'l', 'o'))
    val expectedTree = makeCodeTree(Leaf('l', 2), makeCodeTree(Leaf('o', 1), makeCodeTree(Leaf('h', 1), Leaf('e', 1))))
    assert(tree == expectedTree)
  }*/

  /*test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }*/
}

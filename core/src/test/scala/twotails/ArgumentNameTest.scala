package twotails

import org.scalatest.{ FlatSpec, Matchers }

class Fly{
  @mutualrec final def one(x: Int, y: Int): Int = if(0 < x) two(x-1, y) else y
  @mutualrec final def two(u: Int, v: Int): Int = if(0 < u) one(u-1, v) else v
}

class Gnat{
  @mutualrec final def one(x: Int)(y: Int): Int = if(0 < x) two(x-1)(y) else y
  @mutualrec final def two(u: Int)(v: Int): Int = if(0 < u) one(u-1)(v) else v
}

class ArgumentNameTest extends FlatSpec with Matchers{
  val fourK = 400000

  "mutually recursive functions with differing argument names" should "just work" in{
    val fly = new Fly

    fly.one(fourK, 1) should equal(1)
  }

  "mutually recursive functions with multi-param lists with differing argument names" should "just work" in{
    val gnat = new Gnat

    gnat.one(fourK)(0) should equal(0)
  }
}
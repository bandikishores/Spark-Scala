package com.bandi

import java.util.Date

object TestScala {

  /*  def main(args: Array[String]) {
    var wordList = List(List("wor", "a", "ag"), List("wor", "dfg", "ag", "ag"))
    wordList.flatMap(ilist => ilist.toStream).map(wo => (wo,1));

    val date = new Date
    val logWithDateBound = log(date, _: String)
    log(date, "message1")

    Thread.sleep(1000)
    logWithDateBound("message2")

    Thread.sleep(1000)
    logWithDateBound("message3")
  }*/

  def log(date: Date, message: String) = {
    println(date + "----" + message)
  }

  def NoMethod(jd: Int = 2) = {
    println("No method");
    jd;
  }

  def myMethod[A](t: => Int, a: A = "as", b: Int = 7, c: Int) {
    println("myMthod")
    println(a, b, c)
    println(a);
    println(anotherMethod(t));
  }

  def anotherMethod[A](x: A) = {
    def testNested() = {
      println("Nested")
    }
    println("now");
    testNested()
    x.toString
  }

}
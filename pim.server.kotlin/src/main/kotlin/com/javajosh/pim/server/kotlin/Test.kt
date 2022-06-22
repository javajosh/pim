package com.javajosh.pim.server.kotlin

fun main(){
  println("hello");
  printSum(2,3)
}

fun sum(a: Int, b: Int) : Int {
  return a + b
}

fun add(a: Int, b: Int) = a + b;

fun printSum(a: Int, b: Int) : Unit{
  val c = 3
  println("$a $b ${a + b}")

}

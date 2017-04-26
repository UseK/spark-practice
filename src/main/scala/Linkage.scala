package com.usek.stockfoldermeeting
/**
  * Created by yf on 2017/04/23.
  */
object Linkage {
  def isHeader(line: String): Boolean = {
    line.contains("id_1")
  }

  def toDouble(s: String): Double = {
    if (s == "?") Double.NaN else s.toDouble
  }

  def parse(line: String) = {
    println(line)
    val pieces = line.split(",")
    val id1 = pieces(0).toInt
    val scores = pieces.slice(2, pieces.length-1).map(toDouble)
  }
}

package com.usek.stockfoldermeeting
/**
  * Created by yf on 2017/04/23.
  */
object Linkage {
  def isHeader(line: String): Boolean = {
    line.contains("id_1")
  }

  def parse(line: String) = {
    val pieces = line.split(",")
    print(pieces(0).toInt)
  }
}

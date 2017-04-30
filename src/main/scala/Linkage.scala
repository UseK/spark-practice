package com.usek.stockfoldermeeting

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by yf on 2017/04/23.
  */
object Linkage {
  def isHeader(line: String): Boolean = {
    line.contains("id_1")
  }
  def isBody(line: String): Boolean = !isHeader(line)

  def toDouble(s: String): Double = {
    if (s == "?") Double.NaN else s.toDouble
  }

  case class MatchData(id1: Int,
                       id2: Int,
                       scores: Array[Double],
                       matched: Boolean)

  def parse(line: String): MatchData = {
    //println(line) //36950,42116,1,?,1,1,1,1,1,1,1,TRUE
    val pieces = line.split(",")
    val id1 = pieces(0).toInt
    val id2 = pieces(1).toInt
    val scores = pieces.slice(2, pieces.length-1).map(toDouble)
    val matched = pieces(pieces.length-1) == "TRUE"
    MatchData(id1, id2, scores, matched)
  }

  def histogram(mds: RDD[MatchData]): RDD[(Boolean, Int)] = {
    val grouped = mds.groupBy(_.matched)
    val hist = grouped.mapValues(_.size)
    hist
  }
}

class MySpark(path: String) {
  val sc = new SparkContext("local", "example")
  val rawBlocks = sc.textFile(path)
  val head = rawBlocks.take(10)
  val noHeader = rawBlocks.filter(!Linkage.isHeader(_))
  def stop: Unit = sc.stop
}

object MySpark {
  def apply(path: String) = new MySpark(path)
}

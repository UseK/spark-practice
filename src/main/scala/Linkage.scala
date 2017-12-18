package com.usek.stockfoldermeeting
import java.lang.Double.isNaN

import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.rdd.RDD
import org.apache.spark.util.StatCounter
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.DecisionTreeClassifier


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
  val parsed = noHeader.map(Linkage.parse)

  def stop: Unit = sc.stop

  def mds() = {
    head.filter(Linkage.isBody).map(Linkage.parse)
  }

  def matchCounts() = {
    parsed.map(_.matched).countByValue()
  }

  def scoreStats(): IndexedSeq[StatCounter]   = {
    val N = parsed.first.scores.length
    (0 until N).map(i => scoreStat(i))
  }

  def scoreStat(n: Int=0) = {
    parsed.map(_.scores(n)).filter(!isNaN(_)).stats()
  }
}

object MySpark {
  def apply(path: String) = new MySpark(path)
}

class MyDataFrame(path: String) {
  val spark = SparkSession.builder().
    master("local").
    appName("example").
    getOrCreate()
  val parsed = spark.read.
    option("header", "true").
    option("nullValue", "?").
    option("inferSchema", "true").
    csv(path)
  val featureCol = (Array("face", "academic_background", "personality"))
  val assembler = new VectorAssembler().
    setInputCols(featureCol).
    setOutputCol("featureVector")
  val assembledTrainData = assembler.transform(parsed)
  assembledTrainData.select("featureVector").show(truncate = false)
  val classifier = new DecisionTreeClassifier().
    setSeed(7777.toLong).
    setLabelCol("result").
    setFeaturesCol("featureVector").
    setPredictionCol("prediction")
  val model = classifier.fit(assembledTrainData)
  println(model.toDebugString)
  val predictions = model.transform(assembledTrainData)
  predictions.select("result", "prediction", "probability").
    show()



}

object MyDataFrame {
  def apply(path: String): MyDataFrame = new MyDataFrame(path)
}


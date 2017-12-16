import com.usek.stockfoldermeeting.{MySpark, Linkage => LK, MyDataFrame}
import org.apache.hadoop.yarn.util.RackResolver
import org.apache.log4j.{Level, Logger}
import org.apache.spark.util.StatCounter

/**
  * Created by yf on 2017/04/30.
  */
object RunSpark {


  def main(args: Array[String]): Unit = {
    Logger.getLogger(classOf[RackResolver]).getLevel
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    System.setProperty("log4j.warn", "")
    val myDF = MyDataFrame("data/linkage")
    myDF.parsed.printSchema()
    myDF.parsed.show()
  }

  def runWithRDD = {
    val mySpark = MySpark("data/linkage")
    println("----------------------")
    val nasRDD = mySpark.parsed.map(_.scores.map(NAStatCounter(_)))
    val reduced = nasRDD.reduce((n1, n2) => {
      n1.zip(n2).map { case (a, b) => a.merge(b) }
    })
    reduced.foreach(println)

    println("----------------------")
    mySpark.parsed.cache
    mySpark.stop
  }

  def useNaStatCounter = {
    val nas1 = NAStatCounter(10.0)
    println(nas1)
    //stats: (count: 1, mean: 10.000000, stdev: 0.000000,
    //max: 10.000000, min: 10.000000) NaN: 1nas1.add(Double.NaN)

    val sCounter = new StatCounter()
    val newCounter = sCounter.merge(10.0)
    newCounter.merge(Double.NaN)
    println(newCounter)
    //(count: 2, mean: NaN, stdev: NaN, max: NaN, min: NaN)
  }
}

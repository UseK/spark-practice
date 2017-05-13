import com.usek.stockfoldermeeting.{MySpark, Linkage => LK}
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

    val mySpark = MySpark("data/linkage")
    println("----------------------")
    println("----------------------")
    mySpark.parsed.cache
    mySpark.stop
  }

  def useNaStatCounter = {
    val nas1 = NAStatCounter(10.0)
    nas1.add(Double.NaN)
    println(nas1)
    val sCounter = new StatCounter()
    val newCounter = sCounter.merge(10.0)
    newCounter.merge(Double.NaN)
    println(newCounter)
  }
}

import com.usek.stockfoldermeeting.{MySpark, Linkage => LK}
import org.apache.hadoop.yarn.util.RackResolver
import org.apache.log4j.{Level, Logger}

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
    val mds = mySpark.head.filter(LK.isBody).map(LK.parse)
    val parsed = mySpark.noHeader.map(LK.parse)
    println("----------------------")
    val matchCounts = parsed.map(_.matched).countByValue()
    println(matchCounts) //Map(true -> 20931, false -> 5728201)
    println("----------------------")
    parsed.cache
    mySpark.stop
  }
}

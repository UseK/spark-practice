import com.usek.stockfoldermeeting.{Linkage => LK, MySpark}

/**
  * Created by yf on 2017/04/30.
  */
object RunSpark {


  def main(args: Array[String]): Unit = {
    val mySpark = MySpark("data/linkage")
    val mds = mySpark.head.filter(LK.isBody).map(LK.parse)
    val parsed = mySpark.noHeader.map(LK.parse)
    println("----------------------")
    println(parsed)
    println("----------------------")
    mySpark.stop
  }
}

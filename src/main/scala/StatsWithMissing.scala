import org.apache.spark.util.StatCounter
import java.lang.Double.isNaN

/**
  * Created by yf on 2017/05/14.
  */
class StatsWithMissing {

}

class NAStatCounter extends Serializable {
  val stats: StatCounter = new StatCounter()
  var missing: Long = 0

  def add(x: Double): NAStatCounter = {
    if (isNaN(x)) {
      missing += 1
    } else {
      stats.merge(x)
    }
    this
  }

  def merge(other: NAStatCounter): NAStatCounter = {
    stats.merge(other.stats)
    missing += other.missing
    this
  }

  override def toString: String = {
    s"stats: ${stats.toString} NaN: $missing"
  }
}

object NAStatCounter extends Serializable {
  def apply(x: Double): NAStatCounter = new NAStatCounter().add(x)
}

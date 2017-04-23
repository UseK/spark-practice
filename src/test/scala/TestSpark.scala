import com.usek.stockfoldermeeting.{Linkage => lk}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.scalatest.FunSpec

/**
  * Created by yf on 2017/04/23.
  */
class TestSpark extends FunSpec {
  describe("Spark") {
    it("works") {
      val sc = new SparkContext("local", "example")
      val rawBlocks = sc.textFile("data/linkage")
      val head = rawBlocks.take(10)
      val expected =
        """"id_1","id_2","cmp_fname_c1",
          |"cmp_fname_c2","cmp_lname_c1",
          |"cmp_lname_c2","cmp_sex",
          |"cmp_bd","cmp_bm",
          |"cmp_by","cmp_plz",
          |"is_match"""".stripMargin.replace("\n", "")
      assert(expected == head(0))
      assert(head(0) == rawBlocks.take(10)(0))
      assert(lk.isHeader(head(0)))
      val bodies = head.filter(!lk.isHeader(_))
      assert(9 == bodies.length)
      lk.parse(head(5))
    }
  }
}

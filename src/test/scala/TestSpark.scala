import com.usek.stockfoldermeeting.{Linkage => LK}
import org.apache.spark.SparkContext
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
      assert(LK.isHeader(head(0)))
      val bodies = head.filter(!LK.isHeader(_))
      assert(9 == bodies.length)
      LK.parse(head(5))
      class AieeInt  {
        def aiee = "aiee"
      }
      implicit def int2AieeInt(x: Int): AieeInt = new AieeInt
      implicit def int2String(x: Int): String = "aiee"
      implicit def arraryStiring2Int(x: Array[String]): Int = {
        114514
      }
      println(1 + Array("1"))
      val one = 1
      println(one.aiee)
    }
  }
}

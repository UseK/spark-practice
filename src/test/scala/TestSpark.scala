import com.usek.stockfoldermeeting.{Linkage => LK}
import org.apache.spark.SparkContext
import org.scalatest.FunSpec

/**
  * Created by yf on 2017/04/23.
  */
class TestSpark extends FunSpec {
  describe("Spark") {
    val sc = new SparkContext("local", "example")
    val rawBlocks = sc.textFile("data/linkage")
    val head = rawBlocks.take(10)
    val noHeader = rawBlocks.filter(!LK.isHeader(_))

    it("works") {
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
    }

    it("parse") {
      val matchData = LK.parse(head(5))
      assert(matchData.id1 == 36950)
      assert(matchData.id2 == 42116)
      assert(matchData.scores(0) == 1.0)
      assert(matchData.matched == true)
    }

    it("bodies") {
      val bodies = head.filter(!LK.isHeader(_))
      assert(9 == bodies.length)
    }

    it("filter") {
      val mds = head.filter(!LK.isHeader(_)).map(LK.parse)
    }

    it("cache") {
      val parsed = noHeader.map(LK.parse)
      parsed.cache()
    }
  }
}

/**
  * Created by yf on 2017/04/03.
  */
import com.usek.stockfoldermeeting.Crawler
import org.scalatest.FunSpec
class TestCrawler extends FunSpec {
  describe("Crawler") {
    it("can get PDF file") {
      val crawler = new Crawler()
      val expectedStr = "https://www.google.co.jp/search?q=日立 株主総会"
      assert(expectedStr == crawler.google_search_uri.format("日立 株主総会"))
      crawler.get_pdf_links("日立 株主総会")
    }
  }
}

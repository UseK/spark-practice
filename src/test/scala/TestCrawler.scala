/**
  * Created by yf on 2017/04/03.
  */
import com.usek.stockfoldermeeting.Crawler
import org.scalatest.FunSpec
class TestCrawler extends FunSpec {
  describe("Crawler") {
    it("can get PDF file") {
      val crawler = Crawler()
      val expectedStr = "https://www.google.co.jp/search?q=日立 株主総会"
      assert(expectedStr == crawler.google_search_uri.format("日立 株主総会"))
      val firstResult = crawler.getSearchResults("日立 株主総会").head
      val links = crawler.getLinks(firstResult, "a[href]")
      //println(links)
      val myMap = Map('one -> 1)
    }
  }
}

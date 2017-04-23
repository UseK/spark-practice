package com.usek.stockfoldermeeting
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
/**
  * Created by yf on 2017/04/03.
  */
class Crawler {
  val browser = JsoupBrowser()
  val google_search_uri = "https://www.google.co.jp/search?q=%s"
  val URIPattern = """(http://.+?)&.+$""".r

  def getSearchResults(companyName: String): Iterable[String] = {
    val uri = google_search_uri.format(companyName)
    getLinks(uri, "h3>a")
  }

  def getLinks(uri: String, selector: String) = {
    val doc = browser.get(uri)
    val rawLinks = doc.root.select(selector).map(_.attr("href"))
    println(rawLinks)
    rawLinks.flatMap(URIPattern.findFirstMatchIn)
      .map(_.group(1))
  }
}

object Crawler {
  def apply(): Crawler = new Crawler()
}

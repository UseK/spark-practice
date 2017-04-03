package com.usek.stockfoldermeeting
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
/**
  * Created by yf on 2017/04/03.
  */
class Crawler {
  val browser = JsoupBrowser()
  val google_search_uri = "https://www.google.co.jp/search?q=%s"

  def get_pdf_links(companyName: String): List[String] = {
    val doc = browser.get(google_search_uri.format(companyName))
    val links = doc.root.select("h3>a").headOption.fold("")(_.text.trim)
    print(links)
    List("aiee")
  }

}

/**
  * Created by yf on 2017/04/03.
  */
import com.usek.stockfoldermeeting.PDF
import org.scalatest.FunSpec
class TestPDF extends FunSpec {
  describe("PDF library") {
    it("The number of dates is 18") {
      val path = "data/pdf/hitachi.pdf"
      val pdf = new PDF(path)
      val dates = pdf.dates()
      assert(dates.length == 18)
    }
  }
}

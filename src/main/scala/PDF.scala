import java.awt.print.PrinterJob
import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.printing.PDFPageable
import org.apache.pdfbox.text.PDFTextStripper

/**
  * Created by yf on 2017/03/22.
  */
class PDF(path: String) {
  val document: PDDocument = PDDocument.load(new File(path))

  def text(): String = {
    val stripper = new PDFTextStripper()
    stripper.getText(document)
  }

  def dates(): List[String] = {
    val year_month_date = "([0-9０-９]+)年([0-9０-９]+)月([0-9０-９]+)日".r
    text().lines.flatMap (
      year_month_date.findFirstIn(_)
    ).toList
  }
}

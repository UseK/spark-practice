import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

/**
  * Created by yf on 2017/03/22.
  */
class PDF(path: String) {
  val document: PDDocument = PDDocument.load(new File(path))
  val year_month_date: Regex = "([0-9０-９]+)年([0-9０-９]+)月([0-9０-９]+)日".r

  def text(): String = {
    val stripper = new PDFTextStripper()
    stripper.getText(document)
  }

  def dates(): Iterator[Regex.Match] = {
    text().lines.flatMap (
      year_month_date.findFirstMatchIn(_)
    )
  }
}

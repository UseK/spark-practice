import java.awt.print.PrinterJob

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.printing.PDFPageable
import java.awt.print.Book
import java.awt.print.PageFormat
import java.awt.print.Paper
import java.awt.print.PrinterException
import java.awt.print.PrinterJob
import java.io.File
import java.io.IOException
import javax.print.attribute.HashPrintRequestAttributeSet
import javax.print.attribute.PrintRequestAttributeSet
import javax.print.attribute.standard.PageRanges

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.printing.PDFPageable
import org.apache.pdfbox.printing.PDFPrintable
import org.apache.pdfbox.text.PDFTextStripper

import scala.util.matching.Regex

/**
  * Created by yf on 2017/03/12.
  */
class Hello {

}

object Hello {
  def main(args: Array[String]): Unit = {
    val path ="data/pdf/hitachi.pdf"
    val pdf = new PDF(path)
    //val f = new File()
    //print(f.exists())
    println(pdf.dates)
  }
}

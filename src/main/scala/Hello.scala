import java.awt.print.PrinterJob
import java.io.File

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

/**
  * Created by yf on 2017/03/12.
  */
class Hello {

}

object Hello {
  def main(args: Array[String]): Unit = {
    println("hello sbt")
    val path ="data/pdf/hitachi.pdf"
    //val f = new File()
    //print(f.exists())
    val document = PDDocument.load(new File(path))
    print(document)
  }

  def print(document: PDDocument): Unit = {
    val job = PrinterJob.getPrinterJob()
    job.setPageable(new PDFPageable(document))
    //val attr = new HashPrintRequestAttributeSet()
    //attr.add(new PageRanges(1, 1)) // pages 1 to 1
    println("<<<")
    job.print()
    println(">>>")
  }
}

/**
  * Created by yf on 2017/03/12.
  */
class Hello {

}

object Hello {
  def main(args: Array[String]): Unit = {
    val path ="data/pdf/hitachi.pdf"
    val pdf = new PDF(path)
    println(pdf.year_month_date.toString())
    for (m <- pdf.dates()) {
      println("---------------")
      println(m.source)
      println(m.before, m.after)
    }
    println(pdf.dates())
  }
}

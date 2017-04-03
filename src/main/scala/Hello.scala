import com.usek.stockfoldermeeting.PDF

/**
  * Created by yf on 2017/03/12.
  */
class Hello {

}

object Hello {
  def main(args: Array[String]): Unit = {
    val path ="data/pdf/hitachi.pdf"
    val pdf = new PDF(path)
  }
}

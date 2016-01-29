import android.graphics.{Bitmap, BitmapFactory}

object Main {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty) print("No file specified. Please pass the path to an image as an argument.")
    else Option(BitmapFactory.decodeFile(args(0))) match {
      case Some(bitmap) => doStuff(bitmap)
      case None => print("Unable decode file as an image.")
    }
  }

  def doStuff(bitmap: Bitmap): Unit = {

  }
}

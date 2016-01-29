import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

object Main {

  def main(args: Array[String]): Unit = {
    if (args.isEmpty) print("No file specified. Please pass the path to an image as an argument.")
    else ImageLoader.fromFile(new File(args(0))) match {
      case Some(bitmap) => doStuff(bitmap)
      case None => print("Unable decode file as an image.")
    }
  }

  def doStuff(image: BufferedImage): Unit = {
    print("ayy")
  }

  def averageColor(image: BufferedImage): Color = {
    ???
  }

  def sectionImage(image: BufferedImage, granularity: Int): Seq[BufferedImage] = {
    ???
  }

  def findImageWithAverageColor(color: Color): BufferedImage = {
    ???
  }

  def rebuildImage(sections: Seq[BufferedImage]): BufferedImage = {
    ???
  }
}

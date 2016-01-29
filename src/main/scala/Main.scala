import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

object Main {

  def main(args: Array[String]): Unit = {
    if (args.length < 2) print("Please pass the path to an image as an argument as well as a granularity.")
    else ImageLoader.fromFile(new File(args(0))) match {
      case Some(bitmap) => doStuff(bitmap, args(1).toInt)
      case None => print("Unable decode file as an image.")
    }
  }

  def doStuff(image: BufferedImage, granularity: Int): Unit = {
    sectionImage(image, granularity)
  }

  def averageColor(image: BufferedImage, startX: Int, startY: Int, sectionSize: Int): Color = {
    var finalX = startX + sectionSize
    var finalY = startY + sectionSize

    if (finalX > image.getWidth) finalX = image.getWidth
    if (finalY > image.getHeight) finalY = image.getHeight

    var sumRed = 0
    var sumGreen = 0
    var sumBlue = 0

    for (x <- startX until finalX) {
      for (y <- startY until finalY) {
        val pixel : Color = new Color(image.getRGB(x, y))
        sumRed += pixel.getRed
        sumGreen += pixel.getGreen
        sumBlue += pixel.getBlue
      }
    }

    val area = sectionSize * sectionSize

    new Color(sumRed / area, sumGreen / area, sumBlue / area)
  }

  def sectionImage(image: BufferedImage, granularity: Int): Array[Array[Color]] = {
    val largestSide = math.max(image.getWidth, image.getHeight)
    val sectionDimension = largestSide/granularity

    val numOfColumns = image.getWidth/sectionDimension
    val numOfRows = image.getHeight/sectionDimension
    val sectionColors = Array.ofDim[Color](numOfColumns, numOfRows)

    for (x <- 0 until numOfColumns) {
      for (y <- 0 until numOfRows) {
        val startX = x * sectionDimension
        val startY = y * sectionDimension
        sectionColors(x)(y) = averageColor(image, startX, startY, sectionDimension)
      }
    }


    sectionColors
  }

  def findImageWithAverageColor(color: Color): BufferedImage = {
    ???
  }

  def rebuildImage(sections: Seq[BufferedImage]): BufferedImage = {
    ???
  }
}

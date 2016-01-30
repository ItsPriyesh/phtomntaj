import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import scala.collection.SortedMap

object Main {

  var sortedThumbnailsByColor = thumbnailsByAverageColor

  def main(args: Array[String]): Unit = {
    if (args.length < 2) print("Please pass the path to an image as an argument as well as a granularity.")
    else ImageLoader.fromFile(new File(args(0))) match {
      case Some(bitmap) => doStuff(bitmap, args(1).toInt)
      case None => print("Unable decode file as an image.")
    }
  }

  def doStuff(image: BufferedImage, granularity: Int): Unit = {
    val averageColors = sectionImage(image, granularity).map(averageColor)
  }

  def averageColor(image: BufferedImage): Color = {
    var sumRed = 0
    var sumGreen = 0
    var sumBlue = 0

    for (x <- 0 until image.getWidth) {
      for (y <- 0 until image.getHeight) {
        val pixel : Color = new Color(image.getRGB(x, y))
        sumRed += pixel.getRed
        sumGreen += pixel.getGreen
        sumBlue += pixel.getBlue
      }
    }

    val area = image.getWidth * image.getHeight

    new Color(sumRed / area, sumGreen / area, sumBlue / area)
  }

  def sectionImage(image: BufferedImage, granularity: Int): Array[BufferedImage] = {
    val largestSide = math.max(image.getWidth, image.getHeight)
    val sectionDimension = largestSide/granularity

    val numOfColumns = image.getWidth/sectionDimension
    val numOfRows = image.getHeight/sectionDimension
    val sectionedImages = Array.ofDim[BufferedImage](numOfColumns, numOfRows)

    for (x <- 0 until numOfColumns) {
      for (y <- 0 until numOfRows) {
        val startX = x * sectionDimension
        val startY = y * sectionDimension
        sectionedImages(x)(y) = image.getSubimage(startX, startY, sectionDimension, sectionDimension)
      }
    }

    sectionedImages flatten
  }

  def thumbnailsByAverageColor: SortedMap[String, Any] = {
    var sortedThumbnails = SortedMap[String, Any]()
    val images: List[BufferedImage] = ImageRetriever.getImagesFrom500Px
    images.foreach(image => {
      sortedThumbnails += (colorString(averageColor(image)) -> image)
    })

    sortedThumbnails
  }

  def colorString(color: Color): String = {
    s"${"%03d".format(color.getRed)}${"%03d".format(color.getBlue)}${"%03d".format(color.getGreen)}"
  }

  def findImageWithAverageColor(color: Color): BufferedImage = {
    ???
  }

  def rebuildImage(sections: Seq[BufferedImage]): BufferedImage = {
    ???
  }
}
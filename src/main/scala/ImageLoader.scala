import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import scala.util.Try

object ImageLoader {
  def fromFile(file: File): Option[BufferedImage] = Try(ImageIO.read(file)).toOption
}

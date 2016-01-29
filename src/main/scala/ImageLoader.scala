import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import scala.util.Try

object ImageLoader {
  def fromFile(file: File): Option[BufferedImage] = Try(ImageIO.read(file)).toOption
  def fromUrl(url: URL): Option[BufferedImage] = Try(ImageIO.read(url)).toOption
}

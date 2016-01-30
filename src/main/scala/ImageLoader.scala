import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

import org.graphstream.ui.util.swing.ImageCache

import scala.util.Try

object ImageLoader {
  def fromFile(file: File): Option[BufferedImage] = Try(ImageIO.read(file)).toOption
  def fromUrl(url: URL): Option[BufferedImage] = ImageCache.loadImage(url.toString)
}

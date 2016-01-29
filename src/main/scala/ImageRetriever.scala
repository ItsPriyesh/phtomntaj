import java.awt.image.BufferedImage
import java.net.URL
import scala.collection.JavaConverters._

object ImageRetriever {
  def getImagesFrom500Px: List[BufferedImage] = PxApiService.getInstance()
    .getPhotos
    .asScala
    .toList
    .map(photo => ImageLoader.fromUrl(new URL(photo.imageUrl)).get)
}

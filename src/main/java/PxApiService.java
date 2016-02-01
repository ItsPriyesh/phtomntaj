import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.ArrayList;
import java.util.List;

public class PxApiService {

    private class PhotosResult { List<Photo> photos; }

    private interface Service {
        @GET("/photos")
        PhotosResult getPhotos(@Query("feature") String feature,
                       @Query("rpp") int results,
                       @Query("image_size") int imageSize,
                       @Query("consumer_key") String consumerKey,
                       @Query("page") int pageNumber);
    }

    private static final String API_URL = "https://api.500px.com/v1/";
    private static final int NUMBER_OF_PAGES = 10;

    private static PxApiService sApiService = null;
    private final Service service;

    private PxApiService() {
        service = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .build()
                .create(Service.class);
    }

    public static PxApiService getInstance() {
        if (sApiService == null) sApiService = new PxApiService();
        return sApiService;
    }

    public List<Photo> getPhotos() {
        List<Photo> photos = new ArrayList<>();
        for (int i = 1; i < NUMBER_OF_PAGES; i++) {
            photos.addAll(service.getPhotos("popular", 100, 100, System.getenv("PX_CONSUMER_KEY"), i).photos);
        }
        return photos;
    }
}

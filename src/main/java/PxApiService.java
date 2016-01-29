import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public class PxApiService {

    private class PhotosResult {
        List<Photo> photos;
    }

    private interface Service {
        @GET("/photos")
        void getPhotos(@Query("feature") String feature,
                       @Query("rpp") int results,
                       @Query("image_size") int imageSize,
                       @Query("consumer_key") String consumerKey,
                       Callback<PhotosResult> callback);
    }

    private static final String API_URL = "https://api.500px.com/v1/";

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

    interface PhotosListener {
        void onSuccess(List<Photo> photos);
        void onError();
    }

    public void getPhotos(PhotosListener listener) {
        service.getPhotos("popular", 100, 100, System.getenv("PX_CONSUMER_KEY"), new Callback<PhotosResult>() {
            @Override
            public void success(PhotosResult photosResult, Response response) {
                listener.onSuccess(photosResult.photos);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }

}

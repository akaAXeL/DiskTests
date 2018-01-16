import java.util.concurrent.TimeUnit;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
public class Config {
    public static final String THIRDPARTY_RESOURCE = "https://www.google.ru/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
    public static final String API_URL = "https://cloud-api.yandex.net/v1/disk/resources";
    public static final String API_OPERATIONS_URL = "https://cloud-api.yandex.net/v1/disk/operations";
    public static final String AUTH_TOKEN = "OAuth SET YOUR_OWN";
    public static final long TIMEOUT = TimeUnit.SECONDS.toMillis(20);
}

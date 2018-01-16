import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
public class Config {
    private Properties prop = new Properties();
    private InputStream input = null;

    String THIRDPARTY_RESOURCE;
    String API_URL;
    String API_OPERATIONS_URL;
    String AUTH_TOKEN;
    long TIMEOUT;


    private static volatile Config instance;

    public static Config getInstance() {
        Config localInstance = instance;
        if (localInstance == null) {
            synchronized (Config.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Config();
                }
            }
        }
        return localInstance;
    }
    private Config() {
        try {
            input = getClass().getResourceAsStream("project.properties");
            prop.load(input);
            THIRDPARTY_RESOURCE = prop.getProperty("THIRDPARTY_RESOURCE");
            API_URL = prop.getProperty("API_URL");
            API_OPERATIONS_URL = prop.getProperty("API_OPERATIONS_URL");
            AUTH_TOKEN = prop.getProperty("AUTH_TOKEN");
            TIMEOUT = TimeUnit.SECONDS.toMillis(Long.parseLong(prop.getProperty("TIMEOUT")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

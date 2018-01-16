import com.mashape.unirest.http.Unirest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UploadFileTest.class})
public class FilesFoldersSuite {
    static Logger logger = LoggerFactory.getLogger("FilesFoldersSuite");

    @BeforeClass
    public static void setUp() {
        logger.info("Test now starting");
    }

    @AfterClass
    public static void tearDown() {
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            logger.warn("It's not an error, but pay attention: " +  e.getMessage());
        }
    }
}

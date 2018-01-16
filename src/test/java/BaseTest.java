import com.mashape.unirest.http.HttpResponse;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.isIn;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */


public class BaseTest {
    protected static ApiClient apiClient = ApiClient.getInstance();
    protected static Config config = Config.getInstance();

    @Step("Проверка http status code")
    static void chechHttpCode(HttpResponse response) {
        saveTextLog(response.getBody().toString());
        List<Integer> codes = IntStream.range(200, 300).mapToObj(i->i).collect(Collectors.toList());
        Assert.assertThat("Status code is 2xx", response.getStatus(), isIn(codes));
    }

    @Attachment("response.log")
    static String saveTextLog(String message) {
        return message;
    }

    static void fail(Throwable t, String response){
        saveTextLog(response);
        Assert.fail(t.getMessage());
    }
}

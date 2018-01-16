import com.mashape.unirest.http.HttpResponse;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */


public class BaseTest {
    protected static ApiClient apiClient = ApiClient.getInstance();

    @Step("Проверка http status code")
    static void chechHttpCode(HttpResponse response) {
        Assert.assertThat("Status code is in 4xx or 5xx range", response.getStatus(), allOf(greaterThanOrEqualTo(599),lessThanOrEqualTo(400)));
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

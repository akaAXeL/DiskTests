import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Step;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

/**
 * Created by a.a.perfilyev on 16.01.2018.
 */
public class UploadFileTest extends BaseTest{

    @Test
    public void uploadAndCheckExistence() {
        String id = uploadFile("test");
        Assert.assertThat("Can't get id of operation", id, not(isEmptyString()));
        boolean isFileUploadedAtTime = isFileUploaded(id);
        Assert.assertEquals("File wasn't upload in time of:" + config.TIMEOUT + " millis", true, isFileUploadedAtTime);
        isFilePresentOnDisk("test");
    }

    @Step("Ожидаем завершения загрузки файла")
    private boolean isFileUploaded(String id) {
        long wait = 0;
        while (config.TIMEOUT > wait) {
            try {
                String status = apiClient.operationStatus(id).getBody().getObject().getString("status");
                if(status.equals("success")) {
                    return true;
                }
                else {
                    TimeUnit.SECONDS.sleep(5);
                    wait += TimeUnit.SECONDS.toMillis(5);
                }
            } catch (InterruptedException | UnirestException e) {
                continue;
            }
        }
        return false;
    }

    @Step("Загружаем файл")
    private String uploadFile(String fileName) {
        String id = "";
        try {
            HttpResponse<JsonNode> response = apiClient.uploadResources(fileName, config.THIRDPARTY_RESOURCE);
            saveTextLog(response.getBody().toString());
            String href = response.getBody().getObject().getString("href");
            id = href.substring(href.lastIndexOf("/")+1);
        } catch (UnirestException e) {
            fail(e, "Failed when try to upload file");
        }
        return id;
    }

    @Step("Проверям, что файл появился в нашем хранилище")
    public void isFilePresentOnDisk(String fileName) {
        try {
            HttpResponse<JsonNode> response = apiClient.getResources(fileName);
            JsonNode body = response.getBody();
            saveTextLog(body.toString());
            body.getObject().get("error");
            Assert.fail("Response contains \"error\" key");
        }catch (JSONException ex) {
            Assert.assertEquals(ex.getMessage(), "JSONObject[\"error\"] not found.");
        } catch (UnirestException e) {
            fail(e, "Failed when try to check file on disk");
        }
    }


}

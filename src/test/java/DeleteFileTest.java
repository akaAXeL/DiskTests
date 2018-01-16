import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.qameta.allure.Step;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class DeleteFileTest extends BaseTest{

    private String fileName = "test_delete";
    private boolean pendingFlag = false;

    @Before
    public void uploadFile(){
        try {
            apiClient.uploadResources(fileName, config.THIRDPARTY_RESOURCE);
            long wait = 0;
            while (config.TIMEOUT > wait) {
                try {
                    apiClient.getResources(fileName).getBody().getObject().getString("error");
                } catch (JSONException e) {
                    pendingFlag = false;
                    return;
                }
                wait += TimeUnit.SECONDS.toMillis(5);
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (UnirestException | InterruptedException e) {
            pendingFlag = true;
        }
    }


    @Test
    public void deleteAndCheckPurge() {
        if(pendingFlag) {
            throw new UnsupportedOperationException("Can't run test due to test data");
        }

        deleteFile(fileName);
        isFileNotPresentOnDisk(fileName);
    }

    @Step("Удаляем файл")
    private void deleteFile(String fileName) {
        try {
            HttpResponse<JsonNode> response = apiClient.deleteResources(fileName);
        } catch (UnirestException e) {
            fail(e, "Failed when try to delete file");
        }
    }

    @Step("Проверям, что файл удалился из нашего хранилища")
    private void isFileNotPresentOnDisk(String fileName) {
        try {
            HttpResponse<JsonNode> response = apiClient.getResources(fileName);
            JsonNode body = response.getBody();
            saveTextLog(body.toString());
            Assert.assertEquals("DiskNotFoundError", body.getObject().get("error"));
        }catch (JSONException ex) {
            fail(ex, "File wasn't deleted");
        } catch (UnirestException e) {
            fail(e, "Failed when try to check file on disk");
        }
    }


}
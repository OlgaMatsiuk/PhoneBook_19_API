package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import okhttp3.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DeleteContactById {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA1MywiaWF0IjoxNjkwMjIwMDUzfQ.4Q_m_3YjCYLT6vySB58oMK6-GT2EVCvTs9EyEdKB0wQ";

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    String id;

    @BeforeMethod
    public void precondition() throws IOException {
        int i = new Random().nextInt(1000)+1000;
        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA19")
                .lastName("Automation")
                .email("qa19"+i+"@gmail.com")
                .phone("123454678")
                .description("Student")
                .build();
        RequestBody requestBody = RequestBody.create(gson.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().string(), ContactResponseDTO.class);
        String message = contactResponseDTO.getMessage();
        System.out.println(message);
        id = message.substring(message.lastIndexOf(" ")+1);
        System.out.println(id);
    }
    @Test
    public void deleteContactByIdPositive() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/"+id)
                .addHeader("Authorization",token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();
        ContactResponseDTO contactResponseDTO = gson.fromJson(response.body().toString(), ContactResponseDTO.class);
        String message = contactResponseDTO.getMessage();
        System.out.println(message);



    }
}

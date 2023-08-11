package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class AddNewContacts {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA1MywiaWF0IjoxNjkwMjIwMDUzfQ.4Q_m_3YjCYLT6vySB58oMK6-GT2EVCvTs9EyEdKB0wQ";

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";

    }
    @Test
    public void addNewContactPositive(){
        int i = new Random().nextInt(1000)+1000;

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA19")
                .lastName("Automation")
                .email("qa19"+i+"@gmail.com")
                .phone("123454678")
                .description("Student")
                .build();

        given()
                .header("Authorization",token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contacts was added!"));

    }
}

package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class PatientApiClient {

    private RequestSpecification baseSpec() {
        String credentials = ConfigReader.username() + ":" + ConfigReader.password();
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.baseApiUrl())
                .addHeader("Authorization", basicAuth)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public Response createPatient(String requestBody) {
        return given().spec(baseSpec())
                .body(requestBody)
                .when()
                .post("/patient");
    }

    public Response getPatient(String uuid) {
        return given().spec(baseSpec())
                .queryParam("v", "full")
                .when()
                .get("/patient/" + uuid);
    }

    public Response searchPatient(String query) {
        return given().spec(baseSpec())
                .queryParam("q", query)
                .when()
                .get("/patient");
    }
}

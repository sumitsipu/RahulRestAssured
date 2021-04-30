package test;

import io.restassured.RestAssured;
import resources.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DemoRest {

    public static void main(String[] args) {
        System.out.println("Hello");

        Payload payload = new Payload();
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //add place
        String response = given()
//                .log()
//                .all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.getBodyPayload())
        .when()
                .post("maps/api/place/add/json")
        .then()
//                .log()
//                .body()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath jsonResponse = ReuseMethods.stringToJson(response);
        String place_id = jsonResponse.getString("place_id");
        System.out.println(place_id);

        //edit address
        String newAdd = "Andmann";
        String editResponse = given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+newAdd+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
        .when()
                .put("maps/api/place/update/json")
        .then()
//                .log()
//                .body()
                .assertThat()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .extract().response().asString();

        //getPlace
        String getResponse = given()
                    .queryParam("key", "qaclick123")
                    .queryParam("place_id", place_id)
                    .header("Content-Type", "application/json")
                .when()
                    .get("maps/api/place/get/json")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response()
                    .asString();

        JsonPath js1 = ReuseMethods.stringToJson(getResponse);
        String getAddress = js1.getString("address");

        Assert.assertEquals(newAdd, getAddress);

        System.out.println(response);
        System.out.println(editResponse);
        System.out.println(getResponse);
        System.out.println("done");


    }

}

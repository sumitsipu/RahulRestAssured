package test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.GetPlace;
import pojo.LocationAddPlace;
import resources.Payload;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PojoUsingRahulMaps {

    @Test
    public void addPlaceRahul() {

        //create serialize pojo

        AddPlace addPlace = new AddPlace();

        LocationAddPlace locationAddPlace = new LocationAddPlace();
        locationAddPlace.setLat(-32.383494);
        locationAddPlace.setLng(32.427362);

        addPlace.setLocation(locationAddPlace);
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setAddress("29, side layout, cohen 09");

        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");

        addPlace.setTypes(types);

        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage("French-IN");


        //Payload payload = new Payload();
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().body()
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(addPlace)
                .when()
                    .post("maps/api/place/add/json")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .body("scope", equalTo("APP"))
                    .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath jsonResponse = ReuseMethods.stringToJson(response);
        String place_id = jsonResponse.getString("place_id");
        System.out.println(place_id);

    }

    @Test
    public void getPlace() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        GetPlace getPlaceResponse = given()
                    .queryParam("key", "qaclick123")
                    .queryParam("place_id", addPlace())
                    .header("Content-Type", "application/json")
                    .expect().defaultParser(Parser.JSON) //pojo class specific
                .when()
                    .get("maps/api/place/get/json")
                .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response()
                    .as(GetPlace.class); //pojo class specific //deserialization

//        JsonPath js1 = ReuseMethods.stringToJson(getResponse);
        System.out.println(getPlaceResponse.getAccuracy());
        System.out.println(getPlaceResponse.getAddress());
        System.out.println(getPlaceResponse.getLocation().getLatitude());
        System.out.println((getPlaceResponse.getLocation().getLongitude()));

    }

    //just to call from getPlace  to get place_id
    public String addPlace() {

        Payload payload = new Payload();
        RestAssured.baseURI = "https://rahulshettyacademy.com";

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

        return place_id;

    }

}

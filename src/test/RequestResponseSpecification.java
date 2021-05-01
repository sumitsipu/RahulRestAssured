package test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.GetPlace;
import pojo.LocationAddPlace;
import resources.Payload;
import utility.RequestSpecBuilderClass;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestResponseSpecification {

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


        RequestSpecification reqSpecification = given().log().body()
                .spec(new RequestSpecBuilderClass().getRequestSpecification())
                .body(addPlace);

        Response response = reqSpecification.when()
                .post("maps/api/place/add/json")
                .then().spec(new RequestSpecBuilderClass().getResponseSpecification())
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response();
        String responseString = response.asString();

        JsonPath jsonResponse = ReuseMethods.stringToJson(responseString);
        String place_id = jsonResponse.getString("place_id");
        System.out.println(place_id);

    }

        @Test
        public void getPlace () {

            System.out.println("new chnage for firdt person(guy) git");
            System.out.println("Change frpm second guy");
            System.out.println("again first guy changed");
            System.out.println("devlop branch first change by firs guy ");
            System.out.println("develop branch push y real first guy");
            GetPlace getPlaceResponse = given()
                    .spec(new RequestSpecBuilderClass().getRequestSpecification())
                    .queryParam("place_id", addPlace())
                .when()
                    .get("maps/api/place/get/json")
                .then()
                    .spec(new RequestSpecBuilderClass().getResponseSpecification())
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
        public String addPlace () {

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

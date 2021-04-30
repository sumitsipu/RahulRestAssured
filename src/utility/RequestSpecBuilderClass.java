package utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecBuilderClass {

    public RequestSpecification getRequestSpecification() {

        String baseURI = "https://rahulshettyacademy.com";
        //can e used for all types of requests where all these things are common
        RequestSpecification reqSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        return reqSpec;
    }

    public ResponseSpecification getResponseSpecification(){

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        return resSpec;

    }

}

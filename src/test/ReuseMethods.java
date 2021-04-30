package test;

import io.restassured.path.json.JsonPath;

import javax.xml.bind.util.JAXBSource;

public class ReuseMethods {

    public static JsonPath stringToJson(String toBeConverted){

        return new JsonPath(toBeConverted);

    }

}

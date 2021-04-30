//video 32
package test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resources.Payload;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "getCreateBookData")
    public void addBook(String isbn, String aisle) {

        Payload payload = new Payload();
        RestAssured.baseURI = "http://216.10.245.166";

        String postResponse = given()
                .header("Content-Type", "application/json")
                .body(payload.getBookPostPayload(aisle, isbn))
        .when()
                .post("Library/Addbook.php")
        .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        System.out.println(postResponse);
        JsonPath js = ReuseMethods.stringToJson(postResponse);
        String idAdded = js.getString("ID");
        System.out.println(idAdded);
        Assert.assertEquals(js.getString("ID"), isbn+aisle);

    }

    @Test(dataProvider = "getCreateBookData")
    public void deleteBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        Payload payload = new Payload();

        given()
                .header("Content-Type", "application/json")
                .body(payload.getDeleteBookPayload(isbn, aisle))
        .when()
                .post("Library/DeleteBook.php")
        .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

    }

    @DataProvider(name = "getCreateBookData")
    public Object[][] getBookData(){

        return new Object[][] {{"dasd", "2213"}, {"dgdd","6474"}, {"yiff", "76778"}};

    }

}

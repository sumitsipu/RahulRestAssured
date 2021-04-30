//Video 23
package test;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import resources.Payload;

import java.sql.SQLOutput;

public class HandleJson {

    public static void main(String[] args) {

        System.out.println("HandleJson");
        Payload payload = new Payload();

        JsonPath jsonPath = ReuseMethods.stringToJson(payload.getDummyPayload());
        int courseCount = jsonPath.getInt("courses.size()");
        System.out.println(courseCount);
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        String fcTitle = jsonPath.getString("courses[0].title");
        System.out.println(fcTitle);

        //title and price of every course
        for(int i=0; i<courseCount; ++i){
            System.out.println(jsonPath.getString("courses["+ i +"].title"));
            System.out.println(jsonPath.getInt("courses["+ i +"].price"));
        }

        //No of copies sold by RPA course
        for(int i=0; i<courseCount; ++i){
            if(jsonPath.getString("courses["+i+"].title").equalsIgnoreCase("RPA")){
                System.out.println("RPA copies soldddd: " + jsonPath.getString("courses["+i+"].copies"));
                break;
            }
        }

    }

    @Test
    public void totalValidationPrice(){
        //verify total courses price matches with total price
        System.out.println("inside @Test");
        Payload payload = new Payload();

        JsonPath jsonPath = ReuseMethods.stringToJson(payload.getDummyPayload());
        int courseCount = jsonPath.getInt("courses.size()");

        int sumPrice = 0;
        for(int i=0; i<courseCount; ++i){
            sumPrice += jsonPath.getInt("courses[" + i + "].price") * jsonPath.getInt("courses[" + i + "].copies");
        }
        Assert.assertEquals(jsonPath.getInt("dashboard.purchaseAmount"), sumPrice);
    }

}

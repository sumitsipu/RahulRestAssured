package resources;

public class Payload {

    public String getBodyPayload() {

        String bodyPayload = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -32.383494,\n" +
                "    \"lng\": 32.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";

        return bodyPayload;
    }

    public String getEditPlacePayload(){
        String editPlacePayload = "{\n" +
                "\"place_id\":\"43213d5377b0adfcd48f813b616a217e\",\n" +
                "\"address\":\"70 Summer walk\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";

        return editPlacePayload;
    }

    public String getDummyPayload(){
        String dummyPayload = "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";

        return dummyPayload;
    }

    public String getBookPostPayload(String aisle, String isbn){
        String bookPostPayload = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";
        return bookPostPayload;
    }

    public String getDeleteBookPayload(String isbn, String aisle){

        String deleteBookPayload = "{\n" +
                "\"ID\" :" + isbn + aisle + "\n" +
                "} ";

        return deleteBookPayload;

    }

}

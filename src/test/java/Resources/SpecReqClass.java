package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SpecReqClass {
    public static RequestSpecification reqSpecs(){
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/maps/api/")
                .setContentType(ContentType.JSON)
                .build();
        return requestSpecification;
    }
}

package Resources;

import io.restassured.RestAssured;
import pojo.GooglePlaceAPI.GoogleAddPlaceAPIRequest;
import pojo.GooglePlaceAPI.Location;

import java.io.IOException;
import java.util.ArrayList;

public class GetTestData extends Utils{
    public GoogleAddPlaceAPIRequest addPlacePayload(){
        try {
            RestAssured.baseURI = getProp().getProperty("baseUrl");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GoogleAddPlaceAPIRequest googleAddPlaceAPIRequest = new GoogleAddPlaceAPIRequest();
        Location location = new Location();
        location.setLat(41.878932334);
        location.setLng(-87.66676744998608);
        googleAddPlaceAPIRequest.setLocation(location);
        googleAddPlaceAPIRequest.setAccuracy(20);
        googleAddPlaceAPIRequest.setName("Shoes Shop");
        googleAddPlaceAPIRequest.setPhone_number("+1 5058188687");
        googleAddPlaceAPIRequest.setAddress("Ashland & Adams, Chicago, IL 60612, United States");
        googleAddPlaceAPIRequest.setLanguage("Eng-us");
        ArrayList<String> types = new ArrayList<>();
        types.add("asdnka");
        types.add("iqiwp");
        googleAddPlaceAPIRequest.setWebsite("Google.com");
        googleAddPlaceAPIRequest.setTypes(types);
        googleAddPlaceAPIRequest.setLanguage("En-Ind");
        return googleAddPlaceAPIRequest;
    }
}

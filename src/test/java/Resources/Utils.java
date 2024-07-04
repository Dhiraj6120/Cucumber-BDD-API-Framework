package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static int ss = 1;
    public RequestSpecification reqSpecs(){

        PrintStream logReq;
        
        try {
            logReq = new PrintStream(new FileOutputStream("logging_" + ss +".txt"));
            ss++;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/maps/api/")
                .setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(logReq))
                .addFilter(ResponseLoggingFilter.logResponseTo(logReq))
                .build();
        return requestSpecification;
    }
    public Properties getProp() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                ("/Users/dhiraj/Documents/Study/Cucumber BDD API Framework/src/test/java/DataSet/Data.properties");
        prop.load(fis);

        return prop;
    }

}

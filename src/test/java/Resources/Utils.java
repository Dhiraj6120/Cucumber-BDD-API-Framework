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
    private RequestSpecification res;
    PrintStream logReq;
    public RequestSpecification reqSpecs(String name){

        try {
            logReq = new PrintStream(new FileOutputStream("src/test/java/Results/" + name +".txt"));
            ss++;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/maps/api/")
                .setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(logReq))
                .addFilter(ResponseLoggingFilter.logResponseTo(logReq))
                .build();
    }
    public Properties getProp() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                ("/Users/dhiraj/Documents/Study/Cucumber BDD API Framework/src/test/java/DataSet/Data.properties");
        prop.load(fis);

        return prop;
    }

}

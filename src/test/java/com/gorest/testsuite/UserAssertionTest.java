package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200);
        // response.log().all();// to print all data on the console

    }

    //1. Verify the if the total record is 10
    @Test
    public void test001() {
        response.body("size", equalTo(10));
    }

    //2. Verify the if the name of id = 2329080 is equal to ”Anshula Panicker PhD”
    @Test
    public void test002() {
        response
                .body("[2]", hasEntry("id", 2329080))
                .body("[2]", hasEntry("name", "Anshula Panicker PhD"));

    }

    //3. Check the single ‘Name’ in the Array list (Kirti Pothuvaal,)
    @Test
    public void test003() {
        response.body("name", hasItem("Kirti Pothuvaal"));

    }

    //4. Check the multiple ‘Names’ in the ArrayList (Naveen Chopra, Vishwamitra Abbott, Abhaidev Johar)
    @Test
    public void test004() {

        response.body("name", hasItems("Naveen Chopra","Vishwamitra Abbott","Abhaidev Johar"));
    }

    //5. Verify the emai of userid = 2329077 is equal “keerti_chaturvedi@wisozk.example”
    @Test
    public void test005() {
        response
                .body("[4]", hasEntry("id", 2329077))
                .body("[4]", hasEntry("email", "keerti_chaturvedi@wisozk.example"));
    }

    //6. Verify the status is “inactive” of user name is “Deeptanshu Reddy DDS”
    @Test
    public void test006() {
        response
                .body("[0]", hasEntry("name", "Deeptanshu Reddy DDS"))
                .body("[0]", hasEntry("status", "inactive"));
    }

    //7. Verify the Gender = male of user name is “Vishwamitra Chopra”
    @Test
    public void test007() {
        response
                .body("[0]", hasEntry("name", "Vishwamitra Chopra"))
                .body("[0]", hasEntry("gender", "male"));
    }
}

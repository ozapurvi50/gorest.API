package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(25));
    }

    //2. Verify the if the title of id = 39304 is equal to ”Qui sub magnam acquiro carcer.”
    @Test
    public void test002() {
        response
                .body("[1]", hasEntry("title", "Vox admiratio turpis complectus adversus socius."))
                .body("[1]", hasEntry("id", 40104));
    }

    //3. Check the single user_id in the Array list (2272684)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2329082));
    }

    //4. Check the multiple ids in the ArrayList (2250466,2250463,2272670)
    @Test
    public void test004() {
        response.body("user_id", hasItems(2329073, 2329071, 2329056));
    }

    //5. Verify the body of userid = 2272684 is equal “Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."”
    @Test
    public void test005() {
        response.body("[0].body", equalTo("Cohaero at cavus. Administratio callide voluptates. Blandior conservo virga. Tunc aer vorax. Commodi vomer aeger. Clam numquam texo. Viridis aggredior anser. Utilis sollicito communis. Armarium communis considero. Vulgo asper unde. Apparatus aegre cilicium. Vulgus dapifer bardus. Supra tego demens."));
    }


}

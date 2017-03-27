package com.github.romankhachko.articleCodeSamples.earlyTesting.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by roman on 3/25/17.
 */
public class UserServiceTest {

    private static Response createUserResponse;
    private static final String URL = "http://my.service/";

    // Since all verifications in this test class applied to response,
    // it's ok to call the method once, and verify response from different aspects in proper tests.
    @BeforeClass
    public static void createUser() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName("random_user_name_should_be_here");
        userDetails.setUserRole("any_role_from_accepted_roles");

        RequestSpecification request = given().contentType("application/json").body(userDetails).log().all();
        createUserResponse = request.post(URL + "createUser");
    }

    /**
     * Verifying the creation of user
     */
    @Test
    public void userShouldBeCreated() {
        // Deserialization of returned response:
        UserDetails createdUser = createUserResponse.as(UserDetails.class);
        Long createdUserId = createdUser.getUserID();
        assertTrue(createdUserId > 0);
        String createdUserStatus = createdUser.getUserStatus();
        assertEquals("created", createdUserStatus);

        // Second part of verification: call getUser to verify that user is created
        // and another method can fetch it from the data storage:

        UserDetails fetchedUser = given().get(URL + "users/" + createdUserId).as(UserDetails.class);
        assertEquals(createdUserId, fetchedUser.getUserID());
        assertEquals(createdUserStatus, fetchedUser.getUserStatus());

    }

    /**
     * verifying the presence of specific header
     */
    @Test
    public void specificHeaderShouldBePresent() {
        assertNotNull(createUserResponse.getHeader("specific_header"));
        // other verifications of headers can be added:
    }

    /**
     * validating the schema according to specification
     */
    @Test
    public void createUserResponseShouldComplyToSchema() {
        String json = createUserResponse.getBody().prettyPrint();
        assertThat(json, matchesJsonSchemaInClasspath("userDetails-schema.json"));
    }
}

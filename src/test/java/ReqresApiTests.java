import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresApiTests extends TestBase {

    @DisplayName("Create a new user with name and job")
    @Test
    public void createUserSuccessTest() {
        String userData = "{\"name\":\"morpheus\",\"job\":\"leader\"}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }

    @DisplayName("Create a new user with no job")
    @Test
    public void createUserSuccessWithNoJobTest() {
        String userData = "{\"name\":\"morpheus\"}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }

    @DisplayName("Create a new user with no name")
    @Test
    public void createUserSuccessWithNoNameTest() {
        String userData = "{\"job\":\"leader\"}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is("leader"))
                .body("id", is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }

    @DisplayName("Update a user with put method")
    @Test
    public void updateUserSuccessWithPutTest() {
        String userData = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));
    }

    @DisplayName("Update a user with patch method")
    @Test
    public void updateUserWithPatchSuccessTest() {
        String userData = "{\"name\":\"morpheus\",\"job\":\"zion resident\"}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));
    }

    @DisplayName("Delete a user")
    @Test
    public void deleteUserSuccessTest() {
        given()
                .contentType(JSON)
                .log().method()
                .log().uri()
                .when()
                .delete("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}

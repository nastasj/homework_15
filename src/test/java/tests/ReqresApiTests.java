package tests;

import models.lombok.CreateUpdateUserRequestModel;
import models.lombok.CreateUserResponseModel;
import models.lombok.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.ReqresSpec.*;

public class ReqresApiTests extends TestBase {

    @DisplayName("Create a new user with name and job")
    @Test
    public void createUserSuccessTest() {
        CreateUpdateUserRequestModel userData = new CreateUpdateUserRequestModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        CreateUserResponseModel response = step("Make request to create a new user with name and job", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(statusCode201Spec)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("leader");
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
        });
    }

    @DisplayName("Create a new user with no job")
    @Test
    public void createUserWithNoJobSuccessTest() {
        CreateUpdateUserRequestModel userData = new CreateUpdateUserRequestModel();
        userData.setName("morpheus");

        CreateUserResponseModel response = step("Make request to create a new user with no job", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(statusCode201Spec)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
        });
    }

    @DisplayName("Create a new user with no name")
    @Test
    public void createUserWithNoNameSuccessTest() {
        CreateUpdateUserRequestModel userData = new CreateUpdateUserRequestModel();
        userData.setJob("leader");

        CreateUserResponseModel response = step("Make request to create a new user with no name", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(statusCode201Spec)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getJob()).isEqualTo("leader");
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
        });
    }

    @DisplayName("Update a user with put method")
    @Test
    public void updateUserWithPutSuccessTest() {
        CreateUpdateUserRequestModel userData = new CreateUpdateUserRequestModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UpdateUserResponseModel response = step("Make request to update a user with put method", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(statusCode200Spec)
                        .extract().as(UpdateUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
            assertThat(response.getUpdatedAt()).isNotNull();
        });
    }

    @DisplayName("Update a user with patch method")
    @Test
    public void updateUserWithPatchSuccessTest() {
        CreateUpdateUserRequestModel userData = new CreateUpdateUserRequestModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UpdateUserResponseModel response = step("Make request to update a user with patch method", () ->
                given(requestSpec)
                        .body(userData)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(statusCode200Spec)
                        .extract().as(UpdateUserResponseModel.class));

        step("Check response", () -> {
            assertThat(response.getName()).isEqualTo("morpheus");
            assertThat(response.getJob()).isEqualTo("zion resident");
            assertThat(response.getUpdatedAt()).isNotNull();
        });
    }

    @DisplayName("Delete a user")
    @Test
    public void deleteUserSuccessTest() {
        step("Make request to delete a user", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(statusCode204Spec)
        );
    }
}

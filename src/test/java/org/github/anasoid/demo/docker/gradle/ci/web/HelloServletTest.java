package org.github.anasoid.demo.docker.gradle.ci.web;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import org.github.anasoid.demo.docker.gradle.ci.ConfigTest;
import org.github.anasoid.demo.docker.gradle.ci.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class HelloServletTest {

  @Test(timeout = 10000)
  public void getTest() {
    Response response = given()
        .when().
            get(getRestUrlForPath("/hello")).
            then().log().all().
            statusCode(200).extract().response();

    Assert.assertTrue(response.body().prettyPrint().contains("fromdatabase"));
  }


  public String getRestUrlForPath(String path) {
    return ConfigTest.getRestUrl() + path;
  }

}

package com.te.bookmydoctor.resource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DoctorEndPoints {

	 public static Response registerDoctor(String payload)
	     {
	         RestAssured.baseURI=Routes.base_uri;
	         Response response=RestAssured.
	                 given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).
	                 when().post(Routes.post_uri_register_doctor);
	         return response;
	     }
}

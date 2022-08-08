package com.te.bookmydoctor.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.bookmydoctor.dto.AddressDto;
import com.te.bookmydoctor.dto.DoctorDto;
import com.te.bookmydoctor.dto.LanguageDto;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class DoctorResourceEndPointTest {

	DoctorDto doctorDto;
	ObjectMapper mapper;

	@BeforeTest
	public void beforeTest() {
		RestAssured.basePath = "api/v1/doctor";
		RestAssured.given().contentType("application/json").body(doctorDto).when().post();

		mapper = new ObjectMapper();
		List<AddressDto> addressDtos = new ArrayList<AddressDto>();
		addressDtos.add(
				new AddressDto("2nd Cross", "Kareesandra", "Banashankari 2nd Stage", 560070, "Karnataka", "India"));
		List<LanguageDto> languageDtos = new ArrayList<LanguageDto>();
		languageDtos.add(new LanguageDto("Kannada"));
		doctorDto = new DoctorDto("Shivaa", "Shivu", "shivu@gmail.com", 8880638598l, "shivu@123", "MALE", 5.0,
				addressDtos, languageDtos, "MBBS", "Kempegowda Insititution Of Medical And Science", 2017, "FRCS",
				"KIMS8861", "EYE and SKIN");
	}
		@Test
	 void testSaveDoctor() throws JsonProcessingException {
		Map<String, Object> bodyParams = new HashMap<String, Object>();
		bodyParams.put("firstName", doctorDto.getFirstName());
		bodyParams.put("lastName", doctorDto.getLastName());
		bodyParams.put("email", doctorDto.getEmail());
		bodyParams.put("password", doctorDto.getPassword());
		bodyParams.put("phone", doctorDto.getMobileNumber());
		bodyParams.put("isVerified", 0);
		
//		RestAssured.given()
		
		String payload = mapper.writeValueAsString(bodyParams);
		Response response = DoctorEndPoints.registerDoctor(payload);
		response.then().log().all();
	
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(response.getStatusLine().contains("OK"));
		
	}
}

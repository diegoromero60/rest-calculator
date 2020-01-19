package co.com.restcalculator.controller;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.RequestAddNumbers;
import co.com.restcalculator.domain.Status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class NumbersControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private NumbersController numbersController;

	@Test
	void whenAddNumberIsCallGivenValidRequestThenGetValidResponse() {
		assertDoesNotThrow(() -> {
			ResponseEntity<Status> responseCreation = restTemplate.getForEntity(
					new URL(String.format("http://localhost:%d%s", port, ConstantsServices.SERVICE_CREATE_SESSION_PATH))
							.toString(),
					Status.class);
			List<Float> numbersList = new ArrayList<>();
			numbersList.add(4f);
			numbersList.add(4f);
			RequestAddNumbers request = RequestAddNumbers.builder().numbersList(numbersList)
					.sessionCode(responseCreation.getBody().getMessage()).build();
			ResponseEntity<Status> response = restTemplate.postForEntity(
					new URL(String.format("http://localhost:%d%s", port, ConstantsServices.SERVICE_ADD_NUMBERS_PATH))
							.toString(),
					request, Status.class);
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.SUCCES_CODE, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

	@Test
	void whenAddNumberIsCallGivenInValidRequestThenGetErrorResponse() {
		assertDoesNotThrow(() -> {
			List<Float> numbersList = new ArrayList<>();
			numbersList.add(4f);
			numbersList.add(4f);
			RequestAddNumbers request = RequestAddNumbers.builder().numbersList(numbersList).sessionCode("fjsdkd")
					.build();
			ResponseEntity<Status> response = restTemplate.postForEntity(
					new URL(String.format("http://localhost:%d%s", port, ConstantsServices.SERVICE_ADD_NUMBERS_PATH))
							.toString(),
					request, Status.class);
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.ERROR_CODE_SESSION_NOT_FOUND, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

}

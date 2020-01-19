package co.com.restcalculator.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.Status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SessionManagerControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private SessionManagerController sessionManager;

	@Test
	void whenCreationServiceIsCallGivenValidRequestThenGetValidResponse() {
		assertDoesNotThrow(() -> {
			ResponseEntity<Status> response = restTemplate.getForEntity(
					new URL(String.format("http://localhost:%d%s", port, ConstantsServices.SERVICE_CREATE_SESSION_PATH))
							.toString(),
					Status.class);
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.SUCCES_CODE, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

	@Test
	void whenCreationServiceIsCallGivenErrorConnectionWithDBThenGetValidResponse() {
		assertDoesNotThrow(() -> {
			ReflectionTestUtils.setField(sessionManager, "sessionRepository", null);
			ResponseEntity<Status> response = sessionManager.createSession();
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.ERROR_CODE, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

}

package co.com.restcalculator.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.net.URL;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.Status;
import co.com.restcalculator.domain.session.Session;
import co.com.restcalculator.repositories.SessionRepository;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class SessionManagerControllerTest {

	@Mock
	private SessionManagerController sessionManager;

	@Mock
	private SessionRepository sessionRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(sessionManager, "sessionRepository", sessionRepository);
		when(sessionRepository.save(any())).thenReturn(Session.builder().id("TEST").creationDate(new Date()).build());
		when(sessionManager.createSession()).thenCallRealMethod();
	}

	@Test
	void whenCreationServiceIsCallGivenValidReqSessionManagerControlleruestThenGetValidResponse() {
		assertDoesNotThrow(() -> {
			ResponseEntity<Status> response = sessionManager.createSession();
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

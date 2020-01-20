package co.com.restcalculator.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.RequestAddNumbers;
import co.com.restcalculator.domain.Status;
import co.com.restcalculator.domain.session.Session;
import co.com.restcalculator.repositories.SessionRepository;
import co.com.restcalculator.controller.mock.NumberControllerMock;


@ExtendWith(SpringExtension.class)
class NumbersControllerTest {

	@Mock
	private NumberControllerMock numberControllerMock;

	@Mock
	private SessionRepository sessionRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(numberControllerMock, "sessionRepository", sessionRepository);
		when(sessionRepository.save(any())).thenReturn(Session.builder().id("TEST").creationDate(new Date()).build());
		when(sessionRepository.findById(anyString())).thenReturn(Optional.of(Session.builder().build()));
		when(numberControllerMock.addNumber(any(RequestAddNumbers.class))).thenCallRealMethod();
	}

	@Test
	void whenAddNumberIsCallGivenValidRequestThenGetValidResponse() {
		assertDoesNotThrow(() -> {
			List<Float> numbersList = new ArrayList<>();
			numbersList.add(4f);
			numbersList.add(4f);
			RequestAddNumbers request = RequestAddNumbers.builder().numbersList(numbersList)
					.sessionCode("4411").build();
			ResponseEntity<Status> response = numberControllerMock.addNumber(request);
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.SUCCES_CODE, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

	@Test
	void whenAddNumberIsCallGivenEmptyIdThenGetErrorResponse() {
		when(sessionRepository.findById(any())).thenReturn(Optional.empty());
		assertDoesNotThrow(() -> {
			List<Float> numbersList = new ArrayList<>();
			numbersList.add(4f);
			numbersList.add(4f);
			RequestAddNumbers request = RequestAddNumbers.builder().numbersList(numbersList)
					.sessionCode("4411").build();
			ResponseEntity<Status> response = numberControllerMock.addNumber(request);
			assertNotNull(response.getBody());
			assertEquals(ConstantsServices.ERROR_CODE_SESSION_NOT_FOUND, response.getBody().getCode());
			assertTrue(!response.getBody().getMessage().isEmpty());
		});
	}

}

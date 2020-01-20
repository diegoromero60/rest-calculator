package co.com.restcalculator.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.Operator;
import co.com.restcalculator.domain.RequestAddNumbers;
import co.com.restcalculator.domain.Status;
import co.com.restcalculator.domain.session.Session;
import co.com.restcalculator.repositories.SessionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class OperationsController {
	
	@Autowired
	SessionRepository sessionRepository;

	@RequestMapping(path = ConstantsServices.SERVICE_PROCESS_NUMBERS_PATH, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Status> process(@RequestParam(name = "operator", required = true) Operator operator,
			@RequestParam(name = "sessionCode", required = true) String sessionCode) {
		log.info("Going in of addNumber");
		Status statusResponse = Status.builder().code(ConstantsServices.ERROR_CODE_SESSION_NOT_FOUND)
				.message(ConstantsServices.ERROR_MESSAGE_SESSION_NOT_FOUND).build();
		try {
			Optional<Session> session = sessionRepository.findById(sessionCode);
			if (session.isPresent()) {
				if (session.get().getNumbersList() == null || session.get().getNumbersList().isEmpty() ||
						session.get().getNumbersList().size()<2) {
					statusResponse.setMessage(ConstantsServices.ERROR_MESSAGE_LIST_NUMBERS_EMPTY);
				}
				else {
					statusResponse = processList(session.get().getNumbersList(), operator);
				}
			}
		} catch (Exception e) {
			log.error(ConstantsServices.ERROR_MESSAGE, e);
		} finally {
			log.info("Going out of addNumber");
		}
		return new ResponseEntity<Status>(statusResponse, HttpStatus.OK);

	}

	private Status processList(List<Float> numbersList, Operator operator) {
		BigDecimal answer = BigDecimal.valueOf(numbersList.get(0).doubleValue());
		try {
			for(int i=1; i<numbersList.size(); i++) {
				answer = operator.doIt(answer, BigDecimal.valueOf(numbersList.get(i)));
			}
		}
		catch (Exception e) {
			return Status.builder().code(ConstantsServices.ERROR_CODE).message(ConstantsServices.ERROR_MESSAGE_PROCESSING_NUMBERS).build();
		}
		return Status.builder().code(ConstantsServices.SUCCES_CODE).message(answer.toPlainString()).build();
	}
}

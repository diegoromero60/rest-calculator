package co.com.restcalculator.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.RequestAddNumbers;
import co.com.restcalculator.domain.Status;
import co.com.restcalculator.domain.session.Session;
import co.com.restcalculator.repositories.SessionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NumbersController {
	@Autowired
	SessionRepository sessionRepository;

	@RequestMapping(path = ConstantsServices.SERVICE_ADD_NUMBERS_PATH, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Status> addNumber(@Valid @RequestBody RequestAddNumbers requestAddNumbers) {
		log.info("Going in of addNumber");
		try {
			Optional<Session> session = sessionRepository.findById(requestAddNumbers.getSessionCode());
			if (session.isPresent()) {
				if (session.get().getNumbersList() == null)
					session.get().setNumbersList(new ArrayList<>());
				for (Float value : requestAddNumbers.getNumbersList()) {
					session.get().getNumbersList().add(value);
				}
				sessionRepository.save(session.get());
				return new ResponseEntity<Status>(Status.builder().code(ConstantsServices.SUCCES_CODE)
						.message(ConstantsServices.SUCCESS_MESSAGE_ADD_NUMBER).build(), HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(ConstantsServices.ERROR_MESSAGE, e);
		} finally {
			log.info("Going out of addNumber");
		}
		return new ResponseEntity<Status>(Status.builder().code(ConstantsServices.ERROR_CODE_SESSION_NOT_FOUND)
				.message(ConstantsServices.ERROR_MESSAGE_SESSION_NOT_FOUND).build(), HttpStatus.OK);

	}
}

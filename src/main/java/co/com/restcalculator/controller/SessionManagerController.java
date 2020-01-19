package co.com.restcalculator.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.restcalculator.configurations.ConstantsServices;
import co.com.restcalculator.domain.Status;
import co.com.restcalculator.domain.session.Session;
import co.com.restcalculator.repositories.SessionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SessionManagerController {

	@Autowired
	SessionRepository sessionRepository;

	@RequestMapping(path = ConstantsServices.SERVICE_CREATE_SESSION_PATH, method = RequestMethod.GET)
	public ResponseEntity<Status> createSession() {
		try {
		Session newObject = sessionRepository.save(Session.builder().creationDate(new Date()).build());
		return new ResponseEntity<Status>(Status.builder().code(ConstantsServices.SUCCES_CODE).message(newObject.getId()).build(), HttpStatus.OK);
		}
		catch (Exception e) {
			log.error(ConstantsServices.ERROR_MESSAGE, e);
		}
		return new ResponseEntity<Status>(Status.builder().code(ConstantsServices.ERROR_CODE).message(ConstantsServices.ERROR_MESSAGE).build(), HttpStatus.OK);
		
	}

	@RequestMapping(path = "/findAllSessions", method = RequestMethod.GET)
	public ResponseEntity<List<Session>> findAll() {
		return new ResponseEntity<List<Session>>(sessionRepository.findAll(), HttpStatus.OK);
	}
}

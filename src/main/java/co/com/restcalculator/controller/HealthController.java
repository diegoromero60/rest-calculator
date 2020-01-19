package co.com.restcalculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.restcalculator.domain.Status;

@RestController
@RequestMapping(path = "/health")
public class HealthController {
	
	@GetMapping(path = "/status")
	public ResponseEntity<Status> getStatus() {
		return new ResponseEntity<Status>( Status.builder().code(0).message("Server Ok").build(), HttpStatus.OK);
	}
}

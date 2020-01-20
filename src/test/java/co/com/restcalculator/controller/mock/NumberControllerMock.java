package co.com.restcalculator.controller.mock;

import org.springframework.http.ResponseEntity;

import co.com.restcalculator.controller.NumbersController;
import co.com.restcalculator.domain.RequestAddNumbers;
import co.com.restcalculator.domain.Status;

public class NumberControllerMock extends NumbersController{

	@Override
	public ResponseEntity<Status> addNumber(RequestAddNumbers requestAddNumbers) {
		return super.addNumber(requestAddNumbers);
	}
}

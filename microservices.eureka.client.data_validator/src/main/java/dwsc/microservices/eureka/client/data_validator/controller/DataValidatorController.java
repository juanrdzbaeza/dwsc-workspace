package dwsc.microservices.eureka.client.data_validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dwsc.microservices.eureka.client.data_validator.service.DataValidatorService;

@RestController
public class DataValidatorController {

	@Autowired
	private DataValidatorService dataValidatorService;
	
	// Mapping the path in the microservice to validate a data given
	@RequestMapping(value = "/{data}", method = RequestMethod.GET)
	public  ResponseEntity<Boolean> validateData(@PathVariable("data") String data) {
		boolean validator = dataValidatorService.validateData(data);
		if (validator) {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

}

package dwsc.microservices.eureka.client.data_validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody boolean addFollower(@PathVariable("data") String data) {
		return dataValidatorService.validateData(data);
	}

}

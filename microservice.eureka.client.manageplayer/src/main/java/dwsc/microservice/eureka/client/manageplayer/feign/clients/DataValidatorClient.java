package dwsc.microservice.eureka.client.manageplayer.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("SAMPLE-CLIENT-VALIDATOR")
public interface DataValidatorClient {
	@RequestMapping(value = "/{data}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> validateData(@PathVariable("data") String data); 

}

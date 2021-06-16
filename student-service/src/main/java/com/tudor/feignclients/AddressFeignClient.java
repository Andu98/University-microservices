package com.tudor.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tudor.response.AddressResponse;

//valoarea din campul "value" trebuie sa corespunda cu numele aplicatiei
@FeignClient( value = "address-service",
			  path = "/api/address")
public interface AddressFeignClient {
	
	@GetMapping("/getById/{id}")
	public AddressResponse getById(@PathVariable long id) ; //nu necesita implementare in aplicatia student-service, deoarece este impl in address-service

}

package com.tudor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tudor.feignclients.AddressFeignClient;
import com.tudor.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	Logger logger = LoggerFactory.getLogger(CommonService.class);
	long count = 1;

	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressbyId")
	public AddressResponse getAddressById(long addressId) {
		logger.info("count {}", count++);

		AddressResponse response = addressFeignClient.getById(addressId);
		return response;
	}

	public AddressResponse fallbackGetAddressbyId(long addressId, Throwable th) {
		logger.info("Error {}", th);
		return new AddressResponse();
	}
}

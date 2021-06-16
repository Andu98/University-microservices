package com.tudor.service;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tudor.controller.AddressController;
import com.tudor.entity.Address;
import com.tudor.repository.AddressRepository;
import com.tudor.request.CreateAddressRequest;
import com.tudor.response.AddressResponse;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AddressService {
//clasa contine 2 api-uri. unul pt crearea adresei si alta pentru get
	org.slf4j.Logger logger = LoggerFactory.getLogger(AddressService.class) ;
	
	@Autowired
	AddressRepository addressRepository;
	
	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		
		Address address = new Address();
		address.setStreet(createAddressRequest.getStreet());
		address.setCity(createAddressRequest.getCity());
		
		addressRepository.save(address);
		return new AddressResponse(address);
	}
	
	/*
	public AddressResponse getById(long id) {
		logger.info("S-a executat metoda getById"+id);
		
		Address address = addressRepository.findById(id).get();
		return new AddressResponse(address);
	}
	*/
	
	public ResponseEntity<Address> getById(long id){
		logger.info("S-a executat metoda getById"+id);
		Address address = addressRepository.findById(id).get();
		Link addressObject = linkTo(methodOn(AddressController.class).getById(address.getId())).withSelfRel();
		address.add(addressObject);
		return new ResponseEntity<Address>(address,HttpStatus.OK);
		
	}
	
}

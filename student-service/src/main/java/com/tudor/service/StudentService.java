package com.tudor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.tudor.controller.StudentController;
import com.tudor.entity.Student;
import com.tudor.feignclients.AddressFeignClient;
import com.tudor.repository.StudentRepository;
import com.tudor.request.CreateStudentRequest;
import com.tudor.response.AddressResponse;
import com.tudor.response.StudentResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	WebClient webClient;
	
	@Autowired
	CommonService commonService;
	
	Logger logger = LoggerFactory.getLogger(CommonService.class);

	//prin intermediul variabilei vom face apelul get la address-service
	@Autowired
	AddressFeignClient addressFeignClient; 
	
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);
		
		//dupa crearea studentului, returnam detaliile acestuia alaturi de detaliile adresei
		StudentResponse studentResponse = new StudentResponse(student);
	
		//ne folosim de clasa addressFeignClient pentru a apela metoda getById
		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
				
		return studentResponse;
	}
	
	
	
	public ResponseEntity<StudentResponse> getById (long id) {
		
		logger.info("Apelat din metoda getById");
		
		Student student = studentRepository.findById(id).get();
		
		Link studentObject = linkTo(methodOn(StudentController.class).getById(student.getId())).withSelfRel();
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
		studentResponse.add(studentObject);
								
		return new ResponseEntity<StudentResponse>(studentResponse,HttpStatus.OK);
	}
	
	
	//circuit breaker este implementat pe aceasta metoda
	@CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById" )
	public AddressResponse getAddressById (long addressId) {
		AddressResponse addressResponse = addressFeignClient.getById(addressId);
		return addressResponse;
	}
	
	public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {
		return new AddressResponse();
	}
	}

	
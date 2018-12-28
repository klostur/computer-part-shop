package vp.spring.rcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.Brand;
import vp.spring.rcs.model.ComputerPart;
import vp.spring.rcs.service.BrandService;
import vp.spring.rcs.service.ComputerPartService;

@RestController
@RequestMapping(value = "/api/computer-parts")
public class ComputerPartController {
	
	@Autowired
	ComputerPartService computerPartService;
	@Autowired
	BrandService brandService;
//	@GetMapping
//	public ResponseEntity<List<ComputerPart>> findAll() {
//		List<ComputerPart> retVal = computerPartService.findAll();
//		return new ResponseEntity<List<ComputerPart>>(retVal, HttpStatus.OK);
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ComputerPart>> findAllPage(Pageable page) {
		Page<ComputerPart> cp = computerPartService.findAll(page);
		List<ComputerPart> retVal = cp.getContent();
		HttpHeaders headers = new HttpHeaders();
		int componentNumber = cp.getTotalPages();
		headers.add("X-Total-Pages", String.valueOf(componentNumber));
		return new ResponseEntity<> (retVal, headers, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id){
		computerPartService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<ComputerPart> save(@RequestBody ComputerPart computerPart){
		Brand brand = brandService.findByName(computerPart.getBrand().getName());
		computerPart.setBrand(brand);
		ComputerPart retVal = computerPartService.save(computerPart);
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<ComputerPart> update(@PathVariable Long id, @RequestBody ComputerPart computerPart){
		ComputerPart cp = computerPartService.findOne(id);
		cp.setName(computerPart.getName());
		cp.setPrice(computerPart.getPrice());
		cp.setBrand(brandService.findByName(computerPart.getBrand().getName()));
		cp = computerPartService.save(cp);
		return new ResponseEntity<>(cp, HttpStatus.OK);
	}

}

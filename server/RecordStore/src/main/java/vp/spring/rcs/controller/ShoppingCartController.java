package vp.spring.rcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vp.spring.rcs.model.ComputerPart;
import vp.spring.rcs.model.ShoppingCart;
import vp.spring.rcs.service.ComputerPartService;
import vp.spring.rcs.service.ShoppingCartService;

@RestController
@RequestMapping(value = "api/shop")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	ComputerPartService computerPartService;
	
	@GetMapping
	public ResponseEntity<List<ShoppingCart>> findAll() {
		List<ShoppingCart> retVal = shoppingCartService.findAll();
		return new ResponseEntity<List<ShoppingCart>>(retVal, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<ShoppingCart> save(@RequestBody ComputerPart cpId) {
		ShoppingCart sh = new ShoppingCart(computerPartService.findOne(cpId.getId()));
		ShoppingCart retVal = shoppingCartService.save(sh);
		return new ResponseEntity<ShoppingCart>(retVal, HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id){
		shoppingCartService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}

package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.ComputerPartRepository;
import vp.spring.rcs.data.ShoppingCartRepository;
import vp.spring.rcs.model.ShoppingCart;

@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	ComputerPartRepository repo;
	
	public List<ShoppingCart> findAll(){
		return shoppingCartRepository.findAll();
	}

	public ShoppingCart save(ShoppingCart shoppingCart) {
		return shoppingCartRepository.save(shoppingCart);
	}

	public void remove(Long id) {
		shoppingCartRepository.delete(id);
	}

}

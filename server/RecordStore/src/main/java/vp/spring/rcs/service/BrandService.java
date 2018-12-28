package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vp.spring.rcs.data.BrandRepository;
import vp.spring.rcs.model.Brand;

@Service
public class BrandService {
	@Autowired
	BrandRepository brandRepository;

	public List<Brand> findAll() {
		return brandRepository.findAll();
	}
	public Brand save(Brand brand) {
		return brandRepository.save(brand);
	}
	public Brand findByName(String string) {
		return brandRepository.findByName(string); 
	}
}

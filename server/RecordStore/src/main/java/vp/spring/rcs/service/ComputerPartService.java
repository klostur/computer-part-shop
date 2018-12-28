package vp.spring.rcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import vp.spring.rcs.data.ComputerPartRepository;
import vp.spring.rcs.model.ComputerPart;

@Component
public class ComputerPartService {

	@Autowired
	ComputerPartRepository repo;

	public ComputerPart findOne(Long id) {
		return repo.findOne(id);
	}

	public List<ComputerPart> findAll() {
		return repo.findAll();
	}

	public ComputerPart save(ComputerPart computerPart) {
		return repo.save(computerPart);
	}

	public void remove(Long id) {
		repo.delete(id);
	}

	public Page<ComputerPart> findAll(Pageable page) {
		return repo.findAll(page);
	}

	
}

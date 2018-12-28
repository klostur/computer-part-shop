package vp.spring.rcs.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ComputerPart {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Brand brand;
	
	private String name;
	
	private double price;
	
	
	
	public ComputerPart(Brand brand, String name, double price) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
	}


	public ComputerPart() {
	}
	
	
	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
	
}

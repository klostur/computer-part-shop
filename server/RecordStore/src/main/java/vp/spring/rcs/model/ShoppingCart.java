package vp.spring.rcs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private ComputerPart computerPart;

	public ShoppingCart(Long id, ComputerPart computerPart) {
		super();
		this.id = id;
		this.computerPart = computerPart;
	}

	public ShoppingCart(ComputerPart computerPart) {
		super();
		this.computerPart = computerPart;
	}


	public ShoppingCart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ComputerPart getComputerPart() {
		return computerPart;
	}

	public void setComputerPart(ComputerPart computerPart) {
		this.computerPart = computerPart;
	}
	
	
}

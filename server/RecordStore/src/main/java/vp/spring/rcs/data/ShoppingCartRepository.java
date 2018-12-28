package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vp.spring.rcs.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{
	
}

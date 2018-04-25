package es.urjc.code.daw.library.product;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository <Buyer, Long> {
	
	List <Buyer> findByid (Long id);

}


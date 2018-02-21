package es.urjc.code.daw.tablonanuncios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ValorationRepository extends JpaRepository <Valoration, Long> {
	//List <User> findBySeller (String seller);
	//List <User> findByBuyer (Long buyer);

}
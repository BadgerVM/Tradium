package es.urjc.code.daw.library.valorations;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ValorationRepository extends JpaRepository <Valoration, Long> {
	Page <Valoration> findBySeller_Id(long id, Pageable page);
	List <Valoration> findBySellerId(long id);

}
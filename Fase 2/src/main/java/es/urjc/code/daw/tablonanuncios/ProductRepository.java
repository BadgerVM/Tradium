package es.urjc.code.daw.tablonanuncios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List <Product> findByName (String name);
	List <Product> findById (long id);
	List <Product> findByUser_Id(long id);

}	
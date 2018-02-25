package es.urjc.code.daw.tablonanuncios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List <Product> findByName (String name);
	Product findById (long id);
	List <Product> findByUser_Id(long id);
	Page <Product> findByUser_Id(long id, Pageable page);
	Product findTopByOrderByIdDesc();
	Page<Product> findByTagsContaining(String s, Pageable page);
	
	/*
	@Query(value = "select iu from iu product where iu.tags like '%:s%'", nativeQuery = true)
	Page<Product> getProducts(String s, Pageable page);*/
	
}	
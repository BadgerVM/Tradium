package Tradium;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository <User, Long> {
	User findByName (String name);
	User findByid (long id);
	User findTopByOrderByIdDesc();
}

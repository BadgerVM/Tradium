package es.urjc.code.daw.tablonanuncios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	Chat findById (long id);
	
	@Query(value = "select * from chat where user1_id=?1 or user2_id=?1", nativeQuery = true)
	List<Chat> getChats(User u);
	
	
}	
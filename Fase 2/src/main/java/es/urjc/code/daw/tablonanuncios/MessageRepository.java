package es.urjc.code.daw.tablonanuncios;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long> {
	Message findById (long id);
	
	@Query(value = "select * from message where chat_id=?1", nativeQuery = true)
	List<Message> getMessages(Chat c);
	
	@Query(value = "select * from message where chat_id in (select id from chat where user1_id=?1 or user2_id=?1)", nativeQuery = true)
	List<Message> getAllMessagesFromUser(User u);
}	
package es.urjc.code.daw.library.chats;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.library.chats.Message;
import es.urjc.code.daw.library.security.LoginController;
import es.urjc.code.daw.library.user.User;
@Entity
public class Chat {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	public interface ChatAtt {}
	public interface PlayersAtt {}
	
	@JsonView(ChatAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	
	@OneToOne
	private User user1;
	
	@JsonView(ChatAtt.class)
	@OneToOne
	private User user2;
	
	@JsonView(ChatAtt.class)
	private boolean readu1;
	
	@JsonView(ChatAtt.class)
	private boolean readu2;
	
	@JsonView(ChatAtt.class)
	private boolean system;
	
	
	@Autowired
	@OneToMany
	private List<Message> messages = new ArrayList<>();
	
	public Chat() {}
	
	public Chat(User user1, User user2) {
		super();
		this.user1=user1;
		this.user2=user2;
		this.readu1 = false;
		this.readu2 = false;
		this.system = false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public void setReadu(long u, boolean b) {
		if (this.user1.getId() == u) {
			this.readu1 = b;
			log.info("usuario 1 siendo modificado con:" +String.valueOf(b));
		}else {
			this.readu2 = b;
			log.info("usuario 2 siendo modificado con:" +String.valueOf(b));
		}
	}
	
	public boolean getReadu1() {
		return this.readu1;
	}
	
	public boolean getReadu2() {
		return this.readu2;
	}
	
	public void setSystem(boolean b) {
		this.system = b;
	}
	
	public boolean getSystem() {
		return this.system;
	}
}
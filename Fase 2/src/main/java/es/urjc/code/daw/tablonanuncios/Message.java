package es.urjc.code.daw.tablonanuncios;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Message {
	public interface BasicAtt {}
	public interface PlayersAtt {}
	
	@JsonView(BasicAtt.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonView(BasicAtt.class)
	@OneToOne
	private User transmitter;
	
	@JsonView(BasicAtt.class)
	private String text;
	
	@Autowired
	@OneToOne
	private Chat chat;
	
	
	public Message() {}
	
	public Message(User user, String text) {
		super();
		this.transmitter=user;
		this.text=text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(User transmitter) {
		this.transmitter = transmitter;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
	
}

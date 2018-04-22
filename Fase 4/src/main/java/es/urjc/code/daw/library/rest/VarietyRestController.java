package es.urjc.code.daw.library.rest;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import es.urjc.code.daw.library.user.*;
import es.urjc.code.daw.library.chats.ChatRepository;
import es.urjc.code.daw.library.chats.MessageRepository;
import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.valorations.*;
import es.urjc.code.daw.library.product.Product.ProductAtt;
import es.urjc.code.daw.library.chats.*;

import es.urjc.code.daw.library.chats.Message.MessageAtt;


import com.fasterxml.jackson.annotation.JsonView;


@RestController
@RequestMapping("/api")
public class VarietyRestController {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	

	interface chatDetail extends User.UserIdAtt, Chat.ChatAtt {}
	
	@JsonView(chatDetail.class)
	@RequestMapping(value="/chats", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Chat>> chats (Model model, HttpServletRequest request) {
		
		
		
		User loggedUser = userComponent.getLoggedUser();
		Long id  = loggedUser.getId();
		List<Chat> chatList = chatRepository.getChats(id);
		
		List <Chat> finalChatList = new ArrayList <>();
		Chat chat2 = new Chat();
		
		for (Chat chat : chatList) {
			
			if (chat.getUser1().getId() != id) {
				chat2.setId(chat.getId());
				chat2.setUser1(chat.getUser2());
				chat2.setUser2(chat.getUser1());
				finalChatList.add(chat2);
				chat2 = new Chat();
			}else {
				finalChatList.add(chat);
			}		
		}
		
		return new ResponseEntity <>(finalChatList, HttpStatus.OK);
	}
	
	interface MessageDetail extends User.UserIdAtt, Message.MessageAtt {}
	@JsonView(MessageDetail.class)
	@RequestMapping(value="/chats/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Message>> chats (Model model, @PathVariable long id,  HttpServletRequest request) {
		
		List<Message> chatList = messageRepository.getMessages(chatRepository.findById(id));
		
		User loggedUser = userComponent.getLoggedUser();
		
		if (chatList == null) {
			return new ResponseEntity <>(chatList, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity <>(chatList, HttpStatus.OK);
	}
	
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/chats/{id}/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Message>> chatNew (Model model, @PathVariable long id,  @RequestBody Message m,  HttpServletRequest request) {
		
		
		List<Message> chatList = messageRepository.getMessages(chatRepository.findById(id));
		Chat chat = chatRepository.findById(id);

		Message m1= new Message();
		m1.setText(m.getText());
		m1.setTransmitter(userRepository.findByid(userComponent.getLoggedUser().getId()));
		m1.setChat(chatRepository.findById(id));
		messageRepository.save(m1);
		
		User loggedUser = userComponent.getLoggedUser();
		
		if (chatList == null) {
			return new ResponseEntity <>(chatList, HttpStatus.NOT_FOUND);
		}
		chatList.add(m1);
		chat.addMessage(m1);
		chatRepository.save(chat);
		
		return new ResponseEntity <>(chatList, HttpStatus.OK);
	}
	
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/search/", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> product (Model model) {
		List<Product>products = productRepository.findAll();
	    if (!products.equals(null)) {
	    	return new ResponseEntity<>(products,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/search/{tag}", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> product (Model model, @PathVariable String tag) {
		List<Product> products = productRepository.findByTags(tag);
	    if (!products.equals(null)) {
	    	return new ResponseEntity<>(products,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/featured", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> featured (Model model) {
	    List<Product>products = productRepository.findByFeatured(true);
	    if (!products.equals(null)) {
	    	return new ResponseEntity<>(products,HttpStatus.OK);
	    }
	    
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
}
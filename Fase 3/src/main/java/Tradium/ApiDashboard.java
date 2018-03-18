package Tradium;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;

import Tradium.Chat.ChatAtt;
import Tradium.Message.MessageAtt;
import Tradium.Product.ProductAtt;
import Tradium.User.UserAtt;
import Tradium.Valoration.ValorationAtt;

@RestController
@RequestMapping("/api")
public class ApiDashboard {
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ValorationRepository valorationRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> product (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	return new ResponseEntity<>(product,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	interface UserDetail extends User.UserAtt, User.UserIdAtt, Product.ProductAtt {}
	@JsonView(UserDetail.class)
	@RequestMapping(value="/seller/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> User (Model model, @PathVariable long id) {
		
	    User user = userRepository.findByid(id);
	    if (!user.equals(null)) {
	    	
	    	List <Product> products = productRepository.findByUser_Id(id);
	  
	    	return new ResponseEntity<>(user,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value="/user/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User register (Model model, @RequestBody User user, HttpServletRequest request) {
		String s ="USER";
		List <String> role = new ArrayList();
		role.add(s);
		if(user.getLocationX()==null) {

			user.setLocationX("0000");
		}
		if(user.getLocationY()==null) {

			user.setLocationY("0000");
		}
		if(user.getImage() ==null) {

			user.setImage("\\images\\user_images\\user_default.jpg");
		}
		
		user.setMedValoration(0);
		user.setRoles(role);
		userRepository.save(user);
		
		return user;
		
	}
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/product/{id}/delete", method=RequestMethod.DELETE)
	public ResponseEntity<Product>  deleteProduct (Model model, @PathVariable Integer id) {
		
		Product product = productRepository.findById(id);
		User loggedUser = userComponent.getLoggedUser();
		
			
		if(!loggedUser.isUserInRole("ADMIN")){
			
				if (loggedUser.getId() != product.getUser().getId()) {
					return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
				}
		}
		
		if (product != null) {
			
			productRepository.delete(product);
			return new ResponseEntity <>(product, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}

	interface ValorationDetail extends Valoration.ValorationAtt, User.UserAtt,  User.UserIdAtt{}
	@JsonView(ValorationDetail.class)
	@RequestMapping(value="/seller/{id}/valorations", method=RequestMethod.GET)
	public ResponseEntity<List<Valoration>>  showValoration (Model model, @PathVariable Integer id) {
		
		List<Valoration> valorations = valorationRepository.findBySellerId(id);
		if (valorations != null) {
			
			
			return new ResponseEntity <>(valorations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/product/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> upload (Model model, @RequestBody Product product, HttpServletRequest request) {
		
		User loggedUser = userComponent.getLoggedUser();
		if (loggedUser == null) {
			return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
		}
		Product productOur = new Product(product.getName(), product.getDescription(), product.getTags(), product.getPrice());
		productOur.setUser(loggedUser);
		productRepository.save(productOur);
		User u =userRepository.findByid(loggedUser.getId());
		productOur.setFeatured(false);
        u.addProduct(productOur);
        userRepository.save(u);
		return new ResponseEntity <>(productOur, HttpStatus.OK);
		
	}
	
	interface chatDetail extends User.UserIdAtt, Chat.ChatAtt {}
	
	@JsonView(chatDetail.class)
	@RequestMapping(value="/chats", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Chat>> chats (Model model, HttpServletRequest request) {
		
		
		
		User loggedUser = userComponent.getLoggedUser();
		Long id  = loggedUser.getId();
		List<Chat> chatList = chatRepository.getChats(userRepository.findByid(id));
		
		List <Chat> finalChatList = new ArrayList <>();
		Chat chat2 = new Chat();
		
		for (Chat chat : chatList) {
			
			if (chat.getUser1().getId() != id) {
				chat2.setUser1(chat.getUser2());
				chat2.setUser2(chat.getUser1());
				finalChatList.add(chat2);
				
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
	@RequestMapping(value="/search/{tag}", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> product (Model model, @PathVariable String tag) {
	    List<Product>products = productRepository.findByTags(tag);
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
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/offer", method=RequestMethod.POST)
	public ResponseEntity<Message> offer (Model model, @PathVariable long id, @RequestBody int pr) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	User loggedUser = userComponent.getLoggedUser();
			if(loggedUser.getId()!=product.getUser().getId()) {
				
				Chat c1 = new Chat(loggedUser, product.getUser());
		  		Message m1 = new Message();
				
				if( pr > productRepository.findById(id).getMinPrice() ) {
					m1 = new Message(product.getUser(), "Hi! i offer you "+ pr +"€"); 	
				}else {
					m1 = new Message(product.getUser(), "Hi! i offer you "+ pr +"€, may you get your price down a little bit?");
				}
				
				m1.setChat(c1);
				product.addListBuyers(loggedUser);	

				chatRepository.save(c1);
				messageRepository.save(m1);

				c1.addMessage(m1);
				chatRepository.save(c1);
				productRepository.save(product);
				return new ResponseEntity<>(m1,HttpStatus.OK);
	    	
			}
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/buy", method=RequestMethod.POST)
	public ResponseEntity<Message> buy (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	User loggedUser = userComponent.getLoggedUser();
			if(loggedUser.getId()!=product.getUser().getId()) {


			  	product.setBought(true);
			  	product.addListBuyers(loggedUser);	
			  	User seller = product.getUser();
			  	
			  	Chat c1 = new Chat(loggedUser, seller);
			  	Message m1 =  new Message(seller, "Hi! I bought your " + product.getName()); m1.setChat(c1);
			  	chatRepository.save(c1);
				messageRepository.save(m1);
			  	productRepository.save(product);
			  	
				return new ResponseEntity<>(m1,HttpStatus.OK);
	    	
			}
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/sold", method=RequestMethod.POST)
	public ResponseEntity<Message> sold (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	User loggedUser = userComponent.getLoggedUser();
			if(loggedUser.getId()==product.getUser().getId() && product.getBought()) {

				User seller = product.getUser();
		  		Chat c1 = new Chat(loggedUser, seller);
				Message m1 = new Message(seller, "Could you please valorate me in api/user/"+seller.getId()+"/valoration");m1.setChat(c1);
		  		
				chatRepository.save(c1);
				messageRepository.save(m1);
			  	
			  	
			  	
				return new ResponseEntity<>(m1,HttpStatus.OK);
	    	
			}
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(ValorationAtt.class)
	@RequestMapping(value="/user/{id}/valoration", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Valoration> createValoration (Model model, @PathVariable long id, @RequestBody Valoration valoration, HttpServletRequest request) {
		
		User loggedUser = userComponent.getLoggedUser();
		if(loggedUser.getId()!=id) {
			User seller=userRepository.findByid(id);
			Valoration v =new Valoration(seller, loggedUser, valoration.getValoration(), valoration.getDescription());
			valorationRepository.save(v);
			
			return new ResponseEntity <>(v, HttpStatus.OK);
		}
		return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
		
	}
	
	
	
	
	

}

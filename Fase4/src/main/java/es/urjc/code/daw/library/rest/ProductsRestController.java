package es.urjc.code.daw.library.rest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.library.chats.Message.MessageAtt;
import es.urjc.code.daw.library.product.Product.ProductAtt;
import es.urjc.code.daw.library.rest.SellerRestController.UserDetail;
import es.urjc.code.daw.library.rest.SellerRestController.ValorationDetail;
import es.urjc.code.daw.library.security.LoginController;
import es.urjc.code.daw.library.chats.*;
import es.urjc.code.daw.library.product.*;
import es.urjc.code.daw.library.user.*;
import es.urjc.code.daw.library.valorations.Valoration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProductsRestController {
	
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
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
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	interface APIDetail extends User.UserIdAtt, Product.ProductAtt {}
	@JsonView(APIDetail.class)
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> product (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	return new ResponseEntity<>(product,HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(ProductAtt.class)
    @RequestMapping(value="/product/{id}/delete", method=RequestMethod.DELETE)
    public ResponseEntity<Product>  deleteProduct (Model model, @PathVariable Integer id) {
        Product product = productRepository.findById(id);

        User loggedUser = userComponent.getLoggedUser();
        
        if (loggedUser.getId() != product.getUser().getId()) {

            if(!loggedUser.isUserInRole("ADMIN")){
                return new ResponseEntity <>(product, HttpStatus.UNAUTHORIZED);
            }

        }


        if (product != null) {
            User u = product.getUser();
            userRepository.save(u);
            productRepository.delete(product);
            return new ResponseEntity <>(product, HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@JsonView(ProductAtt.class)
	@RequestMapping(value="/product/new", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> upload (Model model, @RequestBody Product product, HttpServletRequest request) throws FileNotFoundException, IOException {
		
		User loggedUser = userComponent.getLoggedUser();
		if (loggedUser == null) {
			return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
		}

		
		Product productOur = new Product(product.getName(), product.getDescription(), product.getTags(), product.getPrice());
		productOur.setUser(loggedUser);
		productOur.setImage("\\images\\product_images\\"+product.getName()+"-1.jpg");
		productRepository.save(productOur);
		User u =userRepository.findByid(loggedUser.getId());
		productOur.setFeatured(false);
        //u.addProduct(productOur);
        userRepository.save(u);
		return new ResponseEntity <>(productOur, HttpStatus.OK);
		
	}
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/offer", method=RequestMethod.POST)
	public ResponseEntity<Message> offer (Model model, @PathVariable long id, @RequestBody Offer offer) {
		
		
		int pr = Integer.parseInt(offer.getOffer());
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	User loggedUser = userComponent.getLoggedUser();
			if(loggedUser.getId() != product.getUser().getId()) {
				
				Chat c1 = new Chat(loggedUser, product.getUser());
		  		Message m1 = new Message();
				
				if( pr > productRepository.findById(id).getMinPrice() ) {
					m1 = new Message(product.getUser(), "Hi! i offer you "+ pr +"€"); 	
				}else {
					m1 = new Message(product.getUser(), "Hi! i offer you "+ pr +"€, may you get your price down a little bit?");
				}
				
				m1.setChat(c1);
				Buyer b = new Buyer(loggedUser);
				product.addListBuyers(b);	

				c1.setReadu(loggedUser.getId(), true);
                c1.setReadu(product.getUser().getId(), false);
				
                buyerRepository.save(b);
				chatRepository.save(c1);
				messageRepository.save(m1);
				userRepository.save(product.getUser());
				userRepository.save(loggedUser);
				c1.addMessage(m1);
				chatRepository.save(c1);
				productRepository.save(product);
				return new ResponseEntity<>(m1,HttpStatus.OK);
	    	
			}
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/buy", method=RequestMethod.GET)
	public ResponseEntity<Chat> buy (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	User loggedUser = userComponent.getLoggedUser();
	    	if (loggedUser == null) {
				return new ResponseEntity <>(HttpStatus.UNAUTHORIZED);
			}
		    			    	
				if(loggedUser.getId()!=product.getUser().getId()) {
	
	
				  	product.setBought(true);
				  	Buyer b = new Buyer(loggedUser);
				  	product.addListBuyers(b);	
				  	User seller = product.getUser();
				  	
				  
				  	Chat c1 = new Chat(loggedUser, seller);
				 
				  	c1.setSystem(false);
			  		c1.setReadu(loggedUser.getId(), true);
			  		c1.setReadu(seller.getId(), false);
			  	
				  	Message m1 =  new Message(seller, "Hi! I bought your " + product.getName()); m1.setChat(c1);
				  	
				  	buyerRepository.save(b);
				  	chatRepository.save(c1);
					messageRepository.save(m1);
					userRepository.save(loggedUser);
					userRepository.save(seller);
				  	productRepository.save(product);
				  	
					return new ResponseEntity<>(c1,HttpStatus.OK);
		    	
				}
	    	
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	interface BuyersDetail extends Product.ProductAtt, User.UserAtt,  User.UserIdAtt{}
	@JsonView(BuyersDetail.class)
	@RequestMapping(value="/product/{id}/buyers", method=RequestMethod.GET)
	public ResponseEntity<List<Buyer>> buyers (Model model, @PathVariable long id) {
		
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	
	    	List <Buyer> buyers = product.getListBuyers();
	    	//List <User> buyers = buye
			  	
			  	
			return new ResponseEntity<>(buyers, HttpStatus.OK);
	    	
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(MessageAtt.class)
	@RequestMapping(value="/product/{id}/sold", method=RequestMethod.POST)
	public ResponseEntity<Message> sold (Model model, @PathVariable long id, @RequestBody User user) {
		log.info(Long.toString(id));
	    Product product = productRepository.findById(id);
	    if (!product.equals(null)) {
	    	if(userComponent.getLoggedUser()!= null) {
	    		
		    	User loggedUser = userComponent.getLoggedUser();
		    	
				if(loggedUser.getId()==product.getUser().getId() && (product.getListBuyers().size() > 0)  ) {
	
					User seller = loggedUser;
	
					product.setBought(true);
			  		Chat c1 = new Chat(user, seller);
			  		c1.setSystem(true);
					Message m1 = new Message(seller, "Could you please valorate me?");
					m1.setChat(c1);
			  		productRepository.save(product);
			  		
			  		
					chatRepository.save(c1);
					messageRepository.save(m1);
					
					c1.addMessage(m1);
					
					chatRepository.save(c1);
				  	
					return new ResponseEntity<>(m1,HttpStatus.OK);
		    	
				}
			}else{
				return new ResponseEntity<>(HttpStatus.FORBIDDEN );		
			}
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}


}
